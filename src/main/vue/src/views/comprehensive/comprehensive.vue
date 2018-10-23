<template>
  <div class="pad20">
    <!-- 筛选 -->
    <div class="filterbar">

      <el-form :model="formSearch"
               size="small"
               :inline="true"
               :rules="rules"
               ref="formSearch"
               label-width="80px">

        <!-- 上层节点 -->
        <el-form-item label="上层节点"
                      prop="parentNodeCode">
          <el-select v-model="formSearch.parentNodeCode"
                     @change="clearEvent"
                     placeholder="请选择">
            <el-option v-for="item in parentNodeList"
                       :key="item.parentNodeCode"
                       :label="item.parentNodeName"
                       :value="item.parentNodeCode">
            </el-option>
          </el-select>
        </el-form-item>

        <!-- 设备类型 -->
        <el-form-item label="设备类型">
          <el-select v-model="formSearch.equipmentType"
                     placeholder="请选择">
            <el-option v-for="item in equipmentTypeList"
                       :key="item.equipmentTypeCode"
                       :label="item.equipmentTypeName"
                       :value="item.equipmentTypeCode">
            </el-option>
          </el-select>
        </el-form-item>

        <!-- 设备监控信号模板 -->
        <el-form-item label="设备监控信号模板"
                      label-width="150px">
          <el-select v-model="formSearch.equipmentPropertyTemplateCode"
                     placeholder="请选择">
            <el-option v-for="item in equipmentSignalTemplateList"
                       :key="item.equipmentPropertyTemplateCode"
                       :label="item.equipmentPropertyTemplateName"
                       :value="item.equipmentPropertyTemplateCode">
            </el-option>
          </el-select>
        </el-form-item>

        <!-- 监控信号 -->
        <el-form-item label="监控信号">
          <el-select v-model="formSearch.equipmentPropertyCode"
                     placeholder="请选择">
            <el-option v-for="item in searchSigleList"
                       :key="item.equipmentPropertyCode"
                       :label="item.equipmentPropertyName"
                       :value="item.equipmentPropertyCode">
            </el-option>
          </el-select>
        </el-form-item>

        <!-- 查询 -->
        <el-form-item>
          <el-button type="primary"
                     @click="searchSumit">查询</el-button>
        </el-form-item>

      </el-form>

      <!-- 开始 | 停止 发送 -->
      <div class="actions">
        <el-button type="primary"
                   size="small"
                   :disabled="dataEnableFalg"
                   @click="dialog.sendFlag = true">开始发送</el-button>
        <el-button type="primary"
                   size="small"
                   :disabled="stopFlag"
                   @click="stopSumit">停止发送</el-button>
      </div>
    </div>

    <!--表格 -->
    <el-table :data="tableData"
              border
              style="width: 100%">
      <el-table-column type="index">
      </el-table-column>
      <el-table-column prop="parentNodeName"
                       label="上层节点"
                       width="180">
      </el-table-column>
      <el-table-column prop="equipmentName"
                       label="设备名称">
      </el-table-column>

      <el-table-column prop="equipmentTypeName"
                       label="设备类型">
      </el-table-column>

      <el-table-column prop="equipmentPropertyTemplateName"
                       label="设备监控模板">
      </el-table-column>

      <el-table-column prop="equipmentPropertyName"
                       label="监控信号">
      </el-table-column>
      <el-table-column prop="keyword"
                       label="监控Key">
      </el-table-column>
      <el-table-column prop="dataVal"
                       label="实时数据">
      </el-table-column>
      <el-table-column prop="dataUpdate"
                       label="数据刷新时间">
      </el-table-column>
      <el-table-column prop="alarmVal"
                       label="上次报警状态">
      </el-table-column>
      <el-table-column prop="alarmUpdate"
                       label="上次报警时间">
      </el-table-column>
      <el-table-column prop="controlVal"
                       label="上次控制结果">
      </el-table-column>
      <el-table-column prop="controllerUpdate"
                       label="上次控制时间">
      </el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini"
                     :disabled="alarmEnableFalg||scope.row.alarmVal===''?true:false"
                     @click="warnEvent(scope.$index, scope.row)">发送报警</el-button>

          <el-button size="mini"
                     type="danger"
                     :disabled="scope.row.alarmVal===''?true:false"
                     @click="troublesHootingEvent(scope.$index, scope.row)">解除故障</el-button>
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

    <!--弹出层：开始发送 -->
    <el-dialog title="开始发送"
               width="880px"
               :visible.sync="dialog.sendFlag">

      <el-form :model="formSend"
               size="small"
               :rules="rules"
               ref="formSend"
               :inline="true"
               label-width="150px">

        <el-form-item label="设备上层节点"
                      prop="parentNodeCode">
          <el-select v-model="formSend.parentNodeCode"
                     placeholder="请输入设备上层节点"
                     @change="changEvent">
            <el-option v-for="item in parentNodeList"
                       :key="item.parentNodeCode"
                       :label="item.parentNodeName"
                       :value="item.parentNodeCode">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="设备类型"
                      prop="equipmentType">
          <el-select v-model="formSend.equipmentType"
                     placeholder="请输入设备类型"
                     @change="changEvent">
            <el-option v-for="item in equipmentTypeList"
                       :key="item.equipmentTypeCode"
                       :label="item.equipmentTypeName"
                       :value="item.equipmentTypeCode">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="设备信号模板"
                      prop="equipmentPropertyTemplateCode">
          <el-select v-model="formSend.equipmentPropertyTemplateCode"
                     placeholder="请输入设备信号模板"
                     @change="changEvent">
            <el-option v-for="item in equipmentSignalTemplateList"
                       :key="item.equipmentPropertyTemplateCode"
                       :label="item.equipmentPropertyTemplateName"
                       :value="item.equipmentPropertyTemplateCode">
            </el-option>
          </el-select>
        </el-form-item>

        <div class="biao">
          <!--表格 -->
          <el-table :data="tableKeyData"
                    border
                    height="250"
                    style="width: 100%">
            <el-table-column type="index">
            </el-table-column>

            <el-table-column align="center"
                             label="监控信号">
              <template slot-scope="scope">
                <span>{{ scope.row.equipmentPropertyName }} </span>
              </template>
            </el-table-column>

            <el-table-column align="center"
                             label="基础值">
              <template slot-scope="scope">
                <el-form-item>
                  <el-input v-model="scope.row.baseValue"
                            type="number"
                            placeholder="请填写整数类型"></el-input>
                </el-form-item>
              </template>
            </el-table-column>

            <el-table-column align="center"
                             label="上下波动范围">
              <template slot-scope="scope">
                <el-form-item>
                  <el-input v-model="scope.row.upAndDown"
                            type="number"
                            placeholder="请填写整数类型"></el-input>
                </el-form-item>
              </template>
            </el-table-column>

          </el-table>
        </div>
      </el-form>

      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialog.sendFlag = false"
                   size="small">取 消</el-button>
        <el-button type="primary"
                   size="small"
                   @click="sendSumit('formSend')">确 定</el-button>
      </div>
    </el-dialog>

    <!--弹出层：报警发送 -->
    <el-dialog title="发送报警"
               width="600px"
               :visible.sync="dialog.warnFlag">
      <el-form :model="formWarn"
               size="small"
               :rules="rules"
               ref="formWarn"
               label-width="100px">

        <el-form-item label="事件类型"
                      prop="eventType">
          <el-select v-model="formWarn.eventType"
                     placeholder="请选择事件类型">
            <el-option v-for="item in eventList"
                       :key="item.value"
                       :label="item.label"
                       :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="事件码"
                      prop="eventCode">
          <el-input v-model.number="formWarn.eventCode"
                    placeholder="请输入事件码"></el-input>
        </el-form-item>

        <el-form-item label="事件值"
                      prop="eventValue">
          <el-input v-model.number="formWarn.eventValue"
                    placeholder="请输入事件值"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialog.warnFlag = false"
                   size="small">取 消</el-button>
        <el-button type="primary"
                   size="small"
                   @click="warnSumit('formWarn')">确 定</el-button>
      </div>
    </el-dialog>

    <!--弹出层：解除故障 -->
    <el-dialog title="解除故障"
               width="600px"
               :visible.sync="dialog.troubleshootingFlag">

      <el-form :model="formTrouble"
               size="small"
               :rules="rules"
               ref="formTrouble"
               label-width="100px">

        <el-form-item label="事件类型"
                      prop="eventType">
          <el-select v-model="formTrouble.eventType"
                     placeholder="请选择事件类型">
            <el-option v-for="item in eventList"
                       :key="item.value"
                       :label="item.label"
                       :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="事件码"
                      prop="eventCode">
          <el-input v-model.number="formTrouble.eventCode"
                    placeholder="请输入事件码"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialog.troubleshootingFlag = false"
                   size="small">取 消</el-button>
        <el-button type="primary"
                   size="small"
                   @click="dialog.troubleshootingFlag = false">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import * as AJAX from '@/api/comprehensive/comprehensive.js'

