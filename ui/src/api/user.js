import request from '@/utils/request'
//获取自己用户的
export function getUser() {
  return request({
    url: '/user/info',
    method: 'POST',
  })
}

//获取传入ID的用户的
export function getOther(userId) {
  return request({
    url: '/user/infoByUserId',
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    data: userId
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


