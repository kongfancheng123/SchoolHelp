<template>
  <div>
    <!-- 筛选 -->
    <div class="filterbar">
      <el-form :inline="true"
               :model="formFilter"
               size="small">

        <!-- 上层节点 -->
        <el-form-item label="上层节点">
          <el-select v-model="formFilter.region"
                     placeholder="活动区域">
            <el-option label="区域一"
                       value="shanghai"></el-option>
            <el-option label="区域二"
                       value="beijing"></el-option>
          </el-select>
        </el-form-item>

        <!-- 设备类型 -->
        <el-form-item label="设备类型">
          <el-select v-model="formFilter.region"
                     placeholder="设备类型">
            <el-option label="区域一"
                       value="shanghai"></el-option>
            <el-option label="区域二"
                       value="beijing"></el-option>
          </el-select>
        </el-form-item>

        <!-- 设备监控信号模板 -->
        <el-form-item label="设备监控信号模板">
          <el-select v-model="formFilter.region"
                     placeholder="设备类型">
            <el-option label="区域一"
                       value="shanghai"></el-option>
            <el-option label="区域二"
                       value="beijing"></el-option>
          </el-select>
        </el-form-item>

        <!-- 监控信号 -->
        <el-form-item label="监控信号">
          <el-select v-model="formFilter.region"
                     placeholder="设备类型">
            <el-option label="区域一"
                       value="shanghai"></el-option>
            <el-option label="区域二"
                       value="beijing"></el-option>
          </el-select>
        </el-form-item>

        <!-- 查询 -->
        <el-form-item>
          <el-button type="primary">查询</el-button>
        </el-form-item>

      </el-form>

      <!-- 开始 | 停止 发送 -->
      <div class="actions">
        <el-button type="primary"
                   size="small"
                   @click="dialog.sendFlag = true">开始发送</el-button>
        <el-button type="primary"
                   size="small"
                   @click="handleCancelSendFrom">停止发送</el-button>
      </div>
    </div>
    <!--表格 -->
    <el-table :data="tableData"
              border
              style="width: 100%">
      <el-table-column type="index">
      </el-table-column>
      <el-table-column prop="name"
                       label="上层节点"
                       width="180">
      </el-table-column>
      <el-table-column prop="address"
                       label="设备名称">
      </el-table-column>
      <el-table-column prop="address"
                       label="设备监控模板">
      </el-table-column>
      <el-table-column prop="address"
                       label="设备类型">
      </el-table-column>

      <el-table-column prop="address"
                       label="监控信号">
      </el-table-column>
      <el-table-column prop="address"
                       label="监控Key">
      </el-table-column>
      <el-table-column prop="address"
                       label="实时数据">
      </el-table-column>
      <el-table-column prop="address"
                       label="数据刷新时间">
      </el-table-column>
      <el-table-column prop="address"
                       label="上次报警状态">
      </el-table-column>
      <el-table-column prop="address"
                       label="上次报警时间">
      </el-table-column>
      <el-table-column prop="address"
                       label="上次控制结果">
      </el-table-column>
      <el-table-column prop="address"
                       label="上次控制时间">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini"
                     @click="handleWarn(scope.$index, scope.row)">发送报警</el-button>
          <el-button size="mini"
                     type="danger"
                     @click="handleRemove(scope.$index, scope.row)">解除故障</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--弹出层：开始发送 -->
    <el-dialog title="开始发送"
               width="600px"
               :visible.sync="dialog.sendFlag">
      <el-form :model="formSend"
               size="small"
               label-width="100px">

        <el-form-item label="设备上层节点">
          <el-select v-model="formSend.region"
                     placeholder="请选择活动区域">
            <el-option label="区域一"
                       value="shanghai"></el-option>
            <el-option label="区域二"
                       value="beijing"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="设备类型">
          <el-select v-model="formSend.region"
                     placeholder="请选择活动区域">
            <el-option label="区域一"
                       value="shanghai"></el-option>
            <el-option label="区域二"
                       value="beijing"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="设备信号模板">
          <el-select v-model="formSend.region"
                     placeholder="请选择活动区域">
            <el-option label="区域一"
                       value="shanghai"></el-option>
            <el-option label="区域二"
                       value="beijing"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialog.sendFlag = false"
                   size="small">取 消</el-button>
        <el-button type="primary"
                   size="small"
                   @click="handleSendFrom">确 定</el-button>
      </div>
    </el-dialog>

    <!--弹出层：报警发送 -->
    <el-dialog title="发送报警"
               width="600px"
               :visible.sync="dialog.warnFlag">
      <el-form :model="formWarn"
               size="small"
               label-width="100px">

        <el-form-item label="事件类型">
          <el-select v-model="formWarn.region"
                     placeholder="请选择活动区域">
            <el-option label="区域一"
                       value="shanghai"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="事件码">
          <el-input></el-input>
        </el-form-item>

        <el-form-item label="事件值">
          <el-input></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialog.warnFlag = false"
                   size="small">取 消</el-button>
        <el-button type="primary"
                   size="small"
                   @click="dialog.warnFlag = false">确 定</el-button>
      </div>
    </el-dialog>

    <!--弹出层：解除故障 -->
    <el-dialog title="解除故障"
               width="600px"
               :visible.sync="dialog.removeFlag">
      <el-form :model="formRemove"
               size="small"
               label-width="100px">

        <el-form-item label="事件类型">
          <el-select v-model="formRemove.region"
                     placeholder="请选择活动区域">
            <el-option label="区域一"
                       value="shanghai"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="事件码">
          <el-input></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialog.removeFlag = false"
                   size="small">取 消</el-button>
        <el-button type="primary"
                   size="small"
                   @click="dialog.removeFlag = false">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { getdata } from '@/api/comprehensive/comprehensive.js'

export default {
  name: 'Comprehensive',
  data() {
    return {
      // 弹窗flag
      dialog: {
        sendFlag: false,
        warnFlag: false,
        removeFlag: false
      },
      // 表格数据
      tableData: [
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }
      ],
      // 取消令牌
      source: null,
      // 筛选条件表单
      formFilter: {
        user: '',
        region: ''
      },
      // 表单：开始发送
      formSend: {},
      // 表单：报警发送
      formWarn: {},
      // 表单：解除故障
      formRemove: {}
    }
  },
  methods: {
    /*
     01: 开始发送事件
     02: CancelToken | source取消令牌
     03：sendFlag 关闭弹窗
    */
    handleSendFrom() {
      const vm = this
      const CancelToken = vm.$AxiosFn.CancelToken
      vm.source = CancelToken.source()
      vm.dialog.sendFlag = false
      setTimeout(() => {
        getdata
          .r({}, { cancelToken: vm.source.token })
          .then(response => {
            console.log(response.data.data)
          })
          .catch(error => {
            if (vm.$AxiosFn.isCancel(error)) {
              console.log(error.message)
            } else {
              console.log(error)
            }
          })
      }, 2000)
    },

    /*
    01: 取消发送事件
   */
    handleCancelSendFrom() {
      const vm = this
      vm.source.cancel('取消请求')
    },

    handleWarn(index, row) {
      let vm = this
      vm.dialog.warnFlag = true
      console.log(index, row)
    },
    handleRemove(index, row) {
      let vm = this
      vm.dialog.removeFlag = true
      console.log(index, row)
    }
  },
  /*
    01: 获取筛选数据
  */
  created() {
    console.log('获取筛选数据')
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.filterbar {
  padding: 5px 0 20px 0;
}
</style>
