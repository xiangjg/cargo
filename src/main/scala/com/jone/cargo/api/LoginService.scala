package com.jone.cargo.api

trait LoginService {
  def login(userId: String, password: String): Any
}
