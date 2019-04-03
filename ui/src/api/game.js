import request from '@/utils/request'

export function getList() {
  return request({
    url: '/game/list/',
    method: 'GET'
  })
}
