// 引入配置中导出的对象
import { instance } from '@/axiosConfig.js'

// 获取所有表格数据
let getTabData = {
  r: params => {
    return instance.post('/EquipmentInfo/showAllEquipmentInfo', params)
  }
}

// 过滤：上层节点
let getUpNodeData = {
  r: params => {
    return instance.post('/ParentNode/showAllParentNode', params)
  }
}

//  过滤：设备类型
let getEquipmentTypeData = {
  r: params => {
    return instance.post('/EquipmentType/showAllEquipmentType', params)
  }
}

//  过滤：设备监控信号模板
let getEquipmentSignalTemplateData = {
  r: params => {
    return instance.post(
      '/MonitorPropertyTemplate/showAllMonitorPropertyTemplate',
      params
    )
  }
}

//  过滤：设备监控信号
let getEquipmentSignalData = {
  r: params => {
    return instance.post('/MonitorProperty/showAllMonitorProperty', params)
  }
}

//  过滤：搜索结果
let getSearchData = {
  r: params => {
    return instance.post('/EquipmentInfo/showEquipmentInfoByCondition', params)
  }
}

//  开始发送:根据模板,设备类型和上层节点获取属性
let getSendTableData = {
  r: params => {
    return instance.post(
      '/EquipmentInfo/getPropertyByTypeAndTemplateAndParentNode',
      params
    )
  }
}
// 使能判断
let getEnergyData = {
  r: params => {
    return instance.post('/ParamsConfig/getParamsConfig', params)
  }
}
// 发送报警
let sendWarnData = {
  r: params => {
    return instance.post('/EquipmentInfo/sendEventHistory', params)
  }
}

// 解除故障
let troublesHootingData = {
  r: params => {
    return instance.post('/EquipmentInfo/dealEventHistory', params)
  }
}

//  开始发送
let sendData = {
  r: (params, cancelToke) => {
    return instance.post(
      '/EquipmentInfo/sendEquipmentRealtimeData',
      params,
      cancelToke
    )
  }
}

// 停止发送
let stopSendData = {
  r: params => {
    return instance.post('/EquipmentInfo/stopSendEquipmentRealtimeData', params)
  }
}

// 停止开始发送开关
let switchData = {
  r: params => {
    return instance.post('/EquipmentInfo/getSendControlVal', params)
  }
}
export {
  getTabData,
  getUpNodeData,
  getEquipmentTypeData,
  getEquipmentSignalTemplateData,
  getEquipmentSignalData,
  getSearchData,
  getSendTableData,
  sendData,
  getEnergyData,
  sendWarnData,
  troublesHootingData,
  stopSendData,
  switchData
}
