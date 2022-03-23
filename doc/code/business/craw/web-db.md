<font face="Simsun" size=3>

[TOC]

## 三个表

### 1. 主表

字段 | 说明
---|---
id |
uuid |

### 2. 子表

字段 | 说明
---|---
id |
uuid |

### 3. 数据表

字段 | 说明
---|---
id |
uuid | 
parentid | 子表的id
data | 原始数据
type | 1.html 2.pic bytes 3.

##　流程

1. 拿到链接
2. 校验主表是否存在， 如果不存在，添加
3. 存入数据表数据
4. 存入子表链接和标题
5. 处理业务逻辑

### 自定义json

~~~

### root
{
"webroot": "http://www.eyny.com/",
"webname": "acgngame",
"softtype": "web",
"webtype": "h-论坛",
"vip": "0",
username: "",
password: "",
"email": "everforcc@gmail.com"
}



### tag
{
"weburl": "",
"webrootname": "",
"webname": "",
"softtype": "",
"webtype": ""
}
~~~

### 分类

| 分类   |      |
| ------ | ---- |
| 图片   |      |
| 视频   |      |
| 模型   |      |
| 小说   |      |
| 轻小说   |      |
| h-小说 |      |
| h-综合 |      |
| h-视频 |      |
| h-游戏 |      |
| h-二次元 | |
| h-本子 | |
| h-论坛 | |
| 购物   |      |
|论坛| |
|百科| |
|游戏| |
|资源| |
|动漫| |
|漫画| |
|句子| |

</font>