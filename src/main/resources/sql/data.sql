--权限
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '用户查询', NOW(), 'sys_user_select',0);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '用户添加', NOW(), 'sys_user_add',1);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '用户删除', NOW(), 'sys_user_delete',1);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '用户修改', NOW(), 'sys_user_update',1);

insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '权限查询', NOW(), 'sys_authority_select',0);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '权限添加', NOW(), 'sys_authority_add',5);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '权限删除', NOW(), 'sys_authority_delete',5);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '权限修改', NOW(), 'sys_authority_update',5);

insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '角色权限查询', NOW(), 'sys_roleAuthority_select',0);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '角色权限添加', NOW(), 'sys_roleAuthority_add',9);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '角色权限删除', NOW(), 'sys_roleAuthority_delete',9);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '角色权限修改', NOW(), 'sys_roleAuthority_update',9);

--角色
insert into role(create_time, update_time, is_deleted, `name`)
values (NOW(), NOW(), 0, '超级管理员');
insert into role(create_time, update_time, is_deleted, `name`)
values (NOW(), NOW(), 0, '普通用户');
--角色权限
--管理员
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 1);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 2);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 3);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 4);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 5);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 6);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 7);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 8);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 9);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 10);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 11);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 12);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 13);
--普通用户

--内置用户
insert into user (create_time, update_time, is_deleted, `name`, age, sex, role_id, username, password)
values (NOW(), NOW(), 0, '超级管理员', 18, 1, 1, 'admin',
        '$2a$10$yndC6fntPcMlU5zcB2pczeUS8H4q4xpws2slXcfShVXNC65R20U3y');
