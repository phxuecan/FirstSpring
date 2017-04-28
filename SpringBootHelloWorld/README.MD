## 项目简介
这是学习Spring Boot练习项目,主要学习如何通过Spring Boot快速构建Web项目,主要涉及:
- 基础框架:Spring Boot
- 页面渲染: Thymeleaf + Vue
- 数据库: h2

主要实现简单用户注册功能

## 学习内容
- Spring Boot中添加资源
- 如何定义错误页面

### 静态资源
Spring Boot的Web自动配置所定义的Spring MVC Bean中包含了一个资源处理器resource handler,
它会将"/**"映射到几个资源路径中.这些资源路径主要有:
- /META-INF/resources/
- /resources/
- /static/
- /public/
### 定义错误页面
