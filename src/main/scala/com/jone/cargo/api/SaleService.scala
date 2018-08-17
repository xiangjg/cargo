package com.jone.cargo.api

import java.util

import com.jone.cargo.entity.SaleVo

trait SaleService {

  def saveSale(json:String,userId:String):Any

  def querySale(userId:String):util.ArrayList[SaleVo]

  def querySaleDetail(saleId:Int):util.ArrayList[util.HashMap[String,Object]]

}
