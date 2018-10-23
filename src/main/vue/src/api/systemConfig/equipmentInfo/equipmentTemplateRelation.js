// 引入配置中导出的对象
import { instance } from '@/axiosConfig.js'

// 弹窗：设备属性 =>下拉数据
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

// 分页
let pageData = {
  r: params => {
    return instance.post(
      '/MonitorPropertyTemplateBind/showPageMonitorPropertyTemplateBind',
      params
    )
  }
}

// 导出
let exportData = {
  r: params => {
    return instance.post(
      '/MonitorPropertyTemplateBind/exportExcelMonitorPropertyTemplateBind',
      params,
      {
        responseType: 'blob'
      }
    )
  }
}

export {
  getEquipmentProtoList,
  getTreeDataList,
  pageData,
  addData,
  editData,
  deleteData,
  exportData
}
