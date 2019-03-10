import request from '@/utils/request'
// 查询Tag列表
export function getList(labelId) {
  return request({
    url: '/tag/list/' + labelId,
    method: 'GET'
  })
}
