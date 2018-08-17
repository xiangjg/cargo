package com.jone.cargo.entity

import scala.beans.BeanProperty

class UserVo(_userId:String, _name:String) extends Serializable {

  @BeanProperty var userId:String = _userId
  @BeanProperty var name:String = _name
}
