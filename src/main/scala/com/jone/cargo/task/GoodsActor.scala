package com.jone.cargo.task

import akka.actor.Actor
import com.jone.cargo.config.DBConfig
import com.jone.cargo.config.DBConfig.db._
import com.jone.cargo.db.DBObject
import com.jone.cargo.util.GoodsUtil
import org.slf4j.LoggerFactory

class GoodsActor extends Actor {
  val log = LoggerFactory.getLogger(classOf[GoodsActor])

  override def receive = {
    case _ => updateGoods()
  }

  def updateGoods() = {
    val goods = DBConfig.db.run(DBObject.goods.filter(_.name == null))
    if (goods.size > 0) {
      goods.foreach((g: DBObject.Goods) => {
        GoodsUtil.updateGoods(g.barcode)
      })
    }
  }
}
