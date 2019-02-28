import request from '@/utils/request'

// 查询文章列表
export function getList(obj) {
  return request({
    url: '/posts/list?currPage=' + obj.currPage + '&pageSize=' + obj.pageSize + '&sortType=' + obj.sortType + '&postType=' +
    obj.postType + '&labelId=' + obj.labelId,
    method: 'GET'
  })
}

// 查询置顶文章
export function getTop() {
  return request({
    url: '/posts/top',
    method: 'GET'
  })
}

// 查询文章详情
export function getDetail(postId) {
  return request({
    url: '/posts/detail/' + postId,
    method: 'GET'
  })
}

// 查询个人发布文章列表
export function getPersonList() {
  return request({
    url: '/posts/personList',
    method: 'GET'
  })
}


// 发布文章
export function publish(bbsPosts) {
  return request({
    url: '/posts/save',
    method: 'POST',
    data: bbsPosts
  })
}
