
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
  app_Key varchar(32) NOT NULL COMMENT '接入者标识',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  modify_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  contacts varchar(50) NOT NULL COMMENT '联系人',
  mobile varchar(11) DEFAULT NULL COMMENT '手机号码',
  description varchar(512) DEFAULT NULL COMMENT '说明',
  secret varchar(32) DEFAULT NULL COMMENT '接入者密钥',
  state tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 0 禁用  1 可用',
  type tinyint(4) NOT NULL DEFAULT '0' COMMENT '类型 0 普通  1 自己',
  PRIMARY KEY (id),
  UNIQUE KEY app_Key (app_Key)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS developer_api;

CREATE TABLE developer_api (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  developer_id int(11) NOT NULL,
  api_id int(11) NOT NULL,
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  modify_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY de_app_id (developer_id,api_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS developer_api_log;
/**
调用日志
*/
CREATE TABLE developer_api_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  api_id bigint(20) DEFAULT NULL,
  developer_id bigint(20) DEFAULT NULL,
  create_time datetime DEFAULT CURRENT_TIMESTAMP,
  count int(11) DEFAULT NULL COMMENT '调用次数',
  day_time char(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  modify_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY idx_api_de_time (api_id,developer_id,day_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS developer_api_time_log;

CREATE TABLE developer_api_time_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  developer_id bigint(20) DEFAULT NULL,
  api_id bigint(20) DEFAULT NULL,
  time int(11) DEFAULT '0' COMMENT '耗时ms',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

