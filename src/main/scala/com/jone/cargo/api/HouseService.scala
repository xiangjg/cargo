package com.jone.cargo.api

trait HouseService {

  def saveHouse(barcode:String, name:String, inPrice:Double, outPrice:Double, price:Double, userId:String):Any

  def queryHouse(barcode:String, userId:String):Any

  def queryHouseByUser(userId:String):Any
}
