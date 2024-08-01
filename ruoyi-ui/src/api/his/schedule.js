import request from '@/utils/request'

// 查询排班表视图列表
export function listSchedule(query) {
  return request({
    url: '/his/schedule/list',
    method: 'get',
    params: query
  })
}



// 查询个人排班表
export function getSchedule(query) {
  return request({
    url: '/his/schedule/selflist',
    method: 'get',
    params: query
  })
}


// 修改排班表视图
export function updateSchedule(data) {
  return request({
    url: '/his/schedule',
    method: 'put',
    data: data
  })
}

// 查询排班表视图详细(收费页面需要)
export function getScheduleById(scheduleId) {
  return request({
    url: '/his/schedule/' + scheduleId,
    method: 'get'
  })
}

