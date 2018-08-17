package com.jone.cargo.service

import java.util
import java.util.Date

import com.alibaba.dubbo.config.annotation.Service
import com.alibaba.fastjson.JSON
import com.jone.cargo.api.SaleService
import com.jone.cargo.config.DBConfig
import com.jone.cargo.config.DBConfig.db._
import com.jone.cargo.db.DBObject
import com.jone.cargo.entity.SaleVo
import com.jone.cargo.util.DateUtil
import org.slf4j.LoggerFactory

@Service
class SaleServiceImpl extends SaleService {

  val log = LoggerFactory.getLogger(classOf[SaleServiceImpl])

  override def saveSale(json: String,userId:String): Any = {
    log.debug("结算：{}",json)
    val jSONArray = JSON.parseArray(json)
    if(jSONArray!=null&&jSONArray.size()>0){
      val time = new Date
      var sum = BigDecimal.apply(0)
      var i = 0
      while (i < jSONArray.size()) {
        val jsonObj = jSONArray.getJSONObject(i)
        val price:BigDecimal = jsonObj.getBigDecimal("price")
        val num = jsonObj.getInteger("num")
        sum = sum.+(price.*(BigDecimal.apply(num)))
        i += 1
      }
      val saleId = DBConfig.db.run(
        DBObject.sale.insert(liftCaseClass(DBObject.Sale(0,sum,time,userId))).returning(_.id)
      )
      i = 0
      while (i < jSONArray.size()) {
        val jsonObj = jSONArray.getJSONObject(i)
        val price = jsonObj.getBigDecimal("price")
        DBConfig.db.run(
          DBObject.saleDetail.insert(liftCaseClass(DBObject.SaleDetail(saleId,price,jsonObj.getInteger("num"),jsonObj.getString("code"),jsonObj.getString("name"),time)))
        )
        i += 1
      }
    }
    true
  }

  override def querySale(userId: String): util.ArrayList[SaleVo] = {
    val list = new util.ArrayList[SaleVo]()
    DBConfig.db.run(
      DBObject.sale.filter(_.userId == lift(userId)).sortBy(_.time)(Ord.desc)
    ).foreach((s:DBObject.Sale) => {
      list.add(new SaleVo(s.id, s.price, DateUtil.formatDateTime(s.time)))
    })
    list
  }

  override def querySaleDetail(saleId: Int): util.ArrayList[util.HashMap[String,Object]] = {
    val list = new util.ArrayList[util.HashMap[String,Object]]()
    DBConfig.db.run(
      DBObject.saleDetail.filter(_.sale_id == lift(saleId))
    ).foreach((s:DBObject.SaleDetail) => {
      val map = new util.HashMap[String,Object]()
      map.put("price",s.price.toString)
      map.put("num",s.num.toString)
      map.put("barcode",s.barcode)
      map.put("name",s.name)
      list.add(map)
    })
    list
  }
}
