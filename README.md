# 数据模拟工具(data-tool)




## 介绍

data-tool是一套基于Spring Boot/Netty/Vue/PostgreSql实现的数据模拟工具,功能包含模拟实时数据,实时事件,响应控制命令

## 打包

项目采用前后端集中打包部署方式,

前端使用nodeJs管理依赖  webpack打包

后端使用Maven管理依赖   assembly打包

必要条件:打包所在机器需安装Maven 3.x 和NodeJS 8.x

1 前端依赖下载 

  进入src/vue目录执行
  
  ```bash
  npm install
  ```
  
2 前端打包 

  执行命令
  
  ```bash
  npm run build
  ``` 

3 后端打包

  执行命令
   
  ```bash
  mvn clean package
  ``` 



## 运行

  解压target目录下的ZIP包
  
  Linux环境进入bin/unix下运行脚本
  
  ```bash
    ./run.sh start
  ```
  
  Windows环境进入bin/win下双击run.bat脚本
 





## License

[MIT](http://opensource.org/licenses/MIT)

Copyright (c) 2017-present, agioe
