<template>
  <el-container>
    <!-- el-header -->
    <el-header>
      <img src="@/assets/logo.png"
           width="300">
      <span>ATM8000</span>数据模拟平台
    </el-header>
    <router-view></router-view>
    <!-- el-main -->
    <el-main>
      <div class="loginFrom">
        <el-form :model="formLogin"
                 status-icon
                 :rules="rules2"
                 ref="formLogin">
          <h4>登录系统</h4>

          <el-form-item prop="name">
            <el-input type="text"
                      v-model="formLogin.name"
                      autocomplete="off"
                      placeholder="用户名"></el-input>
          </el-form-item>

          <el-form-item prop="pass">
            <el-input type="password"
                      v-model="formLogin.pass"
                      autocomplete="off"
                      placeholder="密码"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary"
                       class="login"
                       @click="submitForm('formLogin')">登 录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-main>

    <!-- el-footer -->
    <el-footer>版权所有 苏州光格设备有限公司 苏ICP备14003533-1号</el-footer>
  </el-container>
</template>

<script>
export default {
  name: 'app',
  data() {
    let checkName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('名字不能为空'))
      } else if (value !== 'atm8000') {
        return callback(new Error('用户名不对'))
      }
      callback()
    }
    let checkPass = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('密码不能为空'))
      } else if (value !== 'agioe123456') {
        return callback(new Error('密码不对'))
      }
      callback()
    }
    return {
      formLogin: {
        pass: ''
      }, // 登录表单
      rules2: {
        // 表单验证
        pass: [{ validator: checkPass, trigger: 'blur' }],
        name: [{ validator: checkName, trigger: 'blur' }]
      }
    }
  },
  methods: {
    submitForm(formName) {
      let vm = this
      vm.$refs[formName].validate(valid => {
        if (valid) {
          // 密码账号正确，跳转内页
          vm.$router.push({ path: '/comprehensive' })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.el-header,
.el-footer {
  background-color: #007ef3;
  line-height: 60px;
  text-align: center;
}
.el-header {
  font-size: 26px;
  color: #fff;
  position: relative;
  span {
    font-size: 28px;
    padding-right: 3px;
  }
  img {
    position: absolute;
    top: 18px;
    left: 10px;
  }
}
.el-main {
  display: flex;
  align-items: center;
  .loginFrom {
    width: 400px;
    margin: 0 auto;
    border: 1px solid #fff;
    box-shadow: 0px 0px 25px #bbb;
    padding: 50px;
    border-radius: 8px;
    text-align: center;
    h4 {
      font-size: 20px;
      margin-bottom: 30px;
    }
    .login {
      color: #fff;
      background-color: #409eff;
      border-color: #409eff;
      display: block;
      border-radius: 2px;
      width: 100%;
    }
  }
}
.el-footer {
  font-size: 14px;
  color: #fff;
}
</style>
