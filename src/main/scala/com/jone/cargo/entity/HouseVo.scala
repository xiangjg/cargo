package com.jone.cargo.entity

import scala.beans.BeanProperty

class HouseVo(_barcode: String, _name: String, _inPrice: BigDecimal, _outPrice: BigDecimal, _price: BigDecimal) extends Serializable {
  @BeanProperty var barcode: String = _barcode
  @BeanProperty var name: String = _name
  @BeanProperty var inPrice: Double = _inPrice.doubleValue()
  @BeanProperty var outPrice: Double = _outPrice.doubleValue()
  @BeanProperty var price: Double = _price.doubleValue()
}
