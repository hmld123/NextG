/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     2021/7/13 21:32:47                           */
/*==============================================================*/


drop table sys_role;

drop table sys_user;

drop table sys_user_password_history;

drop table sys_user_role;

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role (
   role_pk              VARCHAR(50)          not null,
   role_name            VARCHAR(20)          not null,
   role_key             VARCHAR(100)         not null,
   role_sort            NUMERIC(4)           not null default 0,
   status               CHAR(1)              not null default '0',
   del_flag             CHAR(1)              null default '0',
   update_time          TIMESTAMP            not null,
   update_by            VARCHAR(20)          not null,
   create_time          TIMESTAMP            not null,
   create_by            VARCHAR(20)          not null,
   constraint PK_SYS_ROLE primary key (role_pk),
   constraint AK_QU_ROLE_KEY_SYS_ROLE unique (role_key)
);

comment on table sys_role is
'Ȩ�ޱ�';

comment on column sys_role.role_pk is
'��ɫ����';

comment on column sys_role.role_name is
'��ɫ����';

comment on column sys_role.role_key is
'��ɫ��ֵ';

comment on column sys_role.role_sort is
'��ʾ˳��';

comment on column sys_role.status is
'״̬��0���� 1ͣ�ã�';

comment on column sys_role.del_flag is
'ɾ����־��0������� 2����ɾ����';

comment on column sys_role.update_time is
'����ʱ��';

comment on column sys_role.update_by is
'������';

comment on column sys_role.create_time is
'����ʱ��';

comment on column sys_role.create_by is
'������';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user (
   user_pk              VARCHAR(20)          not null,
   user_name            VARCHAR(20)          not null,
   nick_name            VARCHAR(20)          not null,
   status               CHAR(1)              not null default '0',
   del_flag             CHAR(1)              null default '0',
   update_time          TIMESTAMP            not null,
   update_by            VARCHAR(20)          not null,
   create_time          TIMESTAMP            not null,
   create_by            VARCHAR(20)          not null,
   constraint PK_SYS_USER primary key (user_pk),
   constraint AK_UQ_USER_NAME_SYS_USER unique (user_name)
);

comment on table sys_user is
'�û���';

comment on column sys_user.user_pk is
'�û�����';

comment on column sys_user.user_name is
'�û���';

comment on column sys_user.nick_name is
'�ǳ�';

comment on column sys_user.status is
'״̬��0���� 1ͣ�ã�';

comment on column sys_user.del_flag is
'ɾ����־��0������� 2����ɾ����';

comment on column sys_user.update_time is
'����ʱ��';

comment on column sys_user.update_by is
'������';

comment on column sys_user.create_time is
'����ʱ��';

comment on column sys_user.create_by is
'������';

/*==============================================================*/
/* Table: sys_user_password_history                             */
/*==============================================================*/
create table sys_user_password_history (
   user_history_pk      VARCHAR(20)          not null,
   user_pk              VARCHAR(20)          null,
   user_name            VARCHAR(20)          not null,
   nick_name            VARCHAR(20)          not null,
   user_password        VARCHAR(255)         not null,
   salt                 VARCHAR(8)           not null,
   status               CHAR(1)              not null default '0',
   update_time          TIMESTAMP            not null,
   update_by            VARCHAR(20)          not null,
   create_time          TIMESTAMP            not null,
   create_by            VARCHAR(20)          not null,
   constraint PK_SYS_USER_PASSWORD_HISTORY primary key (user_history_pk)
);

comment on table sys_user_password_history is
'�û�����䶯��ʷ��';

comment on column sys_user_password_history.user_history_pk is
'�û��䶯��ʷ����';

comment on column sys_user_password_history.user_pk is
'�û�����';

comment on column sys_user_password_history.user_name is
'�û���';

comment on column sys_user_password_history.nick_name is
'�ǳ�';

comment on column sys_user_password_history.user_password is
'����';

comment on column sys_user_password_history.salt is
'��';

comment on column sys_user_password_history.status is
'״̬(0����������1����ͣ��)';

comment on column sys_user_password_history.update_time is
'�������ʱ��';

comment on column sys_user_password_history.update_by is
'���������';

comment on column sys_user_password_history.create_time is
'����ʱ��';

comment on column sys_user_password_history.create_by is
'������';

/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role (
   sur_pk               VARCHAR(20)          not null,
   user_pk              VARCHAR(20)          null,
   role_pk              VARCHAR(50)          null,
   status               CHAR(1)              not null default '0',
   del_flag             CHAR(1)              null default '0',
   update_time          TIMESTAMP            not null,
   update_by            VARCHAR(20)          not null,
   create_time          TIMESTAMP            not null,
   create_by            VARCHAR(20)          not null,
   constraint PK_SYS_USER_ROLE primary key (sur_pk)
);

comment on table sys_user_role is
'�û�Ȩ�ޱ�';

comment on column sys_user_role.sur_pk is
'�û�Ȩ������';

comment on column sys_user_role.user_pk is
'�û�����';

comment on column sys_user_role.role_pk is
'��ɫ����';

comment on column sys_user_role.status is
'״̬��0���� 1ͣ�ã�';

comment on column sys_user_role.del_flag is
'ɾ����־��0������� 2����ɾ����';

comment on column sys_user_role.update_time is
'����ʱ��';

comment on column sys_user_role.update_by is
'������';

comment on column sys_user_role.create_time is
'����ʱ��';

comment on column sys_user_role.create_by is
'������';

alter table sys_user_password_history
   add constraint FK_SYS_USER_REFERENCE_SYS_USER foreign key (user_pk)
      references sys_user (user_pk)
      on delete restrict on update restrict;

alter table sys_user_role
   add constraint FK_SYS_USER_REFERENCE_SYS_USER foreign key (user_pk)
      references sys_user (user_pk)
      on delete restrict on update restrict;

alter table sys_user_role
   add constraint FK_SYS_USER_REFERENCE_SYS_ROLE foreign key (role_pk)
      references sys_role (role_pk)
      on delete restrict on update restrict;

