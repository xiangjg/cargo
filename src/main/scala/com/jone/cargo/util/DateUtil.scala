package com.jone.cargo.util

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import org.slf4j.LoggerFactory

object DateUtil {
  class DateUtil
  val log = LoggerFactory.getLogger(classOf[DateUtil])

  def formatDateTime(date: Date): String = {
    val sdf: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    sdf.format(date)
  }

  def parse(dateStr: String): Date = {
    var date = new Date
    val sdf: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    try {
      if(dateStr.length == 19)
        date = sdf.parse(dateStr)
      else{
        val strTmp: Array[String] = dateStr.split("-");
        if(strTmp(1).length == 1)
          strTmp(1) = "0" + strTmp(1)
        val strTmp2: Array[String] = strTmp(2).split(" ")
        if(strTmp2(0).length == 1)
          strTmp2(0) = "0" + strTmp2(0)
        date = sdf.parse(strTmp(0)+"-"+strTmp(1)+"-"+strTmp2(0)+" "+strTmp2(1))
      }
    }catch {
      case e:Exception => {
        log.error(e.getMessage)
        throw new Exception("["+dateStr+"]日期格式错误：应为[yyyy-MM-dd HH:mm:ss] "+e.getMessage)
      }
    }
    date
  }

  def tranTimeStrToLong(tm: String) = {
    val sdf: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    sdf.parse(tm).getTime() / 1000
  }

  def tranTimeDateToLong(tm: Date) = tm.getTime() / 1000

  def tranTimeLongStrToDateString(tm: String) = {
    val sdf: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    sdf.format(new Date(tm.toLong * 1000))
  }

  def tranTimeLongStrToDate(tm: String) = new Date(tm.toLong * 1000)

  def tranTimeLongToDateString(tm: Long) = {
    val sdf: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    sdf.format(new Date(tm * 1000))
  }

  def tranTimeLongToDate(tm: Long) = new Date(tm * 1000)

  def timeMinuteStr(tm: Date) = {
    val sdfMinute: SimpleDateFormat = new SimpleDateFormat("mm")
    sdfMinute.format(tm)
  }

  /**
    * 当前时间往前三天 分钟、秒设置为0
    * @return
    */
  def getAfterThreeDayDate = {
    val calendar = Calendar.getInstance()
    calendar.setTime(new Date)
    calendar.add(Calendar.DAY_OF_MONTH,-3)
    calendar.set(Calendar.MINUTE,0)
    calendar.set(Calendar.SECOND,0)
    calendar.getTime
  }

  def convertWsdlDateTime(tm:Date):String = {
    val date: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
    val time: SimpleDateFormat = new SimpleDateFormat("HH:mm:ss")
    date.format(tm)+"T"+time.format(tm)
  }
  def convertWsdlDateTimeAddMinute(tm:Date):String = {
    val calendar = Calendar.getInstance()
    calendar.setTime(tm)
    calendar.add(Calendar.MINUTE,1)
    convertWsdlDateTime(calendar.getTime)
  }
}
