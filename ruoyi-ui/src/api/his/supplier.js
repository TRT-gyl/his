import request from '@/utils/request'

// 查询全部供应商
export function listSupplier(query) {
  return request({
    url: '/his/supplier/list',
    method: 'get',
    params: query
  })
}

// 查询供应商管理详细
export function getSupplier(supplierId) {
  return request({
    url: '/his/supplier/' + supplierId,
    method: 'get'
  })
}

// 新增供应商管理
export function addSupplier(data) {
  return request({
    url: '/his/supplier',
    method: 'post',
    data: data
  })
}

// 修改供应商管理
export function updateSupplier(data) {
  return request({
    url: '/his/supplier',
    method: 'put',
    data: data
  })
}

// 删除供应商管理
export function delSupplier(supplierId) {
  return request({
    url: '/his/supplier/' + supplierId,
    method: 'delete'
  })
}
