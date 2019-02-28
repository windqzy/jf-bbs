import request from '@/utils/request'

// 添加收藏记录
export function addCollection(postId) {
  return request({
    url: '/collection/update/' + postId,
    method: 'GET'
  })
}
