import request from '@/utils/request'

// 添加收藏记录
export function addCollection(postId) {
  return request({
    url: '/collection/update/' + postId,
    method: 'GET'
  })
}

// 判断是否收藏
export function isColl(postId) {
  return request({
    url: '/collection/isColl/' + postId,
    method: 'GET'
  })
}
