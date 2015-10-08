drop table if exists sd_open_developer;

create table sd_open_developer
(
   id                   int not null auto_increment,
   access_id            varchar(32) not null comment '接入者标识',
   create_time          datetime not null default current_timestamp,
   modify_time          datetime not null default current_timestamp on update current_timestamp,
   contacts             varchar(50) not null comment '联系人',
   mobile               varchar(11) comment '手机号码',
   description          varchar(512) comment '说明',
   access_secret        varchar(32) comment '接入者密钥',
   state                tinyint not null default 1 comment '状态 0 禁用  1 可用',
   level_id             int not null comment '等级',
   unique (access_id),
   primary key (id)
);


drop table if exists sd_open_level;

create table sd_open_level
(
   id                   int not null auto_increment,
   name                 varchar(30) not null comment '名称',
   day_call_count       int not null default 0 comment '日调用次数',
   create_time          datetime not null default current_timestamp comment '创建时间',
   modify_time          datetime not null default current_timestamp on update current_timestamp comment '更新时间' ,
   primary key (id)
);


drop table if exists sd_open_api;

create table sd_open_api
(
   id                   int not null auto_increment,
   version              varchar(5) not null comment '版本号',
   method               varchar(30) not null comment '方法',
   title                varchar(128) not null comment '说明',
   create_time          datetime not null default current_timestamp comment '创建时间',
   modify_time          datetime not null default current_timestamp on update current_timestamp comment '更新时间',
   state                tinyint not null default 1 comment '状态 0 无效 1 有效',
   unique (method,version),
   primary key (id)
);
