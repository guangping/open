
drop index idx_mobile on sys_user;

drop index idx_email on sys_user;

drop index idx_name on sys_user;

drop table if exists sys_user;

create table sys_user
(
   id                   int not null auto_increment,
   name                 varchar(15) not null comment '名称',
   mobile               varchar(11) not null comment '电话',
   password             varchar(20) not null comment '密码',
   email                varchar(20) not null comment '邮箱',
   role                 tinyint not null default 0 comment '角色: 0 普通用户 1 系统管理员',
   sort                 int not null default 0,
   create_time          datetime not null default CURRENT_TIMESTAMP,
   modify_time          datetime default CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP,
   state                tinyint not null default 0 comment '状态: 0 正常 1 封存 2 删除',
   primary key (id)
);

create unique index idx_name on sys_user
(
   name
);

create unique index idx_email on sys_user
(
   email
);

create unique index idx_mobile on sys_user
(
   mobile
);
