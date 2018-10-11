<template>
  <el-container>
    <!-- el-aside -->
    <el-aside width="210px">
      <el-menu :default-active="activeIndex"
               :collapse="isCollapse"
               router>
        <el-menu-item index="/systemConfig/basciSeting">基础设置</el-menu-item>
        <el-submenu index="/systemConfig">
          <template slot="title">设备信息</template>
          <el-menu-item index="/systemConfig/equipmentType">设备类型</el-menu-item>
          <el-menu-item index="/systemConfig/equipmentSignal">设备监控信号</el-menu-item>
          <el-menu-item index="/systemConfig/equipmentSignalTemplate">监控信号模板</el-menu-item>
          <el-menu-item index="/systemConfig/equipmentTemplateRelation">信号模板关联</el-menu-item>
          <el-menu-item index="/systemConfig/upperNodeInfo">上层节点信息</el-menu-item>
          <el-menu-item index="/systemConfig/equipmentInfo">设备信息</el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>

    <!-- el-main -->
    <el-main>
      <router-view></router-view>
    </el-main>

  </el-container>
</template>

<script>
export default {
  data() {
    return {
      activeIndex: '/systemConfig/basciSeting', // 默认选中栏目
      isCollapse: false
    }
  },
  methods: {},

  /*
    01:路由切换时：根据路由，获取高亮选中的栏目
  */
  beforeRouteUpdate(to, from, next) {
    this.activeIndex = to.path
    next()
  },

  /*
    01: 页面刷新时：根据路由，获取高亮选中的栏目
   */
  created() {
    this.activeIndex = this.$route.path
  }
}
</script>

<style lang="scss" scoped>
.el-aside {
  border-right: 1px solid #ddd;
  .el-menu {
    border: none;
  }
}
.el-main {
  padding: 20px;
  position: relative;
}
</style>
