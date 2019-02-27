import request from '@/utils/request'

// 查询Label列表
export function getCurr() {
  return request({
    url: '/grade/rule/curr',
    method: 'GET'
  })
}

/**
 * 获取当前积分
 */
export function getGrade() {
  return request({
    url: '/grade/info',
    method: 'GET'
  })
}
