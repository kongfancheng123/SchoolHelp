<template>
  <div>
    <!-- tagTip -->
    <div class="tagTip">
      <el-button type="primary"
                 size="mini"
                 @click="dialog.addFlag = true">保存</el-button>
      <el-button size="mini">取消</el-button>
    </div>

    <!--表单 -->
    <el-form ref="form"
             :model="form"
             :inline="true"
             size="small"
             label-width="80px">

      <!--通信设置 -->
      <h4>通信设置</h4>
      <div class="content">
        <el-col :span="12">
          <el-form-item label="IP">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="PORT">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="密码">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
        </el-col>
      </div>

      <!--基础设置 -->
      <h4>基础设置</h4>
      <div class="content">

        <el-col :span="12">
          <el-form-item label="数据使能">
            <el-select v-model="form.region"
                       placeholder="请选择活动区域">
              <el-option label="区域一"
                         value="shanghai"></el-option>
              <el-option label="区域二"
                         value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="报警使能">
            <el-select v-model="form.region"
                       placeholder="请选择活动区域">
              <el-option label="区域一"
                         value="shanghai"></el-option>
              <el-option label="区域二"
                         value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="上送周期">
            <el-input v-model="form.name"
                      placeholder="数值1 ~ 15"></el-input>
          </el-form-item>
          <span style="line-height:32px; color:#999">秒</span>
        </el-col>

        <el-col :span="12">
          <el-form-item label="日志级别">
            <el-select v-model="form.region"
                       placeholder="请选择活动区域">
              <el-option label="区域一"
                         value="shanghai"></el-option>
              <el-option label="区域二"
                         value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </div>

      <!--版本信息 -->
      <h4>版本信息</h4>
      <div class="content">软件版本号 : V1.1.001</div>

    </el-form>

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
      // 增加表单
      form: {},
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
  }
}

.el-form {
  font-size: 14px;
  .content {
    overflow: hidden;
    padding-left: 40px;
  }
  h4 {
    border-bottom: 1px solid #ddd;
    line-height: 34px;
    padding-bottom: 10px;
    margin: 30px 0 40px 0;
    font-weight: normal;
    font-size: 18px;
  }
}
</style>
