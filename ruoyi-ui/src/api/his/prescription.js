import request from '@/utils/request'

// 查询处方药品列表
export function listPrescription(query) {
  return request({
    url: '/his/prescription/list',
    method: 'get',
    params: query
  })
}

// 查询处方药品详细
export function getPrescription(prescriptionId) {
  return request({
    url: '/his/prescription/' + prescriptionId,
    method: 'get'
  })
}

// 新增处方药品
export function addPrescription(data) {
  return request({
    url: '/his/prescription',
    method: 'post',
    data: data
  })
}

// 修改处方药品
export function updatePrescription(data) {
  return request({
    url: '/his/prescription',
    method: 'put',
    data: data
  })
}

// 删除处方药品
export function delPrescription(prescriptionId) {
  return request({
    url: '/his/prescription/' + prescriptionId,
    method: 'delete'
  })
}
