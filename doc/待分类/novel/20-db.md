<font face="Simsun" size=3>

[TOC]

### 章节拆分

- [ ] 常见小说内容和章节混搭在一起，用正则拆分为多个章节保存

### Dto-db

- 对象字段 
- [ ] 主表 cc_novel

字段 | 说明
---|---
id | 
name | 小说名
sourceurl | 小说来源
sourcename | 来源网站名

- [ ] 子表 cc_novel_capter

字段 | 说明
---|---
id |
parentid | 小说名id
contentid | 内容数据id
captername | 章节名
contenttype | 1 原始html，2处理后的html可以放入markdown，3 处理为txt
sourceurl | 小说章节来源
seq | 单个小说排序, 同一个parentid下seq,contenttype不能重复


</font>