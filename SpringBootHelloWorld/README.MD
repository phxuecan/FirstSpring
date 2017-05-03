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

以上方式都是通过默认的ResourceHttpRequestHandler来进行映射处理.
### 定义错误页面
web请求中,可能会发生像404之类的错误,这里要讨论的是关于spring boot时如何来处理这样的错误,首先
当我们请求一个并没有定义的接口,如:192.168.1.32:8080/contacts-q
会发现出现如下页面:
```
Whitelabel Error Page

This application has no explicit mapping for /error, so you are seeing this as a fallback.

Wed May 03 12:10:20 CST 2017
There was an unexpected error (type=Not Found, status=404).
No message available
```
**为什么会显示这样的页面呐?**  
由于spring boot对异常请求进行了统一的的处理,具体见:BasicErrorController,由于我们没有定义error.html处理页面,
系统就会显示无法找到对应的资源,而显示这个页面,这里我们将资源都是放在public下,所以我们只需要在public下添加error.html页面就行,添加内容如下的error.html页面:
```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <title>统一错误处理</title>
</head>
<body>
    <h1>抱歉,出错了</h1>
    <div th:text="${url}"></div>
    <div th:text="${message}"></div>
</body>
</html>
```
重新运行应用,再输入如上请求,则展示:
```
Error Handler

No message available
```
#### 通过定制EmbeddedServletContainerCustomizer来添加错误页面
另外一种方式,则通过定制EmbeddedServletContainerCustomizer来添加错误页面:
```
@Bean
    public EmbeddedServletContainerCustomizer containerCustomizer()
    {
        return configurableEmbeddedServletContainer ->
        {
            configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400.html"));
            configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
        };
    }
```