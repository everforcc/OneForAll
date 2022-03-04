<font face="SimSun" size=3>

- 实现下常用的功能
- [对着官网的文档做](https://docs.spring.io/spring-boot/docs/current/reference/html/index.html)

功能 | 使用方式
---|---
@Pattern | 正则 需要类上@Validated 方法参数@Valid
@@Validated | 类注解
@Valid | 参数
@NotNull(message = "【fileSystemDto】不能为null")| 方法调用上
@JSONField(format = "yyyy-MM-dd HH:mm:ss") | 代码 AppConfig fastJsonHttpMessageConverter()
@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") | sql插入返回id
@Transactional(rollbackFor = Exception.class) |　如果使用 try catch 需要 在catch中 抛出异常

### sql

- 插入后返回id
~~~
-- 注解
@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
-- xml
<insert id="insert" useGeneratedKeys="true" keyProperty="id"  keyColumn="id">
~~~

### 事务

- @Transactional(rollbackFor = Exception.class)
~~~
自定义异常，可以下载rollbackfor中
~~~

### 邮件

- [参考](http://springboot.javaboy.org/2019/0717/springboot-mail)
- 配置文件
~~~
配置 SMTP 服务器地址
spring.mail.host=smtp.qq.com
SMTP 服务器的端口
spring.mail.port=587
配置邮箱用户名
spring.mail.username=1510161612@qq.com
配置密码，注意，不是真正的密码，而是刚刚申请到的授权码
spring.mail.password=ubknfzhjkhrbbabe
默认的邮件编码
spring.mail.default-encoding=UTF-8
配饰 SSL 加密工厂
spring.mail.properties.mail.smtp.socketFactoryClass=javax.net.ssl.SSLSocketFactory
表示开启 DEBUG 模式，这样，邮件发送过程的日志会在控制台打印出来，方便排查错误
spring.mail.properties.mail.debug=true
~~~

### 外部配置

- 图床 PicGo gitee
- 

### idea目录说明

1. .gitignore：分布式版本控制系统git的配置文件，意思为忽略提交
   在 .gitingore 文件中，遵循相应的语法，即在每一行指定一个忽略规则。 如：.log、/target/、.idea
2. mvnw：全名是maven wrapper的文件
   它的作用是在maven-wrapper.properties文件中记录你要使用的maven版本，当用户执行mvnw clean 命令时，发现当前用户的maven版本和期望的版本不一致，那么就下载期望的版本，然后用期望的版本来执行mvn命令，比如mvn clean命令。
3. mvn文件夹：存放mvnw相关文件
   存放着maven-wrapper.properties和相关jar包以及名为MavenWrapperDownloader的java文件
4. mvn.cmd：执行mvnw命令的cmd入口
   *注：mvnw文件适用于Linux（bash），mvnw.cmd适用于Windows 环境。
5. .iml文件：intellij idea的工程配置文件
   里面包含当前project的一些配置信息，如模块开发的相关信息，比如java组件，maven组件，插件组件等，还可能会存储一些模块路径信息，依赖信息以及一些别的信息。
6. .idea文件夹：存放项目的配置信息
   包括数据源，类库，项目字符编码，历史记录，版本控制信息等。
7. pom.xml：项目对象模型（核心重要）
   pom.xml主要描述了项目的maven坐标，依赖关系，开发者需要遵循的规则，缺陷管理系统，组织和licenses，以及其他所有的项目相关因素，是项目级别的配置文件。


</font>