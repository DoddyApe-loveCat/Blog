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
values('liwei','be78263da332dc2c7005f7551d7e57cd',null,null,null,null);


drop table if exists t_blogtype;
create table t_comment(
  id int(11) NOT NULL AUTO_INCREMENT comment 'id',
  PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comemnt '博客类型表';

drop table if exists t_comment;
create table t_comment(
  id int(11) NOT NULL AUTO_INCREMENT comment 'id',
  PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comemnt '评论表';


drop table of exista t_link;
create table t_link(
  id int(11) NOT NULL AUTO_INCREMENT comment 'id',
  PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 comment '友情链接表';