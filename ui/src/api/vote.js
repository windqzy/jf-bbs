import request from '@/utils/request'

//获取自己用户的
export function getList(postId) {
  return request({
    url: '/vote/' + postId,
    method: 'GET',
  })
}

// 添加投票信息
export function addVote(bbsVote) {
  return request({
    url: '/vote/add',
    method: 'POST',
    data: bbsVote
  })
}

// 查询投票选项
export function userVote(voteArr) {
  return request({
    url: '/vote/record?options=' + voteArr,
    method: 'GET'
  })
}

// 查询用户是否对当前投票贴投票
export function isVoted(postId) {
  return request({
    url: '/vote/isVote/' + postId,
    method: 'GET'
  })
}
