// 引入配置中导出的对象
import { instance } from '@/axiosConfig.js'

// 获取所有
let getAllDataList = {
  r: params => {
    return instance.post('/MonitorProperty/showAllMonitorProperty', params)
  }
}

// 新增
let addData = {
  r: params => {
    return instance.post('/MonitorProperty/addMonitorProperty1', params)
  }
}

// 编辑
let editData = {
  r: params => {
    return instance.post('/MonitorProperty/updateMonitorProperty1', params)
  }
}

// 删除
let deleteData = {
  r: params => {
    return instance.post('/MonitorProperty/deleteMonitorProperty1', params)
  }
}

export { getAllDataList, addData, editData, deleteData }
