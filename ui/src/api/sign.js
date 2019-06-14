import request from '@/utils/request'

// 查询连续签到次数
export function getSignCount() {
  return request({
    url: '/sign/count/',
    method: 'GET'
  })
}

// 查询该用户今日是否签到
export function isSign() {
  return request({
    url: '/sign/bool/',
    method: 'GET'
  })
}

// 点击进行签到
export function save() {
  return request({
    url: '/sign/save/',
    method: 'GET'
  })
}

//查询签到榜
export function list(listType) {
  return request({
    url: '/sign/list/' + listType,
    method: 'GET'
  })
}
