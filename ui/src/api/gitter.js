import request from '@/utils/request'

// 查询trending列表
export function getReposTrendingList(type, language, since) {
  return request({
    url: '/gitter/trending?type=' + type + '&language=' + language + '&since=' + since,
    method: 'GET'
  })
}
