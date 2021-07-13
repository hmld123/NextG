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
'权限表';

comment on column sys_role.role_pk is
'角色主键';

comment on column sys_role.role_name is
'角色名称';

comment on column sys_role.role_key is
'角色键值';

comment on column sys_role.role_sort is
'显示顺序';

comment on column sys_role.status is
'状态（0正常 1停用）';

comment on column sys_role.del_flag is
'删除标志（0代表存在 2代表删除）';

comment on column sys_role.update_time is
'更新时间';

comment on column sys_role.update_by is
'更新人';

comment on column sys_role.create_time is
'创建时间';

comment on column sys_role.create_by is
'创建人';

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
'用户表';

comment on column sys_user.user_pk is
'用户主键';

comment on column sys_user.user_name is
'用户名';

comment on column sys_user.nick_name is
'昵称';

comment on column sys_user.status is
'状态（0正常 1停用）';

comment on column sys_user.del_flag is
'删除标志（0代表存在 2代表删除）';

comment on column sys_user.update_time is
'更新时间';

comment on column sys_user.update_by is
'更新人';

comment on column sys_user.create_time is
'创建时间';

comment on column sys_user.create_by is
'创建人';

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
'用户密码变动历史表';

comment on column sys_user_password_history.user_history_pk is
'用户变动历史主键';

comment on column sys_user_password_history.user_pk is
'用户主键';

comment on column sys_user_password_history.user_name is
'用户名';

comment on column sys_user_password_history.nick_name is
'昵称';

comment on column sys_user_password_history.user_password is
'密码';

comment on column sys_user_password_history.salt is
'盐';

comment on column sys_user_password_history.status is
'状态(0代表正常，1代表停用)';

comment on column sys_user_password_history.update_time is
'密码更新时间';

comment on column sys_user_password_history.update_by is
'密码更新人';

comment on column sys_user_password_history.create_time is
'创建时间';

comment on column sys_user_password_history.create_by is
'创建人';

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
'用户权限表';

comment on column sys_user_role.sur_pk is
'用户权限主键';

comment on column sys_user_role.user_pk is
'用户主键';

comment on column sys_user_role.role_pk is
'角色主键';

comment on column sys_user_role.status is
'状态（0正常 1停用）';

comment on column sys_user_role.del_flag is
'删除标志（0代表存在 2代表删除）';

comment on column sys_user_role.update_time is
'更新时间';

comment on column sys_user_role.update_by is
'更新人';

comment on column sys_user_role.create_time is
'创建时间';

comment on column sys_user_role.create_by is
'创建人';

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

