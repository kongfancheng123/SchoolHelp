import Vue from 'vue'
import axios from 'axios'

// 创建实例，并配置
const instance = axios.create({
  // baseURL: 'http://192.168.52.50:8080',
  baseURL: '',
  timeout: 100000,
  responseType: 'json',
  headers: {
    username: 'atm8000',
    password: 'agioe123456',
    'content-type': 'application/json'
  }
})
// 将axios实例，注册在Vue原型上的某个属性
Vue.prototype.$AxiosFn = axios

export { instance }
