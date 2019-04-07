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
export function getGrade(id) {
  return request({
    url: '/grade/info?userId=' + id,
    method: 'GET'
  })
}

export function reward(fromId, toId, grade) {
  return request({
    url: '/grade/reward?fromId=' + fromId + '&toId=' + toId + '&grade=' + grade,
    method: 'GET'
  })
}

export function getTopThree(postsId){
  return request({
    url: '/grade/finTopThree/?postsId='+postsId,
    method:'GET'
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


