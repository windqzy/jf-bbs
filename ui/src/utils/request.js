import axios from 'axios'
// 创建axios实例
const service = axios.create({
  baseURL: window.localStorage.baseUrl, // api的base_url
  timeout: 15000 // 请求超时时间
})

axios.defaults.withCredentials = true;
// request拦截器
service.interceptors.request.use(config => {
  if (window.localStorage['B-Token']) {
    config.headers['B-Token'] = window.localStorage['B-Token'] // 让每个请求携带自定义token 请根据实际情况自行修改
  }
  // if (store.getters.token) {
  //   // config.headers['X-Token'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
  // }
  //设置请求头携带cookie、跨域session
  config.withCredentials = true;
  return config
}, error => {
  // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
  response => {

    const res = response.data

    if (res.status == 'failure') {
      // Message({
      //   message: res.message,
      //   type: 'error',
      //   duration: 5 * 1000
      // })
    } else {
      return response.data
    }
  },
  error => {
    console.log('err' + error) // for debug
    // Message({
    //   message: error.message,
    //   type: 'error',
    //   duration: 5 * 1000
    // })
    return Promise.reject(error)
  }
)
export default service
