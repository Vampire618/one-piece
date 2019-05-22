/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/5/22 17:09:48                           */
/*==============================================================*/


drop table if exists sys_permission;

drop table if exists sys_role;

drop table if exists sys_role_permission;

drop table if exists sys_user;

drop table if exists sys_user_role;

/*==============================================================*/
/* Table: sys_permission                                        */
/*==============================================================*/
create table sys_permission
(
   id                   varchar(64) not null comment '主键',
   per_name             varchar(32) not null comment '资源名称',
   per_type             varchar(32) not null comment '资源类型：menu,button',
   url                  varchar(128) comment '访问url地址',
   per_code             varchar(64) comment '权限代码字符串',
   parent_id            int comment '父结点id',
   parent_ids           varchar(64) comment '父结点id列表串',
   sortstring           varchar(64) comment '排序号',
   is_available         varchar(1) comment '是否可用,1：可用，0不可用',
   gmt_create           datetime comment '创建时间',
   gmt_modified         datetime comment '修改时间',
   primary key (id)
);

alter table sys_permission comment '系统权限表';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   varchar(64) not null comment '主键',
   role_name            varchar(32) not null comment '角色名',
   is_available         varchar(1) comment '是否可用,1：可用，0不可用',
   gmt_create           datetime comment '创建时间',
   gmt_modified         datetime comment '修改时间',
   primary key (id)
);

alter table sys_role comment '系统角色表';

/*==============================================================*/
/* Table: sys_role_permission                                   */
/*==============================================================*/
create table sys_role_permission
(
   id                   varchar(64) not null comment '主键',
   sys_role_id          int not null comment '角色id',
   sys_permission_id    int not null comment '权限id',
   gmt_create           datetime comment '创建时间',
   gmt_modified         datetime comment '修改时间',
   primary key (id)
);

alter table sys_role_permission comment '角色权限关系表';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   varchar(64) not null comment '主键',
   email                varchar(64) comment '邮箱',
   username             varchar(64) comment '用户名',
   password             varchar(64) comment '密码',
   phone_number         varchar(16) comment '联系电话',
   is_locked            varchar(1) comment '账号是否锁定，1：锁定，0未锁定',
   last_password_reset_date datetime comment '上次密码重置时间',
   gmt_create           datetime comment '创建时间',
   gmt_modified         datetime comment '修改时间',
   primary key (id)
);

alter table sys_user comment '系统用户表';

/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role
(
   id                   varchar(64) not null comment '主键',
   sys_user_id          int not null comment '用户id',
   sys_role_id          int not null comment '角色id',
   gmt_create           datetime comment '创建时间',
   gmt_modified         datetime comment '修改时间',
   primary key (id)
);

alter table sys_user_role comment '用户角色关系表';

