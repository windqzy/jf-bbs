import {getUser} from '@/api/user'

const user = {
  state: {
    username: '',
    name: '',
    sex: '',
    signature: '',
    email: '',
    position: '',
    mobile: ''
  },
  actions: {
    addUserInfo({commit}) {
      getUser().then(res => {
        commit('USER_INFO', res.data);
      })
    }
  },
  mutations: {
    USER_INFO: (state, data) => {
      state.username = data.username;
      state.name = data.name;
      state.sex = data.sex;
      state.signature = data.signature;
      state.email = data.email;
      state.position = data.position;
      state.mobile = data.mobile;
    }
  }
}
export default user
