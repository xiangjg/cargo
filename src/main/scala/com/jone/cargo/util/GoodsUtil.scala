package com.jone.cargo.util

import com.alibaba.fastjson.JSON
import com.jone.cargo.config.DBConfig
import com.jone.cargo.config.DBConfig.db._
import com.jone.cargo.db.DBObject
import com.jone.cargo.entity.MyLogger
import org.apache.commons.lang.StringUtils
import org.slf4j.LoggerFactory

object GoodsUtil {

  val log = LoggerFactory.getLogger(classOf[MyLogger])

  def updateGoods(code:String)={
    val url = "https://way.jd.com/jisuapi/barcode2?barcode=" + code + "&appkey=348eda72e0ed654bdd5e82e65f7c011a"
    val response = HttpClientUtil.doGet(url)
    if (response != null && !StringUtils.isEmpty(response.content)) {
      val jsonObj = JSON.parseObject(response.content)
      val ret = jsonObj.getJSONObject("result")
      if(ret!=null){
        val jsonObject = ret.getJSONObject("result")
        if(jsonObject!=null){
          val name = jsonObject.getString("name")
          val eName = jsonObject.getString("ename")
          val unspsc = jsonObject.getString("unspsc")
          val brand = jsonObject.getString("brand")
          val _type = jsonObject.getString("type")
          val width = jsonObject.getString("width")
          val height = jsonObject.getString("height")
          val depth = jsonObject.getString("depth")
          val originCountry = jsonObject.getString("origincountry")
          val originPlace = jsonObject.getString("originplace")
          val assemblyCountry = jsonObject.getString("assemblycountry")
          val barcodeType = jsonObject.getString("barcodetype")
          val catena = jsonObject.getString("catena")
          val isBasicUnit = jsonObject.getString("isbasicunit")
          val packageType = jsonObject.getString("packagetype")
          val grossWeight = jsonObject.getString("grossweight")
          val netWeight = jsonObject.getString("netweight")
          val description = jsonObject.getString("description")
          val keyword = jsonObject.getString("keyword")
          val pic = jsonObject.getString("pic")
          val price = jsonObject.getString("price")
          val licenseNum = jsonObject.getString("licensenum")
          val healthPermitNum = jsonObject.getString("healthpermitnum")
          val netContent = jsonObject.getString("netcontent")
          val company = jsonObject.getString("company")
          val expirationDate = jsonObject.getString("expirationdate")
          val oldGoods = DBConfig.db.run(
            DBObject.goods.filter(_.barcode == lift(code))
          )
          val goods = DBObject.Goods(code,name, eName, unspsc, brand, _type, width, height, depth, originCountry, originPlace, assemblyCountry, barcodeType, catena, isBasicUnit, packageType, grossWeight, netWeight, description, keyword, pic, price, licenseNum, healthPermitNum, netContent, company, expirationDate,oldGoods.head.insertDate)
          log.debug("数据更新:{}",goods.toString)
          DBConfig.db.run(
            DBObject.goods.filter(_.barcode == lift(code)).update(liftCaseClass(goods))
          )
        }
      }
    }
  }
}
