package com.jone.cargo.service

import com.alibaba.dubbo.config.annotation.Service
import com.jone.cargo.api.GoodsService
import com.jone.cargo.config.DBConfig
import com.jone.cargo.config.DBConfig.db._
import com.jone.cargo.db.DBObject
import com.jone.cargo.entity.GoodsVo
import org.slf4j.LoggerFactory

@Service
class GoodsServiceImpl extends GoodsService {

  val log = LoggerFactory.getLogger(classOf[GoodsServiceImpl])

  override def save(code: String): Boolean = {
    var ret = false
    try {
      val goods = DBConfig.db.run(
        DBObject.goods.filter(_.barcode == lift(code))
      )
      if (goods.size == 0) {
        DBConfig.db.run(
          DBObject.goodsBase.insert(liftCaseClass(DBObject.GoodsBase(code)))
        )
      }
      ret = true
    } catch {
      case e: Exception => {
        log.error(e.getMessage)
        ret = false
      }
    }
    ret
  }

  override def queryByCode(code: String): Any = {
    val goods = DBConfig.db.run(
      DBObject.goods.filter(_.barcode == lift(code))
    )
    val ret = if (goods.size > 0) {
      val g = goods.head
      new GoodsVo(g.barcode, g.name, g.eName, g.unspsc, g.brand, g._type, g.width, g.height, g.depth, g.originCountry, g.originPlace, g.assemblyCountry, g.barcodeType, g.catena, g.isBasicUnit, g.packageType, g.grossWeight, g.netWeight, g.description, g.keyword, g.pic, g.price, g.licenseNum, g.healthPermitNum, g.netContent, g.company, g.expirationDate)
    } else Nil
    ret
  }
}
