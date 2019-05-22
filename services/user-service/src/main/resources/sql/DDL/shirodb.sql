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
   id                   varchar(64) not null comment '����',
   per_name             varchar(32) not null comment '��Դ����',
   per_type             varchar(32) not null comment '��Դ���ͣ�menu,button',
   url                  varchar(128) comment '����url��ַ',
   per_code             varchar(64) comment 'Ȩ�޴����ַ���',
   parent_id            int comment '�����id',
   parent_ids           varchar(64) comment '�����id�б�',
   sortstring           varchar(64) comment '�����',
   is_available         varchar(1) comment '�Ƿ����,1�����ã�0������',
   gmt_create           datetime comment '����ʱ��',
   gmt_modified         datetime comment '�޸�ʱ��',
   primary key (id)
);

alter table sys_permission comment 'ϵͳȨ�ޱ�';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   varchar(64) not null comment '����',
   role_name            varchar(32) not null comment '��ɫ��',
   is_available         varchar(1) comment '�Ƿ����,1�����ã�0������',
   gmt_create           datetime comment '����ʱ��',
   gmt_modified         datetime comment '�޸�ʱ��',
   primary key (id)
);

alter table sys_role comment 'ϵͳ��ɫ��';

/*==============================================================*/
/* Table: sys_role_permission                                   */
/*==============================================================*/
create table sys_role_permission
(
   id                   varchar(64) not null comment '����',
   sys_role_id          int not null comment '��ɫid',
   sys_permission_id    int not null comment 'Ȩ��id',
   gmt_create           datetime comment '����ʱ��',
   gmt_modified         datetime comment '�޸�ʱ��',
   primary key (id)
);

alter table sys_role_permission comment '��ɫȨ�޹�ϵ��';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   varchar(64) not null comment '����',
   email                varchar(64) comment '����',
   username             varchar(64) comment '�û���',
   password             varchar(64) comment '����',
   phone_number         varchar(16) comment '��ϵ�绰',
   is_locked            varchar(1) comment '�˺��Ƿ�������1��������0δ����',
   last_password_reset_date datetime comment '�ϴ���������ʱ��',
   gmt_create           datetime comment '����ʱ��',
   gmt_modified         datetime comment '�޸�ʱ��',
   primary key (id)
);

alter table sys_user comment 'ϵͳ�û���';

/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role
(
   id                   varchar(64) not null comment '����',
   sys_user_id          int not null comment '�û�id',
   sys_role_id          int not null comment '��ɫid',
   gmt_create           datetime comment '����ʱ��',
   gmt_modified         datetime comment '�޸�ʱ��',
   primary key (id)
);

alter table sys_user_role comment '�û���ɫ��ϵ��';

