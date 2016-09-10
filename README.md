HelloSellerCloud
====
一个用户的增删改查的小demo
涉及的技术 : SpringBoot, SpringJdbcTemplate, Freemarker, daterangepicker等等
后台数据库采用的是mysql

maven 编译好的jar为info目录下面的"HelloSellerCloud-0.0.1-SNAPSHOT.jar"

QuickStart
---
1 克隆项目

![design_picure](https://raw.githubusercontent.com/970655147/HelloSellerCloud/master/readMeRes/1st_clone.png)


2 导入项目为maven项目

![design_picure](https://raw.githubusercontent.com/970655147/HelloSellerCloud/master/readMeRes/2nd_import.png)
这里会有一个小错误, 是由于导入的jdk版本太低, 不支持"<>"特性, 将jdk类库更新为1.7, 然后将compile level更新为1.7就行了
不知道在大家的机器上面会不会出现这个问题, 反正 我这里是有[我也很疑惑 似乎是因为创建项目的时候, 没有提示选择jdk的版本吧, 似乎是选了默认的]


3 然后创建数据库, 创建对应的表, 插入测试数据


4 配置"./src/main/resources/application.properties"的数据库相关的配置 以及开放的端口的配置

![design_picure](https://raw.githubusercontent.com/970655147/HelloSellerCloud/master/readMeRes/4th_updateConfig.png)

5 启动项目, run "./src/main/java/com/hx/sellerCloud/boot/SpringBootSimpleApp"

![design_picure](https://raw.githubusercontent.com/970655147/HelloSellerCloud/master/readMeRes/5th_boot.png)

6. 测试项目的相关功能

访问 "http://localhost:8088/index.html" , 实现的几个基本的业务功能都可以通过index进行访问
![design_picure](https://raw.githubusercontent.com/970655147/HelloSellerCloud/master/readMeRes/6th_index.png)

---------------

相关功能的小演示

1. CD

![design_picure](https://raw.githubusercontent.com/970655147/HelloSellerCloud/master/readMeRes/CD.gif)
创建用户有一个镜头是输入的name为空, 然后跳了一个错误对吧, 这是被UserInterceptor拦截了, 我这里为了简单处理, 就直接重定向到错误页面了


2. UR

![design_picure](https://raw.githubusercontent.com/970655147/HelloSellerCloud/master/readMeRes/UR.gif)

ok 项目的构建到这里就结束了, 至于功能的实现, 业务的处理, 请详见代码!



	
