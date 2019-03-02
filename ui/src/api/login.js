import request from '@/utils/request'
import axios from 'axios'

// 查询Label列表

// export function getAccessToken() {
//   return axios({
//     url: '/api/sns/gettoken?appid=' + window.localStorage.DD_APPID + '&appsecret=' +
//     window.localStorage.DD_APPSECRET,
//     method: 'GET',
//   })
// }

export function getAccessToken() {
  return request({
    url: '/login/getUserFromDingDing',
    method: 'GET',
  })
}



export function getCompanyToken() {
  return axios({
    url: '/api/gettoken?corpid=' + window.localStorage.DD_CORPID + '&corpsecret='
    + window.localStorage.DD_CORPSECRET,
    method: 'GET',
  })
}

export function getUnionId(accessToken, loginTempCode) {
  return axios({
    url: '/api/sns/get_persistent_code?access_token=' + accessToken,
    method: 'POST',
    data: loginTempCode
  })
}

export function getUserId(companyToken, unionId) {
  return axios({
    url: '/api/user/getUseridByUnionid?access_token=' + companyToken +
    '&unionid=' + unionId,
    method: 'GET',
  })
}

export function getUser(companyToken, userId) {
  return axios({
    url: '/api/user/get?access_token=' + companyToken +
    '&userid=' + userId,
    method: 'GET',
  })
}

export function addUser(LoginForm) {
  return request({
    url: '/login',
    method: 'POST',
    data: LoginForm
  })
}
