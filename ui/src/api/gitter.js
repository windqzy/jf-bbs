import request from '@/utils/request'

// 查询trending列表
export function getReposTrendingList(type, language, since) {
  return request({
    url: '/gitter/trending?type=' + type + '&language=' + language + '&since=' + since,
    method: 'GET'
  })
}

export function getCoderDailyDate() {
  return request({
    url: '/hot/getCoderDailyDate',
    method: 'GET'
  })
}

export function getCoderDailyInfo(date) {
  return request({
    url: '/hot/getCoderDailyInfo?date=' + date,
    method: 'GET'
  })
}
