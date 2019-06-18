import request from '@/utils/request'

// 查询文章最新回复列表
export function getList(postId) {
  return request({
    url: '/reply/list/' + postId,
    method: 'GET'
  })
}

// 查询文章热门回复列表
export function getHotList(postId) {
  return request({
    url: '/reply/hotList/' + postId,
    method: 'GET'
  })
}

// 查询周回复榜
export function getTop(obj) {
  return request({
    url: '/reply/top?beginTime=' + obj.beginTime + '&endTime=' + obj.endTime,
    method: 'GET'
  })
}

// 添加回复
export function addReply(ReplyForm) {
  return request({
    url: '/reply/save/',
    method: 'POST',
    data: ReplyForm
  })
}

// 添加回复
export function updateReply(ReplyForm) {
  return request({
    url: '/reply/update/',
    method: 'POST',
    data: ReplyForm
  })
}

// 点赞
export function replyUp(replyId) {
  return request({
    url: '/up/update/' + replyId,
    method: 'get'
  })
}

// 删除评论
export function delReply(replyId) {
  return request({
    url: '/reply/delete/' + replyId,
    method: 'get'
  })
}


// 个人文章回复列表
export function getPersonReplyList(userId) {
  return request({
    url: '/reply/personReply?userId=' + userId,
    method: 'GET'
  })
}


//采纳回复
export function acceptReply(replyId, userId) {
  return request({
    url: '/reply/acceptReply?id=' + replyId + '&userId=' + userId,
    method: 'GET',
  })
}


/******************** 回复API 2.0 *******************/
/**
 * 查询文章回复列表
 * @param postId
 */
export function getReplyList(postId) {
  return request({
    url: `/reply/posts/${postId}`,
    method: 'GET',
  })
}

/**
 * 查询文章回复列表
 * @param data
 */
export function add(data) {
  return request({
    url: `/reply/add`,
    method: 'POST',
    data: data
  })
}
