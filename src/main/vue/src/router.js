import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

let router = new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      meta: {
        title: '登录'
      },
      component: () => import('./views/login.vue'),
      alias: '/login'
    },
    {
      path: '/comprehensive',
      component: () => import('./views/index.vue'),
      children: [
        {
          path: '',
          name: 'Comprehensive',
          meta: {
            title: '综合展示'
          },
          component: () => import('./views/comprehensive/comprehensive.vue')
        },
        {
          path: '/systemConfig',
          component: () => import('./views/systemConfig/index.vue'),
          redirect: '/systemConfig/basciSeting',
          children: [
            {
              path: 'basciSeting',
              name: 'BasciSeting',
              meta: {
                title: '基础设置'
              },
              component: () =>
                import('./views/systemConfig/basciSeting/basciSeting.vue')
            },
            {
              path: 'equipmentType',
              name: 'EquipmentType',
              meta: {
                title: '设备类型'
              },
              component: () =>
                import('./views/systemConfig/equipmentInfo/equipmentType.vue')
            },
            {
              path: 'equipmentSignal',
              name: 'EquipmentSignal',
              meta: {
                title: '设备监控信号'
              },
              component: () =>
                import('./views/systemConfig/equipmentInfo/equipmentSignal.vue')
            },
            {
              path: 'equipmentSignalTemplate',
              name: 'EquipmentSignalTemplate',
              meta: {
                title: '监控信号模板'
              },
              component: () =>
                import('./views/systemConfig/equipmentInfo/equipmentSignalTemplate.vue')
            },
            {
              path: 'equipmentTemplateRelation',
              name: 'EquipmentTemplateRelation',
              meta: {
                title: '信号模板关联'
              },
              component: () =>
                import('./views/systemConfig/equipmentInfo/equipmentTemplateRelation.vue')
            },
            {
              path: 'upperNodeInfo',
              name: 'UpperNodeInfo',
              meta: {
                title: '上层节点信息'
              },
              component: () =>
                import('./views/systemConfig/equipmentInfo/upperNodeInfo.vue')
            },
            {
              path: 'equipmentInfo',
              name: 'EquipmentInfo',
              meta: {
                title: '上层节点信息'
              },
              component: () =>
                import('./views/systemConfig/equipmentInfo/equipmentInfo.vue')
            }
          ]
        },
        {
          path: '/log',
          name: 'Log',
          meta: {
            title: '实时日志'
          },
          component: () => import('./views/log/log.vue')
        }
      ]
    },

    {
      path: '*',
      name: 'Error',
      meta: {
        title: '404页面不存在'
      },
      component: () => import('./views/404.vue')
    }
  ]
})

// 全局路由卫士，设置文档title
router.beforeEach((to, from, next) => {
  document.title = to.meta.title
  next()
})

export default router
