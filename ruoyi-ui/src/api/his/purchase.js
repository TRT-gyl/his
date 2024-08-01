import request from '@/utils/request'

// 查询批次库存及价格
export function listPurchases(query) {
  return request({
    url: '/his/purchase/hisPurchaseList',
    method: 'get',
    params: query
  })
}

// 查询药品采购列表
export function listPurchase(query) {
  return request({
    url: '/his/purchase/list',
    method: 'get',
    params: query
  })
}

// 查询药品采购详细
export function getPurchase(id) {
  return request({
    url: '/his/purchase/' + id,
    method: 'get'
  })
}

// 新增药品采购
export function addPurchase(data) {
  return request({
    url: '/his/warehousing',
    method: 'post',
    data: data
  })
}

// 修改药品采购
export function updatePurchase(data) {
  return request({
    url: '/his/purchase',
    method: 'put',
    data: data
  })
}

// 删除药品采购
export function delPurchase(id) {
  return request({
    url: '/his/purchase/' + id,
    method: 'delete'
  })
}


//--------------------增加药品

// 查询药品列表
export function listMedicine(query) {
  return request({
    url: '/his/medicine/list',
    method: 'get',
    params: query
  })
}

// 查询药品详细
export function getMedicine(medicineId) {
  return request({
    url: '/his/medicine/' + medicineId,
    method: 'get'
  })
}

// 新增药品
export function addMedicine(data) {
  return request({
    url: '/his/medicine',
    method: 'post',
    data: data
  })
}

// 修改药品
export function updateMedicine(data) {
  return request({
    url: '/his/medicine',
    method: 'put',
    data: data
  })
}

// 删除药品
export function delMedicine(medicineId) {
  return request({
    url: '/his/medicine/' + medicineId,
    method: 'delete'
  })
}
