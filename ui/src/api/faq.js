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
// 修改或新增FAQ
export function update(faqEntity) {
  return request({
    url: '/faq/update',
    method: 'POST',
    data: faqEntity
  })
}

// 判断有用没用
export function good(faqId,isGood) {
  return request({
    url: '/faq/good?faqId='+ faqId + '&isGood=' + isGood,
    method: 'GET'
  })
}
