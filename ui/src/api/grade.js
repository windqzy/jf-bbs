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

/**
 * 修改积分
 */
export function update(newGrade) {
  return request({
    url: '/grade/update',
    method: 'POST',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    data: newGrade
  })
}


