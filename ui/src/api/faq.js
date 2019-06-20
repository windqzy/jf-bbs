import request from '@/utils/request'

// 查询FAQ种类列表
export function getFaq() {
  return request({
    url: '/faq/list',
    method: 'GET'
  })
}

// FAQ问题列表
export function getFaqList(typeId) {
  return request({
    url: '/faq/faqList?typeId='+typeId,
    method: 'GET'
  })
}
