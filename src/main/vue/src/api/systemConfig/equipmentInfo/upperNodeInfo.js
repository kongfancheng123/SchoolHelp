// 引入配置中导出的对象
import { instance } from '@/axiosConfig.js'

// 获取所有

// 新增
let addData = {
  r: params => {
    return instance.post('/ParentNode/addParentNode1', params)
  }
}

// 编辑
let editData = {
  r: params => {
    return instance.post('/ParentNode/updateParentNode1', params)
  }
}

// 删除
let deleteData = {
  r: params => {
    return instance.post('/ParentNode/deleteParentNode1', params)
  }
}

// 分页
let pageData = {
  r: params => {
    return instance.post('/ParentNode/showPageParentNode', params)
  }
}

// 导出
let exportData = {
  r: params => {
    return instance.post('/ParentNode/exportExcelParentNode', params, {
      responseType: 'blob'
    })
  }
}
export { pageData, addData, editData, deleteData, exportData }
