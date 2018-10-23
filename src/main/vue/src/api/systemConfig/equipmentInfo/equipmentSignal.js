// 引入配置中导出的对象
import { instance } from '@/axiosConfig.js'

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

// 分页
let pageData = {
  r: params => {
    return instance.post('/MonitorProperty/showPageMonitorProperty', params)
  }
}

// 导出
let exportData = {
  r: params => {
    return instance.post(
      '/MonitorProperty/exportExcelMonitorProperty',
      params,
      {
        responseType: 'blob'
      }
    )
  }
}

export { addData, editData, deleteData, pageData, exportData }
