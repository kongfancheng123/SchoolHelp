<template>
  <div>
    <!-- tagTip -->
    <div class="tagTip">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item class="title">设备信息配置</el-breadcrumb-item>
      </el-breadcrumb>
      <el-button type="primary"
                 size="mini"
                 @click="addEvent">创建</el-button>
      <el-button size="mini"
                 type="info">导入</el-button>
      <el-button size="mini"
                 @click="exportEvent">导出</el-button>
      <el-button size="mini"
                 type="primary"
                 @click="searchEvent">查询</el-button>
      <el-button size="mini"
                 type="primary"
                 @click="keyEvent">KEY配置</el-button>
    </div>

    <!--表格 -->
    <el-table :data="tableData"
              border
              style="width: 100%">
      <el-table-column type="index">
      </el-table-column>
      <el-table-column prop="parentNodeName"
                       label="上层节点">
      </el-table-column>

      <el-table-column prop="equipmentCode"
                       label="设备编号"
                       width="180">
      </el-table-column>
      <el-table-column prop="equipmentName"
                       label="设备名称">
      </el-table-column>
      <el-table-column prop="equipmentTypeName"
                       label="设备类型">
      </el-table-column>
      <el-table-column prop="equipmentPropertyTemplateName"
                       label="设备信号模板">
      </el-table-column>

      <el-table-column prop="equipmentPropertyTypeName"
                       label="监控信号类型">
      </el-table-column>

      <el-table-column prop="equipmentPropertyName"
                       label="设备信号">
      </el-table-column>
      <el-table-column prop="keyword"
                       label="设备点号">
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

    <!--弹出层：KEY配置(先配置该项，才能 添加 修改 删除 ) -->
    <el-dialog title="KEY配置"
               width="400px"
               :visible.sync="dialog.keyFlag">

      <el-form :model="formKey"
               size="small"
               :rules="rules"
               ref="formKey"
               label-width="110px">

        <el-form-item label="设备上层节点"
                      prop="parentNodeCode">
          <el-select v-model="formKey.parentNodeCode"
                     placeholder="请选择">
            <el-option v-for="item in parentNodeList"
                       :key="item.parentNodeCode"
                       :label="item.parentNodeName"
                       :value="item.parentNodeCode">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="监控信号类型"
                      prop="equipmentPropertyType">
          <el-select v-model="formKey.equipmentPropertyType"
                     placeholder="请选择">
            <el-option v-for="item in PropertyList"
                       :key="item.value"
                       :label="item.label"
                       :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="监控Key"
                      prop="keyWordStart">
          <el-input v-model.number="formKey.keyWordStart"
                    placeholder="设备编号前缀,可以为空"></el-input>
        </el-form-item>

      </el-form>

      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialog.keyFlag = false"
                   size="small">取 消</el-button>
        <el-button type="primary"
                   size="small"
                   @click="keySumit('formKey')">确 定</el-button>
      </div>

    </el-dialog>

    <!--弹出层：查询 -->
    <el-dialog title="查询"
               width="450px"
               :visible.sync="dialog.searchFlag">

      <el-form :model="formSearch"
               size="small"
               :rules="rules"
               ref="formSearch"
               label-width="150px">

        <el-form-item label="上层节点"
                      prop="parentNodeCode">

          <el-select v-model="formSearch.parentNodeCode"
                     placeholder="请选择">
            <el-option v-for="item in parentNodeList"
                       :key="item.parentNodeCode"
                       :label="item.parentNodeName"
                       :value="item.parentNodeCode">
            </el-option>
          </el-select>

        </el-form-item>

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

        <el-form-item label="设备监控信号模板">
          <el-select v-model="formSearch.equipmentPropertyTemplateCode"
                     placeholder="请选择">
            <el-option v-for="item in equipmentSignalTemplateList"
                       :key="item.equipmentPropertyTemplateCode"
                       :label="item.equipmentPropertyTemplateName"
                       :value="item.equipmentPropertyTemplateCode">
            </el-option>
          </el-select>
        </el-form-item>

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

      </el-form>

      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialog.searchFlag = false"
                   size="small">取 消</el-button>
        <el-button type="primary"
                   size="small"
                   @click="searchSumit('formSearch')">确 定</el-button>
      </div>

    </el-dialog>

    <!--弹出层：创建 -->
    <el-dialog title="新增"
               width="880px"
               :visible.sync="dialog.addFlag">

      <el-form :model="formAdd"
               size="small"
               :rules="rules"
               ref="formAdd"
               :inline="true"
               label-width="150px">

        <el-row :gutter="15">

          <el-col :span="12">
            <el-form-item label="设备编号前缀"
                          prop="equipmentCodeBefore">
              <el-input v-model="formAdd.equipmentCodeBefore"
                        placeholder="请设置设备编号前缀"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="设备编号起始数值"
                          prop="equipmentCodeStart">
              <el-input v-model.number="formAdd.equipmentCodeStart"
                        placeholder="起始数值1，正整形数字"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="设备名称前缀">
              <el-input v-model="formAdd.equipmentNameBefore"
                        placeholder="请设置设备名称前缀"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="设备名称起始数值">
              <el-input v-model.number="formAdd.equipmentNameStart"
                        placeholder="起始数值1，正整形数字"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="设备名称后缀">
              <el-input v-model="formAdd.equipmentNameAfter"
                        placeholder="请设置设备名称后缀"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">

          <el-col :span="12">
            <el-form-item label="设备类型"
                          prop="equipmentType">
              <el-select v-model="formAdd.equipmentType"
                         placeholder="请输入设备类型"
                         @change="changEvent">
                <el-option v-for="item in equipmentTypeList"
                           :key="item.equipmentTypeCode"
                           :label="item.equipmentTypeName"
                           :value="item.equipmentTypeCode">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="设备信号模板"
                          prop="equipmentPropertyTemplateCode">
              <el-select v-model="formAdd.equipmentPropertyTemplateCode"
                         placeholder="请输入设备信号模板"
                         @change="changEvent">
                <el-option v-for="item in equipmentSignalTemplateList"
                           :key="item.equipmentPropertyTemplateCode"
                           :label="item.equipmentPropertyTemplateName"
                           :value="item.equipmentPropertyTemplateCode">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="设备上层节点"
                          prop="parentNodeCode">
              <el-select v-model="formAdd.parentNodeCode"
                         placeholder="请输入设备上层节点">
                <el-option v-for="item in parentNodeList"
                           :key="item.parentNodeCode"
                           :label="item.parentNodeName"
                           :value="item.parentNodeCode">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">

            <el-form-item label="设备数量"
                          prop="equipmentNumber">
              <el-input v-model.number="formAdd.equipmentNumber"
                        placeholder="设备数量正整数，(1-1000)"></el-input>
            </el-form-item>

          </el-col>
        </el-row>

        <div class="biao">
          <!--表格 -->
          <el-table :data="tableKeyData"
                    border
                    height="250"
                    style="width: 100%">
            <el-table-column type="index">
            </el-table-column>
            <el-table-column prop="equipmentPropertyName"
                             align="center"
                             label="监控信号">
            </el-table-column>
          </el-table>
        </div>
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
               width="450px"
               :visible.sync="dialog.editFlag">

      <el-form :model="formEdit"
               size="small"
               :rules="rules"
               ref="formEdit"
               label-width="150px">

        <el-form-item label="设备编号">
          <el-input v-model="formEdit.equipmentCode"
                    disabled></el-input>
        </el-form-item>

        <el-form-item label="设备名称">
          <el-input v-model="formEdit.equipmentName"
                    disabled></el-input>
        </el-form-item>

        <el-form-item label="设备类型">
          <el-input v-model="formEdit.equipmentType"
                    disabled></el-input>
        </el-form-item>

        <el-form-item label="设备信号模板">
          <el-input v-model="formEdit.equipmentPropertyTemplateCode"
                    disabled></el-input>
        </el-form-item>

        <el-form-item label="设备上层节点">
          <el-input v-model="formEdit.parentNodeName"
                    disabled></el-input>
        </el-form-item>

        <el-form-item label="监控信号">
          <el-input v-model="formEdit.equipmentPropertyName"
                    disabled></el-input>
        </el-form-item>

        <el-form-item label="监控Key">
          <el-input v-model="formEdit.keyword"></el-input>
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

  </div>

