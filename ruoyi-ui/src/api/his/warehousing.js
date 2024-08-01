import request from '@/utils/request'

// 查询采购入库列表
export function listWarehousing(query) {
  return request({
    url: '/his/warehousing/list',
    method: 'get',
    params: query
  })
}

// 查询供应商列表
export function listHisSupplie() {
  return request({
    url: '/his/warehousing/hisSupplierList',
    method: 'get'
  })
}

// 查询药品列表
export function listHisMedicine(query) {
  return request({
    url: '/his/warehousing/hisMedicineList',
    method: 'get',
    params: query
  })
}

// 查询采购入库详细
export function getWarehousing(id) {
  return request({
    url: '/his/warehousing/' + id,
    method: 'get'
  })
}

// 新增采购入库
export function addWarehousing(data) {
  return request({
    url: '/his/warehousing',
    method: 'post',
    data: data
  })
}

// 修改采购入库
export function updateWarehousing(data) {
  return request({
    url: '/his/warehousing',
    method: 'put',
    data: data
  })
}

// 修改采购入库
export function updateWarehousings(data) {
  return request({
    url: '/his/warehousing/updateOrder',
    method: 'put',
    data: data
  })
}

// 删除采购入库
export function delWarehousing(id) {
  return request({
    url: '/his/warehousing/' + id,
    method: 'delete'
  })
}
