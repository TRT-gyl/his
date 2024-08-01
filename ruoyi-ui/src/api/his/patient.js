import request from '@/utils/request'

// 查询患者列表
export function listPatient(query) {
  return request({
    url: '/his/patient/list',
    method: 'get',
    params: query
  })
}

// 查询患者详细
export function getPatient(patientId) {
  return request({
    url: '/his/patient/' + patientId,
    method: 'get'
  })
}

// 根据身份证查询患者详细
export function getPatientByidCard(idCard) {
  return request({
    url: '/his/patient/' + idCard,
    method: 'post'
  })
}


// 新增患者
export function addPatient(data) {
  return request({
    url: '/his/patient',
    method: 'post',
    data: data
  })
}

// 修改患者
export function updatePatient(data) {
  return request({
    url: '/his/patient',
    method: 'put',
    data: data
  })
}

// 删除患者
export function delPatient(patientId) {
  return request({
    url: '/his/patient/' + patientId,
    method: 'delete'
  })
}
