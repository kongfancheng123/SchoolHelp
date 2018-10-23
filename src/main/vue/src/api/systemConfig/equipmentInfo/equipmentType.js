// 引入配置中导出的对象
import { instance } from '@/axiosConfig.js'

// 获取所有
// let getAllDataList = {
//   r: params => {
//     return instance.post('/EquipmentType/showAllEquipmentType', params)
//   }
// }

// 新增
let addData = {
  r: params => {
    return instance.post('/EquipmentType/addEquipmentType1', params)
  }
}

// 编辑
let editData = {
  r: params => {
    return instance.post('/EquipmentType/updateEquipmentType1', params)
  }
}

// 删除
let deleteData = {
  r: params => {
    return instance.post('/EquipmentType/deleteEquipmentType1', params)
  }
}

// 分页
let pageData = {
  r: params => {
    return instance.post('/EquipmentType/showPageEquipmentType', params)
  }
}

// 导出
let exportData = {
  r: params => {
    return instance.post('/EquipmentType/exportExcelEquipmentType', params, {
      responseType: 'blob'
    })
  }
}

export { addData, editData, deleteData, pageData, exportData }
