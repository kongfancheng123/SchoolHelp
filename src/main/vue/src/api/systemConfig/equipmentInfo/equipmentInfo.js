// 引入配置中导出的对象
import { instance } from '@/axiosConfig.js'

// 弹层：设备类型
let getEquipmentType = {
  r: params => {
    return instance.post('/EquipmentType/showAllEquipmentType', params)
  }
}

// 弹层：设备信号模板
let getEquipmentSignalTemplate = {
  r: params => {
    return instance.post(
      '/MonitorPropertyTemplate/showAllMonitorPropertyTemplate',
      params
    )
  }
}

// 弹层：设备上层节点
let getUpNode = {
  r: params => {
    return instance.post('/ParentNode/showAllParentNode', params)
  }
}

// 弹层：监控信号
let getMonitoringSignalData = {
  r: params => {
    return instance.post('/EquipmentInfo/getPropertyByTypeAndTemplate', params)
  }
}

// 弹层：搜索中监控信号
let getSearchMonitoringSignalData = {
  r: params => {
    return instance.post('/MonitorProperty/showAllMonitorProperty', params)
  }
}

// 弹层：搜索结果
let getSearchData = {
  r: params => {
    return instance.post('/EquipmentInfo/showEquipmentInfoByCondition', params)
  }
}

// 设置Key配置
let keyData = {
  r: params => {
    return instance.post('/EquipmentInfo/createKeyWord', params)
  }
}

// 获取表格所有
let getAllDataList = {
  r: params => {
    return instance.post('/EquipmentInfo/showAllEquipmentInfo', params)
  }
}

// 新增
let addData = {
  r: params => {
    return instance.post('/EquipmentInfo/addEquipmentInfo1', params)
  }
}

// 编辑
let editData = {
  r: params => {
    return instance.post('/EquipmentInfo/updateEquipmentInfo1', params)
  }
}

// 删除
let deleteData = {
  r: params => {
    return instance.post('/EquipmentInfo/deleteEquipmentInfo1', params)
  }
}

// 导出
let exportData = {
  r: params => {
    return instance.post('/EquipmentInfo/exportExcelEquipmentInfo', params, {
      responseType: 'blob'
    })
  }
}

export {
  getEquipmentType,
  getEquipmentSignalTemplate,
  getUpNode,
  getMonitoringSignalData,
  getSearchData,
  getSearchMonitoringSignalData,
  keyData,
  getAllDataList,
  addData,
  editData,
  deleteData,
  exportData
}
