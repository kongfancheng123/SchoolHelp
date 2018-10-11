<template>
  <el-container>
    <!-- el-aside -->
    <el-aside width="200px">
      <el-menu :default-active="activeIndex"
               @select="handleSelect"
               router>
        <el-menu-item index="/systemConfig">基础设置</el-menu-item>
        <el-submenu index="">
          <template slot="title">设备信息</template>
          <el-menu-item index="/systemConfig/equipmentInfo/equipmentType">设备类型</el-menu-item>
          <el-menu-item index="/systemConfig/equipmentInfo/equipmentSignal">设备监控信号</el-menu-item>
          <el-menu-item index="/systemConfig/equipmentInfo/equipmentSignalTemplate">监控信号模板</el-menu-item>
          <el-menu-item index="/systemConfig/equipmentInfo/equipmentTemplateRelation">信号模板关联</el-menu-item>
          <el-menu-item index="/systemConfig/equipmentInfo/upperNodeInfo">上层节点信息</el-menu-item>
          <el-menu-item index="/systemConfig/equipmentInfo/equipmentInfo">设备信息</el-menu-item>
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
      activeIndex: '/basciSeting'
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath)
    }
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
