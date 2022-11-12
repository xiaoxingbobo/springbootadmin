--权限
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '系统设置', NOW(), 'sys_system',0);

insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '用户查询', NOW(), 'sys_user_select',1);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '用户添加', NOW(), 'sys_user_add',2);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '用户删除', NOW(), 'sys_user_delete',2);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '用户修改', NOW(), 'sys_user_update',2);

insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '权限查询', NOW(), 'sys_authority_select',1);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '权限添加', NOW(), 'sys_authority_add',6);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '权限删除', NOW(), 'sys_authority_delete',6);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '权限修改', NOW(), 'sys_authority_update',6);

insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '角色权限查询', NOW(), 'sys_roleAuthority_select',1);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '角色权限添加', NOW(), 'sys_roleAuthority_add',10);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '角色权限删除', NOW(), 'sys_roleAuthority_delete',10);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '角色权限修改', NOW(), 'sys_roleAuthority_update',10);

insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '角色查询', NOW(), 'sys_role_select',1);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '角色添加', NOW(), 'sys_role_add',14);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '角色删除', NOW(), 'sys_role_delete',14);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '角色修改', NOW(), 'sys_role_update',14);


insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '代码生成', NOW(), 'sys_code',0);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '代码生成', NOW(), 'sys_code_select',18);
insert into authority (create_time, is_deleted, `name`, update_time, value,parent_id)
values (NOW(), 0, '代码生成-撤销', NOW(), 'sys_code_undo',18);



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
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 14);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 15);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 16);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 17);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 18);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 19);
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 20);

--普通用户

--内置用户
insert into user (create_time, update_time, is_deleted, `name`, age, sex, role_id, username, password)
values (NOW(), NOW(), 0, '超级管理员', 18, 1, 1, 'admin',
        '$2a$10$yndC6fntPcMlU5zcB2pczeUS8H4q4xpws2slXcfShVXNC65R20U3y');
