import request from '@/utils/request'

// 查询就诊列表
export function listTreat(query) {
  return request({
    url: '/his/treat/list',
    method: 'get',
    params: query
  })
}

// 查询就诊详细
export function getTreat(treatId) {
  return request({
    url: '/his/treat' + treatId,
    method: 'get'
  })
}

// 根据挂号Id查询就诊详细
export function getTreatByRegId(registrationId) {
  return request({
    url: '/his/treat/re/' + registrationId,
    method: 'get'
  })
}
// 新增就诊
export function addTreat(data) {
  return request({
    url: '/his/treat',
    method: 'post',
    data: data
  })
}

// 修改就诊
export function updateTreat(data) {
  return request({
    url: '/his/treat',
    method: 'put',
    data: data
  })
}

// 删除就诊
export function delTreat(treatId) {
  return request({
    url: '/his/treat/' + treatId,
    method: 'delete'
  })
}
