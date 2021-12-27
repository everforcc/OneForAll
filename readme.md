<font face="SimSun" size=3>

- 目录说明
- doc项目相关文档
- shell脚本
- src源代码
- temp 临时文件，可删

---

- src
- aop 公共的aop，日志类的
- business所有业务都写到这个地方
- config spring的配置
- demo代码示例
- task定时任务
- utils工具类
- constant 

---
- business

---

- config
- [ ] convert spring支持的公共的处理方法
- [ ] data
- [ ] redis公共配置等
- [ ] inti初始化数据
- [ ] yml初始化yml配置文件的数据


---

- 过滤器
- 拦截器
~~~
需要处理的列表
- [ ] 登录
- [ ] 免登录资源配置
- [ ] 跨域处理
~~~
- [ ] 初始化顺序
~~~
@Order(-1)
~~~

---

- 打包
- [ ] maven
- [ ] 更新到服务器
~~~
bootstrap.yml 配置不同环境的启动项
pom profiles
~~~

</font>