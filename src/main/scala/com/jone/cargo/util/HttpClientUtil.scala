package com.jone.cargo.util

import java.io.InputStream

import com.jone.cargo.entity.Response
import org.apache.commons.lang.StringUtils
import org.apache.http.HttpEntity
import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet, HttpPost}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.{CloseableHttpClient, HttpClients}
import org.apache.http.util.EntityUtils

import scala.io.Source

object HttpClientUtil {
  val httpClient: CloseableHttpClient = HttpClients.createDefault()

  /**
    * doGet请求获取一个网页
    *
    * @param url
    * @return
    */
  def doGet(url: String): Response = {
    val httpGet: HttpGet = new HttpGet(url) //初始化httpGet
    val httpResponse: CloseableHttpResponse = httpClient.execute(httpGet)
    val httpEntity: HttpEntity = httpResponse.getEntity
    val inputStream: InputStream = httpEntity.getContent
    val pageContent: String = Source.fromInputStream(inputStream).mkString //inputStream转化为String
    val status: Int = httpResponse.getStatusLine.getStatusCode
    EntityUtils.consume(httpEntity) //关闭httpResponse中的inputStream
    httpResponse.close()
    new Response(status, url, pageContent)
  }

  def doPost(url: String,params:String): Response = {
    val httpPost: HttpPost = new HttpPost(url)
    httpPost.setHeader("Content-type", "application/json")
    httpPost.setEntity(new StringEntity(params))
    val httpResponse: CloseableHttpResponse = httpClient.execute(httpPost)
    val httpEntity: HttpEntity = httpResponse.getEntity
    val inputStream: InputStream = httpEntity.getContent
    val pageContent: String = Source.fromInputStream(inputStream).mkString //inputStream转化为String
    val status: Int = httpResponse.getStatusLine.getStatusCode
    EntityUtils.consume(httpEntity) //关闭httpResponse中的inputStream
    httpResponse.close()
    new Response(status, url, pageContent)
  }

  def main(args: Array[String]): Unit = {
    val url: String = "http://222.85.224.95:8090/bdsp/getPptn"
    val resp: Response = HttpClientUtil.doPost(url,"{\"key\":\"CUSTOMER_ZHSL\",\"stcd\":\"DF000011\",\"stdate\":1526436000}")
    println(resp.content)
  }
}
