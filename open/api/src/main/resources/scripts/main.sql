
CREATE DATABASE /*!32312 IF NOT EXISTS*/api /*!40100 DEFAULT CHARACTER SET utf8 */;

USE api;


DROP TABLE IF EXISTS api;

CREATE TABLE api (
  id int(11) NOT NULL AUTO_INCREMENT,
  method varchar(30) NOT NULL COMMENT '方法',
  title varchar(128) NOT NULL COMMENT '说明',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  modify_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  state tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 0 无效 1 有效',
  PRIMARY KEY (id),
  UNIQUE KEY method (method)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table developer */

DROP TABLE IF EXISTS developer;

CREATE TABLE developer (
  id int(11) NOT NULL AUTO_INCREMENT,
  appKey varchar(32) NOT NULL COMMENT '接入者标识',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  modify_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  contacts varchar(50) NOT NULL COMMENT '联系人',
  mobile varchar(11) DEFAULT NULL COMMENT '手机号码',
  description varchar(512) DEFAULT NULL COMMENT '说明',
  secret varchar(32) DEFAULT NULL COMMENT '接入者密钥',
  state tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 0 禁用  1 可用',
  PRIMARY KEY (id),
  UNIQUE KEY appKey (appKey)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


