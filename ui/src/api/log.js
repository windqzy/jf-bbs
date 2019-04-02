import request from '@/utils/request'
// 查询Label列表
export function getList(type) {
  return request({
    url: '/log/list/' + type,
    method: 'GET'
  })
}
