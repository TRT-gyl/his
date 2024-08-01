import request from '@/utils/request'

// 查询药品销售记录
export function listMedicineSaleRecords(query) {
  return request({
    url: '/his/statistic/medicine/saleRecords',
    method: 'get',
    params: query
  })
}
// 查询药品销售统计记录
export function listMedicineStatisticRecords(query) {
    return request({
      url: '/his/statistic/medicine/statisticRecords',
      method: 'get',
      params: query
    })
  }

  // 得到收入的汇总信息
  export function calculateRevenuSummary(query) {
    return request({
      url: '/his/statistic/revenu/summary',
      method: 'get',
      params: query
    })
  }
  export function getNetGainsTrend(query) {
    return request({
      url: '/his/statistic/revenu/trend',
      method: 'get',
      params: query
    })
  }

  export function listExamineRecords(query) {
    return request({
      url: '/his/statistic/examine/examineRecords',
      method: 'get',
      params: query
    })
  }
  export function listExamineStatisticRecords(query) {
    return request({
      url: '/his/statistic/examine/statisticRecords',
      method: 'get',
      params: query
    })
  }
  
  export function listRegistrationRecords(query) {
    return request({
      url: '/his/statistic/registration/registrationRecords',
      method: 'get',
      params: query
    })
  }
  export function listRegistrationStatisticRecords(query) {
    return request({
      url: '/his/statistic/registration/statisticRecords',
      method: 'get',
      params: query
    })
  }