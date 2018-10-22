// 引入配置中导出的对象
import {instance} from '@/axiosConfig.js'

// 设置一个对象，属性r为请求数据
let getdata = {
    r: (params, cancelToke) = > {
    return instance.post(
        '/mock/5aa8bd8dbefc9a434dd948cf/list/postarg',
        params,
        cancelToke
    )
}
}

export {getdata}
