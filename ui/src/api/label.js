import request from '@/utils/request'
// 查询Label列表
export function getList() {
  return request({
    url: '/label/list',
    method: 'GET'
  })
}

// 查询用户是否是版主
export function getManager(labelId) {
  return request ({
    url: '/label/manage/confirm/' + labelId,
    method: 'GET'
  })
}
