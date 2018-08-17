package com.jone.cargo.entity

import scala.beans.BeanProperty


class SaleVo(_saleId:Int,_price:BigDecimal,_time:String) extends Serializable {
  @BeanProperty var saleId:Int = _saleId
  @BeanProperty var price:Double = _price.doubleValue()
  @BeanProperty var time:String = _time
}
