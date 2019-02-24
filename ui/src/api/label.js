import request from '@/utils/request'
// 查询Label列表
export function getList() {
  return request({
    url: '/label/list',
    method: 'GET'
  })
}
