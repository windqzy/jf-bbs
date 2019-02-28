import request from '@/utils/request'

export function getUser() {
  return request({
    url: '/user/info',
    method: 'POST',
  })
}


export function upDateUser(UserForm) {
  return request({
    url: '/user/update',
    method: 'POST',
    data: UserForm
  })
}

export function upLoadFile() {
  return request({
    url: '/upload/file',
    method: 'POST'
  })
}

export function updateUserIcon(url) {
  return request({
    url: '/user/update/icon',
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    data: url
  })
}
