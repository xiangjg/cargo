package com.jone.cargo.entity

import java.util.Date

import com.jone.cargo.util.DateUtil

import scala.beans.BeanProperty

class GoodsVo(_barcode: String, _name: String, _eName: String, _unspsc: String, _brand: String, _type: String, _width: String, _height: String, _depth: String, _originCountry: String, _originPlace: String, _assemblyCountry: String, _barcodeType: String, _catena: String, _isBasicUnit: String, _packageType: String, _grossWeight: String, _netWeight: String, _description: String, _keyword: String, _pic: String, _price: String, _licenseNum: String, _healthPermitNum: String, _netContent: String, _company: String, _expirationDate: String, _insertDate: Date) extends Serializable {
  @BeanProperty var barcode: String = _barcode
  @BeanProperty var name: String = _name
  @BeanProperty var eName: String = _eName
  @BeanProperty var unspsc: String = _unspsc
  @BeanProperty var brand: String = _brand
  @BeanProperty var cType: String = _type
  @BeanProperty var width: String = _width
  @BeanProperty var height: String = _height
  @BeanProperty var depth: String = _depth
  @BeanProperty var originCountry: String = _originCountry
  @BeanProperty var originPlace: String = _originPlace
  @BeanProperty var assemblyCountry: String = _assemblyCountry
  @BeanProperty var barcodeType: String = _barcodeType
  @BeanProperty var catena: String = _catena
  @BeanProperty var isBasicUnit: String = _isBasicUnit
  @BeanProperty var packageType: String = _packageType
  @BeanProperty var grossWeight: String = _grossWeight
  @BeanProperty var netWeight: String = _netWeight
  @BeanProperty var description: String = _description
  @BeanProperty var keyword: String = _keyword
  @BeanProperty var pic: String = _pic
  @BeanProperty var price: String = _price
  @BeanProperty var licenseNum: String = _licenseNum
  @BeanProperty var healthPermitNum: String = _healthPermitNum
  @BeanProperty var netContent: String = _netContent
  @BeanProperty var company: String = _company
  @BeanProperty var expirationDate: String = _expirationDate
  @BeanProperty var insertDate: String = DateUtil.formatDateTime(_insertDate)

  def this(_barcode: String, _name: String, _insertDate: Date) = {
    this(_barcode, _name, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", _insertDate)
  }
}
