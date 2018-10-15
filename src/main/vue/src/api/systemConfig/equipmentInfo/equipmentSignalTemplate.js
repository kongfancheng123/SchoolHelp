// 引入配置中导出的对象
import { instance } from '@/axiosConfig.js'

// 弹窗：设备类型 =>下拉数据
let getEquipmentTypeList = {
  r: params => {
    return instance.post('/EquipmentType/showAllEquipmentType', params)
  }
}

// 获取所有
let getAllDataList = {
  r: params => {
    return instance.post(
      '/MonitorPropertyTemplate/showAllMonitorPropertyTemplate',
      params
    )
  }
}

// 新增
let addData = {
  r: params => {
    return instance.post(
      '/MonitorPropertyTemplate/addMonitorPropertyTemplate1',
      params
    )
  }
}

// 编辑
let editData = {
  r: params => {
    return instance.post(
      '/MonitorPropertyTemplate/updateMonitorPropertyTemplate1',
      params
    )
  }
}

// 删除
let deleteData = {
  r: params => {
    return instance.post(
      '/MonitorPropertyTemplate/deleteMonitorPropertyTemplate1',
      params
    )
  }
}

export { getEquipmentTypeList, getAllDataList, addData, editData, deleteData }
