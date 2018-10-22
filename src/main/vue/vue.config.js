module.exports = {
    // 子路径 =》 https://www.my-app.com/【my-app】/
    baseUrl: '',

    // 输出文件目录
    outputDir: 'dist',

    // assetsDir: "/app",

    // index.html(名字) 以及输出路径 (相对于 outputDir)
    indexPath: 'index.html',

    // Vue 组件中使用 template 选项，但是这会让你的应用额外增加 10kb 左右
    runtimeCompiler: true,

    // 是否在保存的时候使用 `eslint-loader` 进行检查。
    // 有效的值：`ture` | `false` | `"error"`
    // 当设置为 `"error"` 时，检查出的错误会触发编译失败。
    lintOnSave: true,

    // 生产环境不需要 source map,可加速打包
    productionSourceMap: false,

    // CSS 相关选项
    css: {
        // 是否开启 CSS source map？
        sourceMap: false
    },

    // 在生产环境下为 Babel 和 TypeScript 使用 `thread-loader`
    // 在多核机器下会默认开启。
    parallel: require('os').cpus().length > 1,

    // webpack设置
    // configureWebpack: config => {
    //   if (env === "production") {
    //     // 为生产环境修改配置...
    //   } else {
    //     // 为开发环境修改配置...
    //   }
    // },

    // 配置 webpack-dev-server 行为
    devServer: {
        port: 9000, // 端口
        host: '0.0.0.0', // IP
        open: true // 自动打开浏览器
        // proxy: {
        //   '/api': 'http://api.zhuishushenqi.com'
        // }
    }
}
