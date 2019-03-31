import request from '@/utils/request'

// 获取马甲列表
export function getList() {
  return request({
    url: '/vest/list',
    method: 'GET'
  })
}

// 添加马甲列表
export function add(vest) {
  return request({
    url: '/vest/add?vest=' + vest,
    method: 'GET'
  })
}
