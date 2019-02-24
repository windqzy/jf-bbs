import request from '@/utils/request'

// 查询文章详情
export function getList(postId) {
  return request({
    url: '/reply/list/' + postId,
    method: 'GET'
  })
}
