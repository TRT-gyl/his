import request from '@/utils/request'

// 查询全部药品管理
export function listMedicine(query) {
  return request({
    url: '/his/medicine/list',
    method: 'get',
    params: query
  })
}

// 查询药品管理详细
export function getMedicine(medicineId) {
  return request({
    url: '/his/medicine/' + medicineId,
    method: 'get'
  })
}

// 新增药品管理
export function addMedicine(data) {
  return request({
    url: '/his/medicine',
    method: 'post',
    data: data
  })
}

// 修改药品管理
export function updateMedicine(data) {
  return request({
    url: '/his/medicine',
    method: 'put',
    data: data
  })
}

// 删除药品管理
export function delMedicine(medicineId) {
  return request({
    url: '/his/medicine/' + medicineId,
    method: 'delete'
  })
}
