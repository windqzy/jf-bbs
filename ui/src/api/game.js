import request from '@/utils/request'

export function getList() {
  return request({
    url: '/game/list/',
    method: 'GET'
  })
}

export function getSumRankList() {
  return request({
    url: '/game/rank/leader',
    method: 'GET'
  })
}

export function getRankList(gameId) {
  return request({
    url: '/game/rank/list?gameId=' + gameId,
    method: 'GET'
  })
}
