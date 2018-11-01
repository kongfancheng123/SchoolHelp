<template>
  <div class="flex">
    <el-aside width="220px">
      <el-tree :data="treeData"
               accordion
               :props="defaultProps"
               :render-content="renderContent"
               @node-click="treeNodeClick"
               node-key="code"
               :default-expanded-keys="[treeExpandedKey]"
               :default-checked-keys="[treeCheckedKey]"
               id="tree"></el-tree>
    </el-aside>

    <main>
      <!-- tagTip -->
      <div class="tagTip">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item class="title">信号模板关联配置</el-breadcrumb-item>
        </el-breadcrumb>
        <el-button type="primary"
                   size="mini"
                   @click="addEvent('formAdd')">创建</el-button>
        <el-button size="mini"
                   type="info">导 入</el-button>
        <el-button size="mini"
                   @click="exportEvent">导 出</el-button>
      </div>

      <!--表格 -->
      <el-table :data="tableData"
                border
                style="width: 100%">
        <el-table-column type="index">
        </el-table-column>
        <el-table-column prop="equipmentTypeName"
                         label="设备类型"
                         width="180">
        </el-table-column>
        <el-table-column prop="equipmentPropertyTemplateName"
                         label="模板名称">
        </el-table-column>
        <el-table-column prop="equipmentPropertyCode"
                         label="属性编码">
        </el-table-column>
        <el-table-column prop="equipmentPropertyName"
                         label="属性名称">
        </el-table-column>
        <el-table-column prop="equipmentPropertyTypeName"
                         label="属性类型">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini"
                       @click="editEvent(scope.$index, scope.row)">编辑</el-button>
            <el-button size="mini"
                       type="danger"
                       @click="removeEvent(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--page分页信息 -->
      <div class="page">
        <el-pagination @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       :current-page="pageNow"
                       :page-sizes="[30, 50, 100]"
                       :page-size="30"
                       layout="total, sizes, prev, pager, next, jumper"
                       :total="totalPage">
        </el-pagination>
      </div>

      <!--弹出层：创建 -->
      <el-dialog title="新增"
                 width="400px"
                 :visible.sync="dialog.addFlag">

        <el-form :model="formAdd"
                 status-icon
                 :rules="rules"
                 ref="formAdd"
                 size="small"
                 label-width="120px">

          <el-form-item label="设备类型">
            <el-input v-model="formAdd.equipmentTypeName"
                      disabled
                      placeholder="请输入设备类型"></el-input>
          </el-form-item>

          <el-form-item label="设备属性模板">
            <el-input v-model="formAdd.equipmentPropertyTemplateName"
                      disabled
                      placeholder="请输入设备属性模板"></el-input>
          </el-form-item>

          <el-form-item label="设备属性类型"
                        prop="equipmentPropertyType">
            <el-select v-model="formAdd.equipmentPropertyType"
                       @change="changEvent('formAdd')"
                       placeholder="请选择">
              <el-option v-for="item in PropertyList"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="设备属性"
                        prop="equipmentPropertyCode">
            <el-select v-model="formAdd.equipmentPropertyCode"
                       placeholder="请选择">
              <el-option v-for="item in equipmentProList"
                         :key="item.equipmentPropertyCode"
                         :label="item.equipmentPropertyName"
                         :value="item.equipmentPropertyCode">
              </el-option>
            </el-select>
          </el-form-item>

        </el-form>
        <div slot="footer"
             class="dialog-footer">
          <el-button @click="dialog.addFlag = false"
                     size="small">取 消</el-button>
          <el-button type="primary"
                     size="small"
                     @click="addSumit('formAdd')">确 定</el-button>
        </div>
      </el-dialog>

      <!--弹出层：编辑 -->
      <el-dialog title="编辑"
                 width="400px"
                 :visible.sync="dialog.editFlag">
        <el-form :model="formEdit"
                 status-icon
                 :rules="rules"
                 ref="formEdit"
                 size="small"
                 label-width="120px">

          <el-form-item label="设备类型">
            <el-input v-model="formEdit.equipmentTypeName"
                      disabled
                      placeholder="请输入设备类型"></el-input>
          </el-form-item>

          <el-form-item label="设备属性模板">
            <el-input v-model="formEdit.equipmentPropertyTemplateName"
                      disabled
                      placeholder="请输入设备属性模板"></el-input>
          </el-form-item>

          <el-form-item label="设备属性类型"
                        prop="equipmentPropertyType">
            <el-select v-model="formEdit.equipmentPropertyType"
                       @change="changEvent('formEdit')"
                       placeholder="请选择">
              <el-option v-for="item in PropertyList"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="设备属性"
                        prop="equipmentPropertyCode">
            <el-select v-model="formEdit.equipmentPropertyCode"
                       placeholder="请选择">
              <el-option v-for="item in equipmentProList"
                         :key="item.equipmentPropertyCode"
                         :label="item.equipmentPropertyName"
                         :value="item.equipmentPropertyCode">
              </el-option>
            </el-select>
          </el-form-item>

        </el-form>
        <div slot="footer"
             class="dialog-footer">
          <el-button @click="dialog.editFlag = false"
                     size="small">取 消</el-button>
          <el-button type="primary"
                     size="small"
                     @click="editSumit('formEdit')">确 定</el-button>
        </div>
      </el-dialog>

      <!--弹出层：删除 -->
      <el-dialog title="删除"
                 width="400px"
                 :visible.sync="dialog.delFlag">
        <h4>是否确定删除当前配置信息？</h4>
        <div slot="footer"
             class="dialog-footer">
          <el-button @click="dialog.delFlag = false"
                     size="small">取 消</el-button>
          <el-button type="primary"
                     size="small"
                     @click="removeSumit('formDelete')">确 定</el-button>
        </div>
      </el-dialog>
    </main>
  </div>
