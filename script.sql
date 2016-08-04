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
values('liwei','be78263da332dc2c7005f7551d7e57cd',null,'李威威','生活简单，做人诚实。',null);


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
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comemnt '评论表';



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

