package com.jone.cargo.api

trait GoodsService {

  def save(code: String):Boolean

  def queryByCode(code:String):Any

  def queryAllGoods():Any
}
