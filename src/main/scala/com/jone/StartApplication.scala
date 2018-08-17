package com.jone

import java.io.File

import com.jone.cargo.task.MainTask
import com.jone.cargo.util.PropertiesScalaUtils
import org.slf4j.LoggerFactory
import org.springframework.context.support.ClassPathXmlApplicationContext

object StartApplication {

  val log = LoggerFactory.getLogger(StartApplication.getClass)

  def main(args: Array[String]): Unit = {
    println("服务启动...")
    // 获取配置文件路径，兼容本地与服务器运行
    val directory = new File(".")
    val conf = "file:" + directory.getAbsolutePath + File.separator + "conf" + File.separator + "providers.xml"
    try {
      new ClassPathXmlApplicationContext(conf).start

      //启动调度任务
      if(args.length>0 && args.contains("task"))
        MainTask.start
      //读取配置信息
      PropertiesScalaUtils.init

      println("服务启动完成")

      this.synchronized {
        this.wait
      }
    } catch {
      case e: Exception => {
        println("服务启动异常：" + e.getMessage)
        log.error("服务启动异常：" + e.getMessage)
        System.exit(-1)
      }
    }
  }
}
