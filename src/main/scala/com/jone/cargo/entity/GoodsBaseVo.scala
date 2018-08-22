package com.jone.cargo.entity

import java.util.Date

import com.jone.cargo.util.DateUtil

import scala.beans.BeanProperty

class GoodsBaseVo(_barcode: String, _name: String, _insertDate:Date) extends Serializable {
  @BeanProperty var barcode: String = _barcode
  @BeanProperty var name: String = _name
  @BeanProperty var insertDate: String = DateUtil.formatDateTime(_insertDate)
}
