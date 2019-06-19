import {getUser} from '@/api/user'

const user = {
  state: {
    icon:'',
    username: '',
    name: '',
    sex: '',
    signature: '',
    email: '',
    position: '',
    mobile: '',
    id: '',
    initTime: '',
    city: '',
    isAdmin: '',
    enable: '',
    unionId: '',
    background: '',
    onLineUserCount:0 , // 在线人数
  },
  actions: {
    addUserInfo({commit}) {
      return new Promise(resolve => {
        getUser().then(res => {
          // console.log(res.data)
          commit('USER_INFO', res.data);
          resolve(res.data)
        })
      })
    }
  },
  mutations: {
    USER_INFO: (state, data) => {
      state.username = data.username;
      state.icon = data.icon;
      state.name = data.name;
      state.sex = data.sex;
      state.signature = data.signature;
      state.email = data.email;
      state.position = data.position;
      state.mobile = data.mobile;
      state.id = data.id;
      state.initTime = data.initTime;
      state.city = data.city;
      state.isAdmin = data.admin;
      state.enable = data.enable;
      state.unionId = data.unionId;
      state.background = data.background;
    },
    ONLINEUSERCOUNT:(state,data) => {
      state.onLineUserCount = data;
    }
  }
}
export default user
