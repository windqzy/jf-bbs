import request from '@/utils/request'

//获取自己用户的
export function getList(type, params) {
  return request({
    url: '/hot/list?type=' + type + '&start=' + params.start + '&size=' + params.size + '&arctype=' + params.arctype,
    method: 'GET',
  })
}

export function getInfo(type, articleId) {
  return request({
    url: '/hot/info?type=' + type + '&articleId=' + articleId,
    method: 'GET',
  })
}

export function getHealthCN72() {
  return request({
    url: '/hot/getHealthCN72',
    method: 'GET',
  })
}

export function getHealthCN20() {
  return request({
    url: '/hot/getHealthCN20',
    method: 'GET',
  })
}
