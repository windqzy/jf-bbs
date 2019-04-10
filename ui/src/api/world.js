import request from '@/utils/request'

//获取自己用户的
export function getList(type, params) {
  return request({
    url: '/hot/list?type=' + type + '&start=' + params.start + '&size=' + params.size + '&arctype=' + params.arctype,
    method: 'GET',
  })
}
