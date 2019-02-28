import request from '@/utils/request'

// 查询连续签到次数
export function getCount() {
  return request({
    url: '/sign/count/',
    method: 'GET'
  })
}

// 查询该用户今日是否签到
export function getBool() {
  return request({
    url: '/sign/bool/',
    method: 'GET'
  })
}

// 点击进行签到
export function saveSign() {
  return request({
    url: '/sign/save/',
    method: 'GET'
  })
}

//查询签到榜
export function listSign(listType) {
  return request({
    url: '/sign/list/' + listType,
    method: 'GET'
  })
}
