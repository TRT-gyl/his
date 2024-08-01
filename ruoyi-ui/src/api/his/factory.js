import request from '@/utils/request'

// 查询全部厂家
export function listFactory(query) {
  return request({
    url: '/his/factory/list',
    method: 'get',
    params: query
  })
}

// 查询厂家管理详细
export function getFactory(factoryId) {
  return request({
    url: '/his/factory/' + factoryId,
    method: 'get'
  })
}

// 新增厂家管理
export function addFactory(data) {
  return request({
    url: '/his/factory',
    method: 'post',
    data: data
  })
}

// 修改厂家管理
export function updateFactory(data) {
  return request({
    url: '/his/factory',
    method: 'put',
    data: data
  })
}

// 删除厂家管理
export function delFactory(factoryId) {
  return request({
    url: '/his/factory/' + factoryId,
    method: 'delete'
  })
}
