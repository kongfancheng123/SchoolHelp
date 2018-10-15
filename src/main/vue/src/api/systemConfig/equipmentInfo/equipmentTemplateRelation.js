// 引入配置中导出的对象
import { instance } from '@/axiosConfig.js'

// 弹窗：设备类型 =>下拉数据
let getEquipmentProtoList = {
  r: params => {
    return instance.post('/MonitorProperty/showAllMonitorProperty', params)
  }
}

// 树
let getTreeDataList = {
  r: params => {
    return instance.post(
      '/MonitorPropertyTemplateBind/getEquipmentTypeTemplateLink',
      params
    )
  }
}

// 获取所有
let getAllDataList = {
  r: params => {
    return instance.post(
      '/MonitorPropertyTemplateBind/showAllMonitorPropertyTemplateBind',
      params
    )
  }
}

// 新增
let addData = {
  r: params => {
    return instance.post(
      '/MonitorPropertyTemplateBind/addMonitorPropertyTemplateBind1',
      params
    )
  }
}

// 编辑
let editData = {
  r: params => {
    return instance.post(
      '/MonitorPropertyTemplateBind/updateMonitorPropertyTemplateBind1',
      params
    )
  }
}

// 删除
let deleteData = {
  r: params => {
    return instance.post(
      '/MonitorPropertyTemplateBind/deleteMonitorPropertyTemplateBind1',
      params
    )
  }
}

export {
  getEquipmentProtoList,
  getTreeDataList,
  getAllDataList,
  addData,
  editData,
  deleteData
}
