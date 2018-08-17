package com.jone.cargo.service

import com.alibaba.dubbo.config.annotation.Service
import com.jone.cargo.api.LoginService
import com.jone.cargo.config.DBConfig
import com.jone.cargo.config.DBConfig.db._
import com.jone.cargo.db.DBObject
import com.jone.cargo.entity.UserVo
import org.apache.commons.lang.StringUtils

@Service
class LoginServiceImpl extends LoginService {
  override def login(userId: String, password: String): Any = {
    if (StringUtils.isEmpty(userId))
      throw new Exception("用户名不能为空")
    if (StringUtils.isEmpty(password))
      throw new Exception("密码不能为空")

    val user = DBConfig.db.run(
      DBObject.user.filter(_.userId == lift(userId))
    )

    if (user.size == 0)
      throw new Exception("用户名不存在")
    val u = user.head
    if (!u.password.equals(password))
      throw new Exception("密码错误")
    new UserVo(u.userId,u.name)
  }
}
