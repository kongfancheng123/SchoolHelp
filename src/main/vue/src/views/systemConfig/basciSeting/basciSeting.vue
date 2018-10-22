<template>
  <div>
    <!-- tagTip -->
    <div class="tagTip">
      <el-button type="primary"
                 size="mini"
                 @click="saveSumit('form')">保存</el-button>
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
            <el-input v-model="form.ip"
                      disabled></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="PORT">
            <el-input v-model="form.port"
                      disabled></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="密码">
            <el-input v-model="form.password"
                      disabled></el-input>
          </el-form-item>
        </el-col>
      </div>

      <!--基础设置 -->
      <h4>基础设置</h4>
      <div class="content">

        <el-col :span="12">
          <el-form-item label="数据使能">
            <el-select v-model="form.dataEnable"
                       placeholder="请选择活动区域">
              <el-option v-for="item in dataEnableList"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="报警使能">
            <el-select v-model="form.alarmEnable"
                       placeholder="请选择活动区域">
              <el-option v-for="item in alarmEnableList"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="上送周期">
            <el-input v-model="form.feedCycle"
                      placeholder="数值1 ~ 15"></el-input>
          </el-form-item>
          <span style="line-height:32px; color:#999">毫秒</span>
        </el-col>

        <el-col :span="12">
          <el-form-item label="日志级别">
            <el-select v-model="form.logLevel"
                       placeholder="请选择活动000区域">
              <el-option v-for="item in logLevelLsit"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </div>

      <!--版本信息 -->
      <h4>版本信息</h4>
      <div class="content">软件版本号 : {{form.version}}</div>

    </el-form>

  </div>
</template>

<script>
import * as AJAX from '@/api/systemConfig/basciSeting/basciSeting.js'

export default {
  name: 'basicseting',
  data() {
    return {
      dataEnableList: [
        {
          value: 1,
          label: '启用'
        },
        {
          value: 0,
          label: '关闭'
        }
      ],
      alarmEnableList: [
        {
          value: 1,
          label: '启用'
        },
        {
          value: 0,
          label: '关闭'
        }
      ],
      logLevelLsit: [
        {
          value: 1,
          label: '正常'
        },
        {
          value: 2,
          label: '严重'
        }
      ],
      form: {
        ip: null,
        port: null,
        password: null,
        dataEnable: null,
        alarmEnable: null,
        feedCycle: null,
        logLevel: null,
        version: null
      }
    }
  },
  methods: {
    /* 
      01: 展示配置信息
      02: 数据使能,报警使能
    */
    getAllData() {
      let vm = this
      AJAX.getShowData
        .r()
        .then(res => {
          vm.form = res.data.data
        })
        .catch(error => {
          console.log(error)
        })
    },

    /* 保存数据*/
    saveSumit(fromName) {
      let vm = this
      AJAX.saveDta
        .r(vm[fromName])
        .then(res => {
          vm.$message.success('保存成功')
          vm.getAllData()
        })
        .catch(error => {
          console.log(error)
        })
    }
  },
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
