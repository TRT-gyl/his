import request from '@/utils/request'

// 查询检查费用设置列表
export function listInspect(query) {
  return request({
    url: '/his/inspect/list',
    method: 'get',
    params: query
  })
}

// 查询检查费用设置详细
export function getInspect(inspectId) {
  return request({
    url: '/his/inspect/' + inspectId,
    method: 'get'
  })
}

// 新增检查费用设置
export function addInspect(data) {
  return request({
    url: '/his/inspect',
    method: 'post',
    data: data
  })
}

// 修改检查费用设置
export function updateInspect(data) {
  return request({
    url: '/his/inspect',
    method: 'put',
    data: data
  })
}

// 删除检查费用设置
export function delInspect(inspectId) {
  return request({
    url: '/his/inspect/' + inspectId,
    method: 'delete'
  })
}
