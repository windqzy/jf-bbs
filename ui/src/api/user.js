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

