package com.jone.cargo.util

import java.io.{File, FileInputStream}
import java.util.Properties

object PropertiesScalaUtils {

  val directory = new File(".")
  val properties = directory.getAbsolutePath + File.separator + "conf" + File.separator + "conf.properties"
  var specialRainStation = scala.collection.mutable.Set[String]()

  def loadProperties(key: String): String = {
    var ret = ""
    val file = new File(properties)
    if (file.exists()) {
      val properties = new Properties()
      properties.load(new FileInputStream(file))
      ret = properties.getProperty(key)
    }
    ret
  }

  def init(): Unit = {
    specialRainStation = getSpecialRainStation
  }

  def getSpecialRainStation(): scala.collection.mutable.Set[String] = {
    val ret = scala.collection.mutable.Set[String]()
    val str = loadProperties("specialRain")
    if (str != null && str.length > 0) {
      str.split(",").foreach(ret += _)
    }
    ret
  }
  def getTasks(): String = {
    loadProperties("task")
  }
//  def main(args: Array[String]): Unit = {
//    println(getSpecialRainStation().contains("10000092"))
//  }
}
