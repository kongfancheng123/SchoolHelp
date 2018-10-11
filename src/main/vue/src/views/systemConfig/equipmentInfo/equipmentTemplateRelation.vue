<template>
  <div class="flex">
    <el-aside width="220px">
      <el-tree :data="data"
               :props="defaultProps"
               @node-click="handleNodeClick"></el-tree>
    </el-aside>

    <main>
      <!-- tagTip -->
      <div class="tagTip">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item class="title">信号模板关联配置</el-breadcrumb-item>
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
                         label="设备类型"
                         width="180">
        </el-table-column>
        <el-table-column prop="address"
                         label="模板名称">
        </el-table-column>
        <el-table-column prop="address"
                         label="属性编码">
        </el-table-column>
        <el-table-column prop="address"
                         label="属性名称">
        </el-table-column>
        <el-table-column prop="address"
                         label="属性类型">
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

          <el-form-item label="设备类型">
            <el-input disabled></el-input>
          </el-form-item>

          <el-form-item label="设备属性模板">
            <el-input disabled></el-input>
          </el-form-item>

          <el-form-item label="设备属性类型">
            <el-select v-model="value"
                       placeholder="请选择">
              <el-option v-for="item in options"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="设备属性">
            <el-select v-model="value"
                       placeholder="请选择">
              <el-option v-for="item in options"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
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

          <el-form-item label="设备类型">
            <el-input disabled></el-input>
          </el-form-item>

          <el-form-item label="设备属性模板">
            <el-input disabled></el-input>
          </el-form-item>

          <el-form-item label="设备属性类型">
            <el-select v-model="value"
                       placeholder="请选择">
              <el-option v-for="item in options"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="设备属性">
            <el-select v-model="value"
                       placeholder="请选择">
              <el-option v-for="item in options"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
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
    </main>
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
      ],
      // 树数据
      data: [
        {
          label: '一级 1',
          children: [
            {
              label: '二级 1-1',
              children: [
                {
                  label: '三级 1-1-1'
                }
              ]
            }
          ]
        },
        {
          label: '一级 2',
          children: [
            {
              label: '二级 2-1',
              children: [
                {
                  label: '三级 2-1-1'
                }
              ]
            },
            {
              label: '二级 2-2',
              children: [
                {
                  label: '三级 2-2-1'
                }
              ]
            }
          ]
        },
        {
          label: '一级 3',
          children: [
            {
              label: '二级 3-1',
              children: [
                {
                  label: '三级 3-1-1'
                }
              ]
            },
            {
              label: '二级 3-2',
              children: [
                {
                  label: '三级 3-2-1'
                }
              ]
            }
          ]
        }
      ],
      // 树渲染规则
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  methods: {
    handleEdit() {
      this.dialog.editFlag = true
    },
    handleRemove() {
      this.dialog.delFlag = true
    },
    handleNodeClick(data) {
      console.log(data)
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
