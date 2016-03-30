CREATE TABLE sys_user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_name varchar(20) DEFAULT NULL COMMENT '用户名',
  email varchar(30) DEFAULT NULL COMMENT '邮箱',
  phone varchar(11) DEFAULT NULL COMMENT '手机号码',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  modify_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  state tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 1 正常 0 禁用',
  type tinyint(4) NOT NULL DEFAULT '1' COMMENT '类型 1 普通用户 2 管理员',
  password varchar(30) NOT NULL COMMENT '密码',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE sys_resources (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(20) DEFAULT NULL COMMENT '名称',
  url varchar(50) DEFAULT NULL COMMENT 'url',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  modify_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  parent_id bigint(20) NOT NULL DEFAULT '0' COMMENT '父编码',
  identify varchar(5) NOT NULL COMMENT '识别码',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE sys_role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(30) DEFAULT NULL COMMENT '名称',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  modify_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE options (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  opt_key varchar(20) NOT NULL COMMENT 'key',
  opt_values varchar(2048) NOT NULL COMMENT '实际值',
  descriptions varchar(512) DEFAULT NULL COMMENT '说明',
  opt_title varchar(128) DEFAULT NULL COMMENT '显示值',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



