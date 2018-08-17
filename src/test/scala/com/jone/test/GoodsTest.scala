package com.jone.test

import com.jone.cargo.util.HttpClientUtil
import org.junit.Test

@Test
class GoodsTest {

  @Test
  def test(): Unit ={
    val code = "6901236341582"
    val url = "https://way.jd.com/jisuapi/barcode2?barcode="+code+"&appkey=348eda72e0ed654bdd5e82e65f7c011a"
    val response = HttpClientUtil.doGet(url)
    println(response)
  }
}
