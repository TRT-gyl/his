import request from '@/utils/request'

// 查询挂号费用列表
export function listResCost(query) {
  return request({
    url: '/his/resCost/list',
    method: 'get',
    params: query
  })
}

// 查询挂号费用详细
export function getResCost(regId) {
  return request({
    url: '/his/resCost/' + regId,
    method: 'get'
  })
}

// 新增挂号费用
export function addResCost(data) {
  return request({
    url: '/his/resCost',
    method: 'post',
    data: data
  })
}

// 修改挂号费用
export function updateResCost(data) {
  return request({
    url: '/his/resCost',
    method: 'put',
    data: data
  })
}

// 删除挂号费用
export function delResCost(regId) {
  return request({
    url: '/his/resCost/' + regId,
    method: 'delete'
  })
}
