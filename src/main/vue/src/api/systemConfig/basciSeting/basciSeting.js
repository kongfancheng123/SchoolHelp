// 引入配置中导出的对象
import { instance } from '@/axiosConfig.js'

// 展示配置信息
let getShowData = {
  r: params => {
    return instance.post('/ParamsConfig/showParamsConfig', params)
  }
}

// 保存修改数据
let saveDta = {
  r: params => {
    return instance.post('/ParamsConfig/updateOrAddParamsConfig', params)
  }
}

export { getShowData, saveDta }
