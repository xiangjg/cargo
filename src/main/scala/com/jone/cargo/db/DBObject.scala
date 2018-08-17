package com.jone.cargo.db

import java.util.Date

import com.jone.cargo.config.DBConfig.db._

object DBObject {

  /**
    *
    * @param barcode         商品条形码
    * @param name            名称
    * @param eName           英文名称
    * @param unspsc          分类
    * @param brand           品牌
    * @param _type
    * @param width           宽度
    * @param height          高度
    * @param depth           深度
    * @param originCountry   原产地
    * @param originPlace     原产地
    * @param assemblyCountry 装配国
    * @param barcodeType     条码类型
    * @param catena          产品系列
    * @param isBasicUnit     是否是基本单元
    * @param packageType     包装类型
    * @param grossWeight     毛重
    * @param netWeight       净重
    * @param description     描述
    * @param keyword         关键词
    * @param pic             图片
    * @param price           价格
    * @param licenseNum      生产许可证
    * @param healthPermitNum 卫生许可证
    * @param netContent      净含量
    * @param company
    * @param expirationDate
    */
  case class Goods(barcode: String, name: String, eName: String, unspsc: String, brand: String, _type: String, width: String, height: String, depth: String, originCountry: String, originPlace: String, assemblyCountry: String, barcodeType: String, catena: String, isBasicUnit: String, packageType: String, grossWeight: String, netWeight: String, description: String, keyword: String, pic: String, price: String, licenseNum: String, healthPermitNum: String, netContent: String, company: String, expirationDate: String)

  val goods = quote {
    querySchema[Goods](
      "cargo.goods",
      _.barcode -> "barcode",
      _.name -> "name",
      _.eName -> "ename",
      _.unspsc -> "unspsc",
      _.brand -> "brand",
      _._type -> "_type",
      _.width -> "width",
      _.height -> "height",
      _.depth -> "depth",
      _.originCountry -> "origincountry",
      _.originPlace -> "originplace",
      _.assemblyCountry -> "assemblycountry",
      _.barcodeType -> "barcodetype",
      _.catena -> "catena",
      _.isBasicUnit -> "isbasicunit",
      _.packageType -> "packagetype",
      _.grossWeight -> "grossweight",
      _.netWeight -> "netweight",
      _.description -> "description",
      _.keyword -> "keyword",
      _.pic -> "pic",
      _.price -> "price",
      _.licenseNum -> "licensenum",
      _.healthPermitNum -> "healthpermitnum",
      _.netContent -> "netcontent",
      _.company -> "company",
      _.expirationDate -> "expirationdate"
    )
  }

  case class GoodsBase(barcode: String)

  val goodsBase = quote {
    querySchema[GoodsBase](
      "cargo.goods",
      _.barcode -> "barcode"
    )
  }

  case class House(barcode: String, name: String, inPrice: BigDecimal, outPrice: BigDecimal, price: BigDecimal, userId: String)

  val house = quote {
    querySchema[House](
      "cargo.house",
      _.barcode -> "barcode",
      _.name -> "name",
      _.inPrice -> "in_price",
      _.outPrice -> "out_price",
      _.price -> "price",
      _.userId -> "user_id"
    )
  }

  case class SaleBase(price: BigDecimal, time: Date, userId: String)

  val saleBase = quote {
    querySchema[SaleBase](
      "cargo.sale",
      _.price -> "price",
      _.time -> "time",
      _.userId -> "user_id"
    )
  }

  case class Sale(id:Int,price: BigDecimal, time: Date, userId: String)

  val sale = quote {
    querySchema[Sale](
      "cargo.sale",
      _.id -> "id",
      _.price -> "price",
      _.time -> "time",
      _.userId -> "user_id"
    )
  }

  case class SaleDetail(sale_id:Int,price: BigDecimal,num:Int, barcode:String, name:String, time: Date)

  val saleDetail = quote {
    querySchema[SaleDetail](
      "cargo.sale_detail",
      _.sale_id -> "sale_id",
      _.price -> "price",
      _.num -> "num",
      _.time -> "time",
      _.barcode -> "barcode",
      _.name -> "name"
    )
  }

  case class User(userId:String, password: String, name: String)

  val user = quote {
    querySchema[User](
      "cargo.user",
      _.userId -> "user_id",
      _.password -> "password",
      _.name -> "name"
    )
  }
}
