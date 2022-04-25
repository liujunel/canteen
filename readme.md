## 学院饭堂管理后台系统（本人2020年的毕业设计）
### 技术架构
- springboot
- mybatis-plus
- spring
- shiro
- layui
- mysql

### 部署步骤
1. 在mysql中新建一个 **canteen** 的数据库
2. 将 **canteen.sql** 脚本导入到canteen的数据库中
3. 使用IDEA导入 **canteen** 项目
4. 等待下载该项目所依赖的pom（前提要在IDEA中配置好maven，至于如何配置maven请找度娘）
5. 使用maven重新编译该项目
6. 修改配置文件 **application.yml** 的数据库连接信息（比如ip、端口、数据库、数据库账号、数据库密码等）
7. 找到 **CanteenApplication** 主文件启动即可
8. 访问地址：http://localhost:8080
   1. 管理员的账号：8888
   2. 管理员的密码：123456