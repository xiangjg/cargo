package com.jone.cargo.task

import akka.actor.{ActorSystem, Props}
import com.jone.cargo.util.PropertiesScalaUtils

import scala.concurrent.duration._

object MainTask {

  val system = ActorSystem("msSystem")
  val goodsActor = system.actorOf(Props[GoodsActor])

  def start = {
    import scala.concurrent.ExecutionContext.Implicits.global

    val startTasks = PropertiesScalaUtils.getTasks
    if (startTasks.contains("goodsActor"))
      system.scheduler.schedule(0 milliseconds, 30 second, goodsActor, "")
  }

  def main(args: Array[String]): Unit = {
    MainTask.start
  }
}