</template>

<script>
import * as AJAX from '@/api/systemConfig/equipmentInfo/equipmentTemplateRelation.js'
export default {
  name: 'basicseting',
  data() {
    return {
      // 当前页码
      pageNow: 1,
      pageSize: 30,
      totalPage: 0,
      // 弹窗flag
      dialog: {
        addFlag: false,
        delFlag: false,
        editFlag: false
      },
      // 属性类型
      PropertyList: [
        {
          value: 0,
          label: '遥测'
        },
        {
          value: 1,
          label: '遥信'
        },
        {
          value: 2,
          label: '遥控'
        },
        {
          value: 3,
          label: '遥调'
        },
        {
          value: 4,
          label: '说明'
        }
      ],
      // 设备属性(所有，根据设备类型筛选)
      equipmentAllProList: [],
      equipmentProList: [],

      // 获取树,选中项的，设备类型code和模板code(后端使用)，name前端显示表格使用
      treeEquipmentTypeName: null,
      treeEquipmentTypeCode: null,
      treeEquipmentPropertyTemplateName: null,
      treeEquipmentPropertyTemplateCode: null,

      // 增加表单
      formAdd: {
        equipmentType: null,
        equipmentPropertyTemplateName: null,
        equipmentPropertyTemplateCode: null,
        equipmentPropertyCode: null
      },
      // 增加表单
      formEdit: {
        equipmentType: null,
        equipmentPropertyTemplateName: null,
        equipmentPropertyTemplateCode: null,
        equipmentPropertyCode: null,
        id: null
      },
      // 删除表单
      formDelete: {
        id: null
      },
      // 表格数据
      tableData: [],
      // 验证规则
      rules: {
        equipmentPropertyName: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        equipmentPropertyType: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        equipmentPropertyCode: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ]
      },
      // 树数据
      treeData: [],
      // 树默认展开项: code
      treeExpandedKey: '',
      // 树选中项
      treeCheckedKey: '',
      // 树渲染规则
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },
  methods: {
    /* 
      树
    */
    geTreeData() {
      let vm = this
      AJAX.getTreeDataList
        .r()
        .then(response => {
          vm.treeData = response.data.data
          vm.geTreeFirstCode()
          vm.getPageData()
        })
        .catch(error => {
          console.log(error)
        })
    },

    /*
      获取树默认展开的项的code
      01: 弹出层
      02：获取树,选中项的，设备类型code和模板code(后端使用)，name前端显示表格使用
    */
    geTreeFirstCode() {
      let vm = this
      let Data = vm.treeData[0]
      let treeExpandedKey = Data.code
      let treeCheckedKey = Data.code
      while (Reflect.has(Data, 'children') && Data.children.length > 0) {
        vm.treeEquipmentTypeName = Data.name
        vm.treeEquipmentTypeCode = Data.code
        Data = Data.children[0]
        treeExpandedKey = Data.code
        treeCheckedKey = Data.code
      }

      vm.treeExpandedKey = treeExpandedKey
      vm.treeCheckedKey = treeCheckedKey
      vm.treeEquipmentPropertyTemplateName = Data.name
      vm.treeEquipmentPropertyTemplateCode = Data.code
    },

    /* 
     自定义树结构
    */
    renderContent(h, { node, data, store }) {
      // console.log(node)
      let vm = this
      // console.log(store)
      let flag = vm.treeCheckedKey === data.code ? true : false
      return h(
        'span',
        {
          class: {
            current: flag
          }
        },
        [`${node.label}`]
      )
    },

    /* 
     点击树：获取选中栏目的treeCheckedKey \ treeExpandedKey
    */
    treeNodeClick(data, Nodes) {
      let vm = this
      if (Nodes.parent.data.name && Nodes.parent.data.code) {
        vm.treeEquipmentTypeName = Nodes.parent.data.name
        vm.treeEquipmentTypeCode = Nodes.parent.data.code
        vm.treeEquipmentPropertyTemplateName = data.name
        vm.treeEquipmentPropertyTemplateCode = data.code
        vm.treeExpandedKey = data.code
        vm.treeCheckedKey = data.code
        vm.getPageData()
      }
    },

    /* 
      设备属性类型：change联动，筛选对应 => 设备属性数据
    */
    changEvent(formName) {
      let vm = this
      let codeNum = vm[formName].equipmentPropertyType

      vm[formName].equipmentPropertyCode = ''

      vm.equipmentProList = vm.equipmentAllProList.filter(
        item => item.equipmentPropertyType === codeNum
      )

      console.log(vm.equipmentProList)
    },

    /* 
    获取弹窗: 设备属性下拉所有数据
     */
    getEquipmentProData() {
      let vm = this
      AJAX.getEquipmentProtoList
        .r()
        .then(response => {
          vm.equipmentAllProList.push(...response.data.data)
        })
        .catch(error => {
          console.log(error)
        })
    },

    /* 
      数据校验是否重复 
  
      01: 400 说明数据库存在, 相同数据
      02：vm.$refs[formName].resetFields() 重置表单
      03:
        response: 后端返回数据
        formName: 表单名
        dialogFlag : 被关闭的弹层flag
        sucessMsg: 成功提示文字
    */
    check(response, formName, dialogFlag, sucessMsg) {
      let vm = this

      response.data.code === 400
        ? vm.$message.error(response.data.message)
        : vm.$message.success(sucessMsg)

      setTimeout(() => {
        // 删除表单不需要重置
        if (formName !== 'formDelete') {
          vm.$refs[formName].resetFields()
        }
      }, 200)

      vm.dialog[dialogFlag] = false
      vm.getPageData()
    },

    /*
      后端请求
      01: 参数解释：
          urlName：调用的那个接口
          formName：表单名字
          dialogFlag：关闭的弹层falg
          sucessMsg：成功提示文字
          failMsg：失败提示文字
      02：
      vm.check()校验数据库重复，返回提示
    */
    httpRequst(urlName, formName, dialogFlag, sucessMsg, failMsg) {
      let vm = this
      AJAX[urlName]
        .r(vm[formName])
        .then(response => {
          vm.check(response, formName, dialogFlag, sucessMsg)
        })
        .catch(error => {
          vm.$message.error(failMsg)
          console.log(error)
        })
    },

    /* 
       增加
       01: addEvent 增加弹窗
       02：addSumit 增加弹窗表格提交
     */
    addEvent() {
      let vm = this

      vm.formAdd.equipmentType = vm.treeEquipmentTypeCode
      vm.formAdd.equipmentTypeName = vm.treeEquipmentTypeName

      vm.formAdd.equipmentPropertyTemplateCode =
        vm.treeEquipmentPropertyTemplateCode
      vm.formAdd.equipmentPropertyTemplateName =
        vm.treeEquipmentPropertyTemplateName
      vm.dialog.addFlag = true
    },
    addSumit(formName) {
      let vm = this
      console.log(vm[formName])
      vm.$refs[formName].validate(valid => {
        if (valid) {
          vm.httpRequst('addData', formName, 'addFlag', '增加成功', '增加失败')
        } else {
          return false
        }
      })
    },

    /* 
      编辑
      01: editEvent 编辑弹窗
      02：editSumit 编辑弹窗表格提交
      03: equipmentPropertyType文字转数字
    */
    editEvent(index, row) {
      let vm = this
      console.log(row)

      vm.formEdit.equipmentType = row.equipmentType
      vm.formEdit.equipmentTypeName = row.equipmentTypeName

      vm.formEdit.equipmentPropertyTemplateCode =
        row.equipmentPropertyTemplateCode

      vm.formEdit.equipmentPropertyTemplateName =
        row.equipmentPropertyTemplateName

      vm.formEdit.equipmentPropertyType = row.equipmentPropertyType

      vm.formEdit.equipmentPropertyName = row.equipmentPropertyName
      vm.formEdit.equipmentPropertyCode = row.equipmentPropertyCode

      vm.formEdit.id = row.id

      vm.equipmentProList = vm.equipmentAllProList.filter(
        item => item.equipmentPropertyType === row.equipmentPropertyType
      )

      vm.dialog.editFlag = true
    },
    editSumit(formName) {
      let vm = this
      vm.$refs[formName].validate(valid => {
        if (valid) {
          vm.httpRequst(
            'editData',
            formName,
            'editFlag',
            '修改成功',
            '修改失败'
          )
        } else {
          return false
        }
      })
    },
    /* 
      删除
      01: removeEvent 删除弹窗
      02：removeSumit 删除弹窗表格提交
    */
    removeEvent(index, row) {
      let vm = this
      vm.dialog.delFlag = true
      vm.formDelete.id = row.id
    },
    removeSumit(formName) {
      let vm = this
      vm.httpRequst('deleteData', formName, 'delFlag', '删除成功', '删除失败')
    },

    /* 根据分页：获取数据
        修改totalPage：总数据个数
    */
    getPageData() {
      let vm = this
      AJAX.pageData
        .r({
          pageSize: vm.pageSize,
          pageNow: vm.pageNow,
          equipmentPropertyTemplateCode: vm.treeEquipmentPropertyTemplateCode
        })
        .then(response => {
          vm.totalPage = response.data.data.totalNum
          console.log(response.data.data.items)
          vm.tableData = response.data.data.items.map(item => {
            item.equipmentPropertyTypeName = vm.PropertyList.find(
              target => target.value === item.equipmentPropertyType
            ).label
            return item
          })
        })
        .catch(error => {
          console.log(error)
        })
    },

    /* 分页功能*/
    handleSizeChange(val) {
      let vm = this
      vm.pageSize = val
      vm.getPageData()
    },
    handleCurrentChange(val) {
      let vm = this
      vm.pageNow = val
      vm.getPageData()
    },
    /* 导出*/
    exportEvent() {
      AJAX.exportData.r().then(res => {
        const fileName = decodeURIComponent(res.headers['filename'])
        const fileType = res.headers['filetype']
        // 这里res.data是返回的blob对象
        let blob = new Blob([res.data], {
          type:
            'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8'
        })
        let downloadElement = document.createElement('a')
        let href = window.URL.createObjectURL(blob) // 创建下载的链接
        downloadElement.href = href
        downloadElement.download = fileName + fileType // 下载后文件名
        document.body.appendChild(downloadElement)
        downloadElement.click() // 点击下载
        document.body.removeChild(downloadElement) // 下载完成移除元素
        window.URL.revokeObjectURL(href) // 释放掉blob对象
      })
    }
  },
  /* 
    00: 获取树数据 vm.geTreeData()
    01: 获取表格数据
    02: 获取弹窗：设备类型下拉数据
  */
  created() {
    let vm = this
    vm.getEquipmentProData()
    vm.geTreeData()
  }
}
</script>

<style lang="scss">
#tree {
  font-size: 14px;
  line-height: 40px;
  .el-tree-node__content {
    height: auto;
  }
  .current {
    color: #409eff;
  }
}
</style>


<style lang="scss" scoped>
.tagTip {
  padding-bottom: 20px;
  .title {
    padding-bottom: 15px;
    font-size: 17px;
  }
}
.flex {
  position: absolute;
  left: 0;
  top: 0;
  bottom: 0;
  right: 0;
  display: flex;
  justify-content: space-between;
  .el-aside {
    height: 100%;
    border-right: 1px solid #ddd;
  }
  main {
    flex: 1;
    padding: 20px;
  }
}
</style>