export default {
  name: 'Comprehensive',
  data() {
    return {
      // 当前页码
      pageNow: 1,
      pageSize: 30,
      totalPage: 0,
      // 弹窗flag
      dialog: {
        sendFlag: false,
        warnFlag: false,
        troubleshootingFlag: false
      },
      // 停止进程
      threadName: null,
      // 数据使能
      dataEnableFalg: false,
      // 停止发送
      stopFlag: true,
      // 报警使能
      alarmEnableFalg: false,
      // 过滤条件：设备上层节点
      parentNodeList: [],
      // 过滤条件：设备类型
      equipmentTypeList: [],
      // 过滤条件：设备信号模板
      equipmentSignalTemplateList: [],
      // 过滤条件：搜索中监控信号
      searchSigleList: [],
      // 表格数据
      tableData: [],
      // 轮询Num
      pollingNum: null,
      // 轮询时间: 根据上送周期时长
      pollingTime: 2000,
      // 弹层监控信号：表格数据
      tableKeyData: [],
      // 表单：查询表单
      formSearch: {
        parentNodeCode: null,
        equipmentType: null,
        equipmentPropertyTemplateCode: null,
        equipmentPropertyCode: null,
        pageNow: 1,
        pageSize: 30
      },
      // 表单：获取数据page表单
      formPage: {
        parentNodeCode: null,
        equipmentType: null,
        equipmentPropertyTemplateCode: null,
        equipmentPropertyCode: null,
        pageNow: 1,
        pageSize: 30
      },
      // 表单：开始发送
      formSend: {
        parentNodeCode: null,
        equipmentType: null,
        equipmentPropertyTemplateCode: null,
        propertyCodeAndValue: []
      },
      // 表单：报警发送
      formWarn: {
        eventCode: null, // 类型：Number  必有字段  备注：事件码
        eventType: null, // 类型：Number  必有字段  备注：事件类型
        eventValue: null, // 类型：Number  必有字段  备注：事件值
        keyword: null, // 类型：String  必有字段  备注：keyword
        parentNodeCode: null, // 类型：String  必有字段  备注：上层节点编码
        equipmentPropertyType: null // 类型：Number  必有字段  备注：属性类型
      },
      // 表单：解除故障
      formTrouble: {},
      // 取消令牌
      source: null,
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
      // 事件类型
      eventList: [
        {
          value: 0,
          label: '报警'
        },
        {
          value: 1,
          label: '故障'
        }
      ],
      // 当前页码
      currentPage: 4,
      // 验证规则
      rules: {
        parentNodeCode: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        equipmentType: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        equipmentPropertyTemplateCode: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        baseValue: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        upAndDown: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        eventCode: [
          {
            type: 'number',
            required: true,
            message: '内容不能为空',
            trigger: 'blur'
          }
        ],
        eventType: [
          {
            type: 'number',
            required: true,
            message: '内容不能为空',
            trigger: 'blur'
          }
        ],
        eventValue: [
          {
            type: 'number',
            required: true,
            message: '内容不能为空',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  methods: {
    /* 数据校验是否重复 
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
      console.log(formName)
      setTimeout(() => {
        // 删除表单不需要重置
        if (formName !== 'formDelete') {
          vm.$refs[formName].resetFields()
        }
      }, 200)
      if (formName === 'formSearch') {
        vm.tableData = response.data.data.items
        return false
      }
      vm.dialog[dialogFlag] = false
      response.data.code === 400
        ? vm.$message.error(response.data.message)
        : vm.$message.success(sucessMsg)
      vm.getPageData()
    },

    /* 后端请求
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
    01:过滤条件数据

    02:获取基础设置中：数据使能和报警使能
        I：数据使能：能否开始发送
        II：报警使能：能否发送报警

    03: 获取开始发送和停止发送按钮是否开启判断(开始发送和数据使能配合)

    04: 停止开始发送开关
    */
    getReadyData() {
      let vm = this
      Promise.all([
        AJAX.getUpNodeData.r(),
        AJAX.getEquipmentTypeData.r(),
        AJAX.getEquipmentSignalTemplateData.r(),
        AJAX.getEquipmentSignalData.r(),
        AJAX.getEnergyData.r(),
        AJAX.switchData.r()
      ])
        .then(res => {
          vm.parentNodeList = res[0].data.data
          vm.equipmentTypeList = res[1].data.data
          vm.equipmentSignalTemplateList = res[2].data.data
          vm.searchSigleList = res[3].data.data
          let { dataEnable, alarmEnable } = res[4].data.data
          let { controlVal } = res[5].data.data

          if (dataEnable !== 1 || controlVal === 0) {
            vm.dataEnableFalg = true
            vm.stopFlag = false
          }
          if (alarmEnable !== 1) {
            vm.alarmEnableFalg = true
          }

          vm.formPage.parentNodeCode = vm.parentNodeList[0].parentNodeCode

          vm.getPageData()
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 筛选条件,清除后面的选择置空
    clearEvent() {
      let vm = this
      vm.formSearch.equipmentType = null
      vm.formSearch.equipmentPropertyTemplateCode = null
      vm.formSearch.equipmentPropertyCode = null
    },
    /* 
    过滤条件 监测：设备类型，设备信号模板选择事件
    获取: 弹窗表格展示数据 
  */
    changEvent() {
      let vm = this
      vm.tableKeyData = null // 下次选择重置
      if (
        vm.formSend.parentNodeCode &&
        vm.formSend.equipmentType &&
        vm.formSend.equipmentPropertyTemplateCode
      ) {
        AJAX.getSendTableData
          .r(vm.formSend)
          .then(res => {
            vm.tableKeyData = res.data.data
          })
          .catch(error => {
            console.log(error)
          })
      }
    },

    /* 获取分页数据 */
    getPageData() {
      let vm = this
      AJAX.getSearchData
        .r(vm.formPage)
        .then(response => {
          vm.tableData = response.data.data.items
          vm.totalPage = vm.tableData.length
          vm.pollingEvent()
        })
        .catch(error => {
          console.log(error)
        })
    },

    /* 查询 */
    searchSumit() {
      let vm = this
      vm.$refs.formSearch.validate(valid => {
        if (valid) {
          vm.formPage = { ...vm.formSearch }
          vm.getPageData()
        } else {
          return false
        }
      })
    },

    /* 开始发送事件
     02: CancelToken | source取消令牌
     03：sendFlag 关闭弹窗
    */
    sendSumit(formName) {
      const vm = this
      let emptyFlag = true // 检测表格中的input是否为空,开关(有空位false,则数据不能提交)
      vm.dialog.sendFlag = false // 关闭弹层

      vm.dataEnableFalg = true
      vm.stopFlag = false

      vm.$refs[formName].validate(valid => {
        if (valid) {
          const CancelToken = vm.$AxiosFn.CancelToken
          vm.source = CancelToken.source()
          // 整个tableKeyData表格数据,转后端需要的格式
          vm[formName].propertyCodeAndValue = vm.tableKeyData.map(item => {
            if (!item.baseValue || !item.upAndDown) {
              emptyFlag = false
            }
            let arr = []
            arr[0] = item.equipmentPropertyCode
            arr[1] = item.baseValue
            arr[2] = item.upAndDown
            return arr
          })

          if (!emptyFlag) {
            vm.$message.error('基础值,上下波动范围值，必须都不为空！')
            vm.dialog.sendFlag = true
            vm.dataEnableFalg = false
            vm.stopFlag = true
            return false
          }

          AJAX.sendData
            .r(vm[formName], { cancelToken: vm.source.token })
            .then(response => {
              if (response.data.code === 200) {
                vm.$message.success('发送成功')
                vm.$refs[formName].resetFields()
              } else {
                vm.$message.error('发送失败')
              }
              vm.dataEnableFalg = true
              vm.stopFlag = false
              vm.getPageData()
            })
            .catch(error => {
              vm.dataEnableFalg = false
              vm.stopFlag = true
              vm.getPageData()
              console.log(error)
            })
        } else {
          return false
        }
      })
    },

    /* 轮训数据*/
    pollingEvent() {
      let vm = this
      clearTimeout(vm.pollingNum)
      vm.pollingNum = setTimeout(() => {
        vm.getPageData()
      }, 3000)
    },

    /* 停止发送*/
    stopSumit() {
      let vm = this
      AJAX.stopSendData
        .r()
        .then(res => {
          vm.dataEnableFalg = false
          vm.stopFlag = true
          vm.$message.success('停止发送成功')
          clearTimeout(vm.pollingNum)
        })
        .catch(error => {
          vm.$message.error('停止发送失败')
          console.log(error)
        })
    },

    /* 01: 取消发送事件*/
    CancelSendEvent() {
      const vm = this
      vm.source.cancel('取消请求')
    },

    /* 发送报警
    02: warnEvent 弹窗
    03：warnSumit 提交发送报警表单
   */
    warnEvent(index, row) {
      let vm = this
      vm.formWarn.keyword = row.keyword
      vm.formWarn.parentNodeCode = row.parentNodeCode
      vm.formWarn.equipmentPropertyType = row.equipmentPropertyType
      // 0:遥测 1:遥信 不是这两个，则不能发送
      if (row.equipmentPropertyType > 1) {
        vm.$message.error('该设备信号类型，不允许发送报警')
        return false
      }
      vm.dialog.warnFlag = true
    },

    warnSumit(formName) {
      let vm = this
      vm.$refs[formName].validate(valid => {
        if (valid) {
          vm.httpRequst(
            'sendWarnData',
            formName,
            'warnFlag',
            '发送报警成功',
            '发送报警失败'
          )
        } else {
          return false
        }
      })
    },

    /* 解除故障
    02: troublesHootingEvent 弹窗
    03：warnSumit 提交发送报警表单
   */
    troublesHootingEvent(index, row) {
      let vm = this
      vm.formTrouble.keyword = row.keyword
      vm.formTrouble.parentNodeCode = row.parentNodeCode
      vm.formTrouble.equipmentPropertyType = row.equipmentPropertyType
      vm.dialog.troubleshootingFlag = true
    },

    /* 分页功能*/
    handleSizeChange(val) {
      let vm = this
      vm.pageSize = val
      vm.formPage.pageSize = val
      vm.getPageData()
    },
    handleCurrentChange(val) {
      let vm = this
      vm.pageNow = val
      vm.formPage.pageNow = val
      vm.getPageData()
    }
  },
  /*
    00: 获取基本设置：数据使能，报警使能 0关闭 1开启
    01: 获取筛选数据
    02: 获取表格数据
  */
  created() {
    let vm = this
    vm.getReadyData()
  },
  destroyed() {
    let vm = this
    clearTimeout(vm.pollingNum)
  }
}
</script>


<style scoped lang="scss">
.pad20 {
  padding: 20px;
}
.filterbar {
  padding: 5px 0 20px 0;
}
</style>
