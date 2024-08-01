import request from '@/utils/request'

// 查询挂号列表
export function listRegistration(query) {
  return request({
    url: '/his/registration/list',
    method: 'get',
    params: query
  })
}
// 查询能挂号的医生列表
export function listRegable(query) {
  return request({
    url: '/his/registration/listRegable',
    method: 'get',
    params: query
  })
}

// 查询科室、医生列表
export function listDept() {
    return request({
      url: '/his/registration/listdept',
      method: 'get' 
    })
  }

// 查询挂号详细
export function getRegistration(registrationId) {
  return request({
    url: '/his/registration/' + registrationId,
    method: 'get'
  })
}

// 查询挂号详细带出就诊信息
export function getRegAndTreat(registrationId) {
  return request({
    url: '/his/registration/andtreatno/' + registrationId,
    method: 'get'
  })
}

// 新增挂号
export function addRegistration(data) {
  return request({
    url: '/his/registration',
    method: 'post',
    data: data
  })
}

// 修改挂号
export function updateRegistration(data) {
  return request({
    url: '/his/registration',
    method: 'put',
    data: data
  })
}

// 删除挂号
export function delRegistration(registrationId) {
  return request({
    url: '/his/registration/' + registrationId,
    method: 'delete'
  })
}
