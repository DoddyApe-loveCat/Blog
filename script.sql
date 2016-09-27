-- 数据库初始化脚本
-- 创建数据库
CREATE DATABASE db_blog;
-- 使用数据库
use db_blog;
DROP TABLE IF EXISTS t_blogger;
CREATE TABLE t_blogger (
  id INT(11) NOT NULL AUTO_INCREMENT comment 'id',
  userName VARCHAR(50) DEFAULT NULL comment '用户名',
  password VARCHAR(100) DEFAULT NULL comment '密码',
  PROFILE TEXT comment '个人简介',
  nickName VARCHAR(50) DEFAULT NULL comment '昵称',
  sign VARCHAR(100) DEFAULT NULL comment '个性签名',
  imageName VARCHAR(100) DEFAULT NULL comment '头像',
  PRIMARY KEY (id)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 comment '博主实体表';

INSERT INTO t_blogger(userName,password,PROFILE,nickName,sign,imageName)
values('admin','a66abb5684c45962d887564f08346e8d','等待添加','李威威','简单，诚实。','/static/userImages/liwei.jpg');

DROP TABLE IF EXISTS t_blogtype;
CREATE TABLE t_blogtype(
  id INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  type_name VARCHAR(100) NOT NULL COMMENT '博客类型名称',
  order_no INT(11) NOT NULL COMMENT '排序序号',
  PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '博客类型表';

INSERT INTO t_blogtype(type_name,order_no)
VALUES ('技术积累',1),('个人随笔',2),('转载文章',3),('二次加工',4),('最新动态',5);



drop table if exists t_comment;
create table t_comment(
  id int(11) NOT NULL AUTO_INCREMENT comment 'id',
  PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '评论表';



DROP TABLE IF EXISTS t_link;
CREATE TABLE t_link(
  id INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  linkName VARCHAR(100) DEFAULT NULL COMMENT '链接名称（给用户看到的）',
  linkUrl VARCHAR(200) DEFAULT NULL COMMENT '链接地址（用户点击链接弹出的地址）',
  orderNo INT(11) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '友情链接表';

INSERT INTO t_link(linkName,linkUrl,orderNo)
VALUES('GitHub','https://github.com/',1),
('Stack Overflow','http://stackoverflow.com/',2),
('Spring','http://spring.io/',3),
('The Apache Software Foundation!','http://www.apache.org/',1),
('Redis','http://redis.io/',1);



DROP TABLE IF EXISTS t_blog;
CREATE TABLE t_blog(
  id INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  title VARCHAR(200) DEFAULT NULL COMMENT '博客标题',
  summary VARCHAR(200) DEFAULT NULL COMMENT '博客简介',
  release_date DATETIME DEFAULT NULL COMMENT '博客发布日期',
  click_hit INT(11) DEFAULT NULL COMMENT '点击次数',
  reply_hit INT(11) DEFAULT NULL COMMENT '回复次数',
  content TEXT DEFAULT NULL COMMENT '博客正文内容',
  type_id INT(11) DEFAULT NULL COMMENT '博客类型',
  key_word VARCHAR(200) DEFAULT NULL COMMENT '关键词',
  PRIMARY KEY (id),
  FOREIGN KEY (type_id) REFERENCES t_blogtype(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '博客表';

INSERT INTO t_blog(title,summary,release_date,click_hit,reply_hit,content,type_id,key_word)
VALUES('Spring 特性之一 Ioc','Spring 的摘要','2016-06-01 14:00:00',0,0,'Spring 90% 以上的公司都在使用。',1,'Spring Ioc Aop'),
('使用 Maven 为项目添加依赖','Maven 的摘要','2016-05-01 19:00:00',0,0,'现在写的 Java Web 项目基本都是 Maven 构建的。',1,'Maven 依赖 Web 生命周期'),
('使用 Spring Boot 简化 Spring Web 项目的开发','Spring Boot 的摘要','2016-04-01 22:00:00',0,0,'Spring Boot 使得 Spring 的开发更加简单、高效',1,'Spring Boot Gradle'),
('Gradle 实战','Gradle 的摘要','2016-03-01 08:00:00',0,0,'Gradle 是比 Maven 更新的构建工具，理念上更先进，但是现在还没有全面铺开，因为 Maven 已经足够稳定了。 ',4,'Maven 构建 Gradle'),
('Git 常用操作','Git 的摘要','2016-04-23 19:00:00',0,0,'Git 无疑是当今最最好的版本构建工具。',1,'Git Svn 版本管理工具'),
('Swagger UI 创建可交互的 API 文档','Swagger UI 的摘要','2015-01-01 07:00:00',0,0,'用了 Swagger UI 就不用写文档了。',1,'Swagger UI'),
('多线程的生命周期','多线程编程的摘要','2015-01-14 07:00:00',0,0,'多线程让 Java 变得生不可测，奥妙无穷。',2,'多线程 生命周期 sleep yield wait notify 线程锁'),
('网络编程的 TCP 与 UDP','网络编程的摘要','2015-01-28 07:00:00',0,0,'学会网络编程，你也懂得了 Tomcat。',2,'套接字 TCP UDP'),
('正则表达式基础','正则表达式的摘要','2015-02-01 07:00:00',0,0,'正则表达式是一串强大的字符串。',2,'正则表达式 匹配 分割 查找 定位'),
('Java IO 流的基本使用','Java IO流的摘要','2015-02-12 07:00:00',0,0,'Java 使用流来操作数据。',3,'IO File 装饰器设计模式 缓冲流 字节流 字符流 基础流 高级流'),
('Java 集合类知多少','Java 集合的摘要','2015-03-18 07:00:00',0,0,'Java 的集合类就是一个容器。',3,'List Set Map 线程安全 线程不安全'),
('Java 泛型知多少','Java 泛型的摘要','2015-03-24 07:00:00',0,0,'泛型让一些检查工作提前到编译期。',4,'菱形语法 泛型下限 泛型上线 通配符'),
('Java 设计模式之单例模式','Java 设计模式单例的摘要','2015-04-01 07:00:00',0,0,'单例模式就是只有一个实例的设计模式。',4,'设计模式 单例 静态 私有');


todo 设置外键
drop table if EXISTS t_comment;
CREATE TABLE t_comment(
  id int(11) NOT NULL AUTO_INCREMENT comment '主键 id',
  user_ip varchar(50) DEFAULT NULL comment '用户 ip',
  blog_id int(11) DEFAULT NULL comment '博客 id',
  content VARCHAR(1000) DEFAULT NULL comment '评论内容',
  comment_date datetime DEFAULT NULL comment '评论日期（时间）',
  state int(11) DEFAULT NULL comment '审核状态',
  PRIMARY KEY (id),
  FOREIGN KEY (blog_id) REFERENCES t_blog(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 comment '博客评论表';