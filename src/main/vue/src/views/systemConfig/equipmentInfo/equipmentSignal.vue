<template>
  <div>
    <!-- tagTip -->
    <div class="tagTip">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item class="title">设备类型配置</el-breadcrumb-item>
      </el-breadcrumb>
      <el-button type="primary"
                 size="mini"
                 @click="addEvent">新 增</el-button>
      <el-button size="mini"
                 type="info">导 入</el-button>
      <el-button size="mini">导 出</el-button>
    </div>

    <!--表格 -->
    <el-table :data="tableData"
              border
              style="width: 100%">
      <el-table-column type="index">
      </el-table-column>
      <el-table-column prop="equipmentTypeCode"
                       label="类型编码"
                       width="180">
      </el-table-column>
      <el-table-column prop="equipmentTypeName"
                       label="类型名称">
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

    <!--弹出层：创建 -->
    <el-dialog title="新增"
               width="400px"
               :visible.sync="dialog.addFlag">
      <el-form :model="formAdd"
               status-icon
               :rules="rules"
               ref="formAdd"
               size="small"
               label-width="100px">

        <el-form-item label="类型编码"
                      prop="equipmentTypeCode">
          <el-input v-model="formAdd.equipmentTypeCode"
                    placeholder="请输入类型编码"></el-input>
        </el-form-item>

        <el-form-item label="类型名称"
                      prop="equipmentTypeName">
          <el-input v-model="formAdd.equipmentTypeName"
                    placeholder="请输入类型名称"></el-input>
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
               label-width="100px">

        <el-form-item label="类型编码"
                      prop="equipmentTypeCode">
          <el-input v-model="formEdit.equipmentTypeCode"
                    disabled
                    placeholder="请输入类型编码"></el-input>
        </el-form-item>

        <el-form-item label="类型名称"
                      prop="equipmentTypeName">
          <el-input v-model="formEdit.equipmentTypeName"
                    placeholder="请输入类型名称"></el-input>
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
                   @click="removeSumit">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import * as AJAX from '@/api/systemConfig/equipmentInfo/equipmentSignal.js'

export default {
  data() {
    return {
      // 表格数据
      tableData: [],
      // 弹窗flag
      dialog: {
        addFlag: false,
        delFlag: false,
        editFlag: false
      },
      // 增加表单
      formAdd: {
        equipmentTypeCode: null,
        equipmentTypeName: null
      },
      // 编辑表单
      formEdit: {
        equipmentTypeCode: null,
        equipmentTypeName: null
      },
      // 删除表单
      formDelete: {
        equipmentTypeCode: null
      },
      // 验证规则
      rules: {
        equipmentTypeCode: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        equipmentTypeName: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    /* 获取所有数据 */
    getAllData() {
      let vm = this
      AJAX.getAllDataList
        .r()
        .then(response => {
          vm.tableData = response.data.data
        })
        .catch(error => {
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
      vm.dialog.addFlag = true
    },
    addSumit(formName) {
      let vm = this
      vm.$refs[formName].validate(valid => {
        if (valid) {
          vm.dialog.addFlag = false
          AJAX.addData
            .r(vm.formAdd)
            .then(response => {
              vm.$message.success('增加成功')
              vm.getAllData()
            })
            .catch(error => {
              vm.$message.error('增加失败')
              console.log(error)
            })
        } else {
          return false
        }
      })
    },

    /* 
      编辑
      01: editEvent 编辑弹窗
      02：editSumit 编辑弹窗表格提交
    */
    editEvent(index, row) {
      let vm = this
      vm.formEdit.equipmentTypeCode = row.equipmentTypeCode
      vm.formEdit.equipmentTypeName = row.equipmentTypeName
      vm.dialog.editFlag = true
    },
    editSumit(formName) {
      let vm = this
      vm.$refs[formName].validate(valid => {
        if (valid) {
          vm.dialog.editFlag = false
          AJAX.editData
            .r(vm.formEdit)
            .then(response => {
              vm.$message.success('修改成功')
              vm.getAllData()
            })
            .catch(error => {
              vm.$message.error('修改失败')
              console.log(error)
            })
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
      vm.formDelete.equipmentTypeCode = row.equipmentTypeCode
    },
    removeSumit() {
      let vm = this
      vm.dialog.delFlag = false
      AJAX.deleteData
        .r(vm.formDelete)
        .then(response => {
          vm.$message.success('删除成功')
          vm.getAllData()
        })
        .catch(error => {
          vm.$message.error('删除失败')
          console.log(error)
        })
    }
  },

  /* 获取表格数据 */
  created() {
    let vm = this
    vm.getAllData()
  }
}
</script>

<style lang="scss" scoped>
.tagTip {
  padding-bottom: 20px;
  .title {
    padding-bottom: 15px;
    font-size: 17px;
  }
}
</style>