</template>

<script>
import * as AJAX from '@/api/systemConfig/equipmentInfo/equipmentInfo.js'
export default {
  name: 'basicseting',
  data() {
    return {
      // 当前页码
      pageNow: 1,
      pageSize: 30,
      totalPage: 0,
      // 增加未成功前,提交按钮不能再去点击了,防止多次提交
      loading: null,
      // 弹窗flag
      dialog: {
        addFlag: false,
        delFlag: false,
        editFlag: false,
        keyFlag: false,
        searchFlag: false
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
      // 弹层：设备类型
      equipmentTypeList: [],
      // 弹层：设备信号模板
      equipmentSignalTemplateList: [],
      // 弹层：设备上层节点
      parentNodeList: [],
      // 弹层：搜索中监控信号
      searchSigleList: [],
      // 弹层表格
      tableKeyData: [],
      // 增加表单
      formAdd: {
        equipmentCodeBefore: null, // 设备编码前缀
        equipmentCodeStart: null, // 设备编码起始值
        equipmentNameBefore: null, // 设备名称前缀
        equipmentNameStart: null, // 设备名称起始值
        equipmentNameAfter: null, // 设备名称后缀
        equipmentType: null, // 设备类型编码
        equipmentPropertyTemplateCode: null, // 模板编码
        parentNodeCode: null, // 上层节点编码
        equipmentNumber: null // 设备数量
      },
      // 编辑表单
      formEdit: {
        equipmentCode: null,
        equipmentName: null,
        equipmentType: null,
        equipmentPropertyTemplateCode: null,
        parentNodeCode: null,
        parentNodeName: null,
        equipmentPropertyName: null,
        keyword: null,
        keywordOld: null
      },
      // 删除表单
      formDelete: {
        keyWord: '',
        parentNodeCode: ''
      },
      // key配置表单
      formKey: {
        keyWordStart: '',
        parentNodeCode: ''
      },
      // 查询表单
      formSearch: {
        parentNodeCode: null,
        equipmentType: null,
        equipmentPropertyTemplateCode: null,
        equipmentPropertyCode: null,
        pageNow: 1,
        pageSize: 10
      },
      // page表单
      formPage: {
        parentNodeCode: null,
        equipmentType: null,
        equipmentPropertyTemplateCode: null,
        equipmentPropertyCode: null,
        pageNow: 1,
        pageSize: 10
      },
      // 表格数据
      tableData: [],
      // 验证规则
      rules: {
        equipmentCodeBefore: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        equipmentNameBefore: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        equipmentNameAfter: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        equipmentCodeStart: [
          { required: true, message: '内容不能为空', trigger: 'blur' },
          {
            type: 'number',
            min: 1,
            message: '最小为1, 正整数',
            trigger: 'blur'
          }
        ],
        equipmentNameStart: [
          { required: true, message: '内容不能为空', trigger: 'blur' },
          {
            type: 'number',
            min: 1,
            message: '最小为1, 正整数',
            trigger: 'blur'
          }
        ],
        equipmentType: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        equipmentPropertyTemplateCode: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        parentNodeCode: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        equipmentNumber: [
          { required: true, message: '内容不能为空', trigger: 'blur' },
          {
            type: 'number',
            min: 1,
            max: 1000,
            message: '最小为1,最大为1000, 正整数',
            trigger: 'blur'
          }
        ],
        equipmentPropertyType: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        equipmentPropertyCode: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        keyWordStart: [
          { required: true, message: '内容不能为空', trigger: 'blur' },
          {
            type: 'number',
            min: 1,
            max: 1000,
            message: '最小为1,最大为1000, 正整数',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  methods: {
    /* 
    01: 弹层：设备类型 
    02: 弹层：设备信号模板 
    03: 弹层：设备上层节点 
    04: 弹层：搜索监控信号
    */
    geTanData() {
      let vm = this
      Promise.all([
        AJAX.getEquipmentType.r(),
        AJAX.getEquipmentSignalTemplate.r(),
        AJAX.getUpNode.r(),
        AJAX.getSearchMonitoringSignalData.r()
      ])
        .then(res => {
          vm.equipmentTypeList = res[0].data.data
          vm.equipmentSignalTemplateList = res[1].data.data
          vm.parentNodeList = res[2].data.data
          vm.searchSigleList = res[3].data.data
          vm.formPage.parentNodeCode = res[2].data.data[0].parentNodeCode
          vm.getPageData()
        })
        .catch(error => {
          console.log(error)
        })
    },

    /* 
      监测：设备类型，设备信号模板选择事件
      获取: 弹窗表格展示数据 
    */
    changEvent() {
      let vm = this
      vm.tableKeyData = null // 下次选择重置
      if (
        vm.formAdd.equipmentType &&
        vm.formAdd.equipmentPropertyTemplateCode
      ) {
        AJAX.getMonitoringSignalData
          .r({
            equipmentTypeCode: vm.formAdd.equipmentType,
            equipmentPropertyTemplateCode:
              vm.formAdd.equipmentPropertyTemplateCode
          })
          .then(res => {
            if (res.data.data.length === 0) {
              vm.$message.warning(
                '设备类型和设备信号模板之间无关联属性，请重新选择'
              )
              return false
            }
            vm.tableKeyData = res.data.data
            console.log(res.data.data)
          })
          .catch(error => {
            console.log(error)
          })
      }
    },

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
      setTimeout(() => {
        // 删除表单不需要重置
        if (formName !== 'formDelete') {
          vm.$refs[formName].resetFields()
        }
      }, 200)

      vm.dialog[dialogFlag] = false

      if (formName === 'formSearch') {
        vm.formPage = { ...vm.formSearch }
        vm.getPageData()
        return false
      }

      if (formName === 'formAdd' || formName === 'formKey') {
        vm.loading.close()
      }

      response.data.code === 400
        ? vm.$message.error(response.data.message)
        : vm.$message.success(sucessMsg)
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

    /* key设置
      01: keyEvent 增加弹窗
      02：keySumit 增加弹窗表格提交
    */
    keyEvent() {
      let vm = this
      vm.dialog.keyFlag = true
    },
    keySumit(formName) {
      let vm = this
      vm.$refs[formName].validate(valid => {
        if (valid) {
          // loading
          vm.loading = vm.$loading({
            lock: true,
            text: 'Loading',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          })

          vm.httpRequst('keyData', formName, 'keyFlag', '配置成功', '配置失败')
        } else {
          return false
        }
      })
    },

    /* 查询
     01: searchEvent 增加弹窗
     02：searchSumit 增加弹窗表格提交
   */
    searchEvent() {
      let vm = this
      vm.dialog.searchFlag = true
    },
    searchSumit(formName) {
      let vm = this
      vm.$refs[formName].validate(valid => {
        if (valid) {
          vm.httpRequst(
            'getSearchData',
            formName,
            'searchFlag',
            '查询成功',
            '查询失败'
          )
        } else {
          return false
        }
      })
    },

    /* 增加
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
          if (!vm.tableKeyData) {
            vm.$message.warning(
              '设备类型和设备信号模板之间无关联属性，请重新选择'
            )
          } else {
            // loading
            vm.loading = vm.$loading({
              lock: true,
              text: 'Loading',
              spinner: 'el-icon-loading',
              background: 'rgba(0, 0, 0, 0.7)'
            })
          }
          vm.httpRequst('addData', formName, 'addFlag', '增加成功', '增加失败')
        } else {
          return false
        }
      })
    },

    /* 编辑
      01: editEvent 编辑弹窗
      02：editSumit 编辑弹窗表格提交
      03: equipmentPropertyType文字转数字
    */
    editEvent(index, row) {
      let vm = this
      if (row.keyword) {
        vm.dialog.editFlag = true
        vm.formEdit.equipmentCode = row.equipmentCode
        vm.formEdit.equipmentName = row.equipmentName
        vm.formEdit.equipmentType = row.equipmentType
        vm.formEdit.parentNodeName = row.parentNodeName
        vm.formEdit.equipmentPropertyTemplateCode =
          row.equipmentPropertyTemplateCode
        vm.formEdit.parentNodeCode = row.parentNodeCode
        vm.formEdit.equipmentPropertyName = row.equipmentPropertyName
        vm.formEdit.keyword = row.keyword
        vm.formEdit.keywordOld = row.keyword
      } else {
        vm.$message.error('请先配置设备点号(KEY配置),在操作')
      }
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

    /* 删除
     01: removeEvent 删除弹窗
     02：removeSumit 删除弹窗表格提交
    */
    removeEvent(index, row) {
      let vm = this
      if (row.keyword) {
        // 要是有设备点号，才可操作
        vm.dialog.delFlag = true
        vm.formDelete.keyWord = row.keyword
        vm.formDelete.parentNodeCode = row.parentNodeCode
      } else {
        vm.$message.error('请先配置设备点号(KEY配置),在操作')
      }
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
      console.log(vm.formPage)
      AJAX.getSearchData
        .r(vm.formPage)
        .then(response => {
          vm.tableData = response.data.data.items.map(item => {
            let num = item.equipmentPropertyType
            if (num !== null) {
              item.equipmentPropertyTypeName = vm.PropertyList.find(element => {
                return element.value === num
              }).label
            }
            return item
          })
          vm.totalPage = response.data.data.totalNum
        })
        .catch(error => {
          console.log(error)
        })
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
    },
    /* 导出*/
    exportEvent() {
      let vm = this
      AJAX.exportData
        .r({ parentNodeCode: vm.formPage.parentNodeCode })
        .then(res => {
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
   01：弹层：数据请求
   03：表格展示数据
  */
  created() {
    let vm = this
    vm.geTanData()
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
.tagile {
  padding: 15px 0;
  border-top: 1px solid #ddd;
  margin-top: 20px;
}
</style>
