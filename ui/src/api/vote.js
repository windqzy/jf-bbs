import request from '@/utils/request'

//获取自己用户的
export function getList(postId) {
  return request({
    url: '/vote/' + postId,
    method: 'GET',
  })
}
