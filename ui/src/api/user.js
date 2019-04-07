import request from '@/utils/request'
//获取自己用户的
export function getUser() {
  return request({
    url: '/user/info',
    method: 'POST',
  })
}

export function addUser(user) {
  return request({
    url: '/user/save',
    method: 'POST',
    data: user
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

export function getAccount() {
  return request({
    url: '/user/getAccount',
    method: 'GET'
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


