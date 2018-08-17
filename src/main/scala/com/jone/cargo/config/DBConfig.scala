package com.jone.cargo.config

import io.getquill.{MysqlJdbcContext, SnakeCase}

object DBConfig {
  lazy val db = new MysqlJdbcContext(SnakeCase, "ctx")
}
