package com.jone.cargo.entity

import scala.beans.BeanProperty

class Response(_status:Int, _url:String, pageContent:String) {
  @BeanProperty var status: Int = _status
  @BeanProperty var url: String = _url
  @BeanProperty var content: String = pageContent


  override def toString = s"Response($status, $url, $content)"
}
