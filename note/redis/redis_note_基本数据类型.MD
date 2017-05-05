## redis 基本数据类型
- strings
- hashes
- lists
- sets
- sorted sets
### 1. strings
#### 1.1 类型
 string 最基本的类型,其定义如下:
 ```
 struct sdshdr{
    long len;
    long free;
    char buf[];
 };
 ```
string类型为二进制安全的,最大上限为1G字节
#### 1.2 操作
##### 1.2.1 set
##### 1.2.2 setnx
##### 1.2.3 setex
##### 1.2.4 setrange
> 设定子字符串,从下标开始(包含下标)替换
##### 1.2.5 mset
