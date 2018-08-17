package com.jone.cargo.service

import java.util

import com.alibaba.dubbo.config.annotation.Service
import com.jone.cargo.api.HouseService
import com.jone.cargo.config.DBConfig
import com.jone.cargo.config.DBConfig.db._
import com.jone.cargo.db.DBObject
import com.jone.cargo.entity.HouseVo

@Service
class HouseServiceImpl extends HouseService {
  override def saveHouse(barcode: String, name: String, inPrice: Double, outPrice: Double, price: Double, userId: String): Any = {
    val Houses = DBConfig.db.run(
      DBObject.house.filter((s: DBObject.House) => s.barcode == lift(barcode) && s.userId == lift(userId))
    )
    val House = DBObject.House(barcode, name, BigDecimal.apply(inPrice), BigDecimal.apply(outPrice), BigDecimal.apply(price), userId)
    if (Houses.size == 0) {
      DBConfig.db.run(
        DBObject.house.insert(liftCaseClass(House))
      )
    } else {
      DBConfig.db.run(
        DBObject.house.filter((s: DBObject.House) => s.barcode == lift(barcode) && s.userId == lift(userId)).update(liftCaseClass(House))
      )
    }
  }

  override def queryHouse(barcode: String, userId: String): Any = {
    val houses = DBConfig.db.run(
      DBObject.house.filter((s: DBObject.House) => s.barcode == lift(barcode) && s.userId == lift(userId))
    )
    val ret = if (houses.size > 0) {
      val s = houses.head
      new HouseVo(s.barcode, s.name, s.inPrice, s.outPrice, s.price)
    } else Nil
    ret
  }

  override def queryHouseByUser(userId: String): Any = {
    val houses = DBConfig.db.run(
      DBObject.house.filter(_.userId == lift(userId))
    )
    val ret = new util.ArrayList[HouseVo]
    if (houses.size > 0) {
      houses.foreach((s: DBObject.House) => {
        ret.add(new HouseVo(s.barcode, s.name, s.inPrice, s.outPrice, s.price))
      })
    }
    ret
  }
}
