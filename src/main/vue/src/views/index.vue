<template>
  <el-container>
    <!-- el-header -->
    <el-header id="salfmenu">
      <!-- leftbar -->
      <div class="leftbar">
        <img src="@/assets/logo.png"
             width="300">

        <!-- el-menu -->
        <el-menu router
                 :default-active="$route.path"
                 class="el-menu-demo"
                 mode="horizontal">
          <el-menu-item index="/comprehensive"
                        route>
            <i class="el-icon-picture"></i>综合展示</el-menu-item>

          <el-submenu index="2">
            <template slot="title">系统配置</template>
            <el-menu-item index="/systemConfig">基础设置</el-menu-item>
            <el-submenu index="">
              <template slot="title">设备信息</template>
              <el-menu-item index="/systemConfig/equipmentType">设备类型</el-menu-item>
              <el-menu-item index="/systemConfig/equipmentSignal">设备监控信号</el-menu-item>
              <el-menu-item index="/systemConfig/equipmentSignalTemplate">监控信号模板</el-menu-item>
              <el-menu-item index="/systemConfig/equipmentTemplateRelation">信号模板关联</el-menu-item>
              <el-menu-item index="/systemConfig/upperNodeInfo">上层节点信息</el-menu-item>
              <el-menu-item index="/systemConfig/equipmentInfo">设备信息</el-menu-item>
            </el-submenu>
          </el-submenu>

          <el-menu-item index="/log"
                        route>
            <i class="el-icon-tickets"></i>实时日志</el-menu-item>
        </el-menu>
      </div>

    </el-header>

    <!-- el-main -->
    <el-main>
      <router-view></router-view>
    </el-main>

  </el-container>
</template>

<script>
export default {
  name: 'index',
  data() {
    return {}
  }
}
</script>
<style>
.el-menu.el-menu--horizontal {
  border-bottom: none;
}
</style>


<style lang="scss">
#salfmenu {
  .el-submenu,
  .el-menu-item {
    background: #007ef3;
    border: none;
    color: #fff;
  }

  i {
    color: #fff;
  }

  .el-menu-item.is-active {
    border-bottom: none;
    color: #fff;
    background: #0084fe;
  }

  .el-submenu__title {
    color: #fff;
  }
  .el-submenu__title:hover {
    background: #0084fe;
  }
}
</style>


<style lang="scss" scoped>
.el-header {
  font-size: 24px;
  color: #fff;
  display: flex;
  background-color: #007ef3;
  line-height: 60px;
  justify-content: space-between;
  .leftbar {
    display: flex;
    align-items: center;
    img {
      flex-shrink: 0;
      height: 28px;
      margin-right: 30px;
    }
  }
}
</style>
