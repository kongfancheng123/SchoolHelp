<template>
  <div>
    <!-- tagTip -->
    <div class="tagTip">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item class="title">上层节点信息配置</el-breadcrumb-item>
      </el-breadcrumb>
      <el-button type="primary"
                 size="mini"
                 @click="dialog.addFlag = true">创建</el-button>
      <el-button size="mini"
                 type="info">导入</el-button>
      <el-button size="mini">导出</el-button>
    </div>

    <!--表格 -->
    <el-table :data="tableData"
              border
              style="width: 100%">
      <el-table-column type="index">
      </el-table-column>
      <el-table-column prop="name"
                       label="节点编号"
                       width="180">
      </el-table-column>
      <el-table-column prop="address"
                       label="节点名称">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini"
                     @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini"
                     type="danger"
                     @click="handleRemove(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--弹出层：创建 -->
    <el-dialog title="新增"
               width="400px"
               :visible.sync="dialog.addFlag">
      <el-form :model="formAdd"
               size="small"
               label-width="100px">

        <el-form-item label="节点编号">
          <el-input></el-input>
        </el-form-item>

        <el-form-item label="节点名称">
          <el-input></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialog.addFlag = false"
                   size="small">取 消</el-button>
        <el-button type="primary"
                   size="small"
                   @click="dialog.addFlag = false">确 定</el-button>
      </div>
    </el-dialog>

    <!--弹出层：编辑 -->
    <el-dialog title="编辑"
               width="400px"
               :visible.sync="dialog.editFlag">
      <el-form :model="formAdd"
               size="small"
               label-width="100px">

        <el-form-item label="节点编号">
          <el-input></el-input>
        </el-form-item>

        <el-form-item label="节点名称">
          <el-input></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialog.editFlag = false"
                   size="small">取 消</el-button>
        <el-button type="primary"
                   size="small"
                   @click="dialog.editFlag = false">确 定</el-button>
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
                   @click="dialog.delFlag = false">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: 'basicseting',
  data() {
    return {
      // 弹窗flag
      dialog: {
        addFlag: false,
        delFlag: false,
        editFlag: false
      },
      // 属性类型
      options: [
        {
          value: 'YC',
          label: '遥测'
        },
        {
          value: 'YX',
          label: '遥信'
        },
        {
          value: 'YK',
          label: '遥控'
        },
        {
          value: 'YT',
          label: '遥调'
        },
        {
          value: 'EXPLAN',
          label: '说明'
        }
      ],
      value: '',
      // 增加表单
      formAdd: {},
      // 表格数据
      tableData: [
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }
      ]
    }
  },
  methods: {
    handleEdit() {
      this.dialog.editFlag = true
    },
    handleRemove() {
      this.dialog.delFlag = true
    }
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
