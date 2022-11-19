--开发设置1
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)
values (NOW(), 0, 'development', NOW(), 'sys:development:setting', 'vaadin:code', 'Layout', '/development', '开发设置',
        0,
        'ROUTER');
--代码生成2
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)
values (NOW(), 0, 'code-view', NOW(), 'sys:code:view', 'vaadin:code',
        'Development/Code/index', 'code', '代码生成', 1,
        'ROUTER');
--代码生成-增加3
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)
values (NOW(), 0, 'code-build', NOW(), 'sys:code:add', null,
        null, null, '代码生成-添加', 2,
        'API');
--代码生成-查询4
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)
values (NOW(), 0, 'code-build', NOW(), 'sys:code:select', null,
        null, null, '代码生成-查询', 2,
        'API');
--代码生成-撤销5
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)
values (NOW(), 0, 'code-undo', NOW(), 'sys:code:undo', null,
        null, null, '代码生成-撤销', 2,
        'API');


--系统设置6
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, 'setting', NOW(), 'sys:system:setting', 'ep:setting', 'Layout', '/setting', '系统设置', 0,
        'ROUTER');
--权限管理7
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, 'authority', NOW(), 'sys:authority:view', 'material-symbols:verified-user',
        'System/Permission/index', 'authority', '权限管理', 6,
        'ROUTER');
--增加8
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:authority:add', null, null, null, '权限管理-增加', 7,
        'API');
--删除9
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:authority:delete', null, null, null, '权限管理-删除', 7,
        'API');
--查询10
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:authority:select', null, null, null, '权限管理-查询', 7,
        'API');
--修改11
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:authority:update', null, null, null, '权限管理-修改', 7,
        'API');


--角色管理12
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, 'role', NOW(), 'sys:role:view', 'mdi:user-convert', 'System/Role/index', 'role',
        '角色管理', 6,
        'ROUTER');

--增加
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:role:add', null, null, null, '角色管理-添加', 12,
        'API');
--删除
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:role:delete', null, null, null, '角色管理-删除', 12,
        'API');
--查询
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:role:select', null, null, null, '角色管理-查询', 12,
        'API');
--修改
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:role:update', null, null, null, '角色管理-修改', 12,
        'API');

--用户管理18
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, 'user', NOW(), 'sys:user:view', 'mdi:user-group', 'System/User/index', 'user',
        '用户管理', 6,
        'ROUTER');

--增加
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:user:add', null, null, null, '用户管理-增加', 17,
        'API');
--删除
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:user:delete', null, null, null, '用户管理-删除', 17,
        'API');
--查询
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:user:select', null, null, null, '用户管理-查询', 17,
        'API');
--修改
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:user:update', null, null, null, '用户管理-修改', 17,
        'API');


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

--普通用户

--内置用户
insert into user (create_time, update_time, is_deleted, `name`, age, sex, role_id, username,nickname, password)
values (NOW(), NOW(), 0, '超级管理员', 18, 1, 1, 'admin','超级管理员',
        '$2a$10$yndC6fntPcMlU5zcB2pczeUS8H4q4xpws2slXcfShVXNC65R20U3y');
insert into user (create_time, update_time, is_deleted, `name`, age, sex, role_id, username,nickname, password)
values (NOW(), NOW(), 0, '用户', 18, 1, 2, 'user','小星',
        '$2a$10$yndC6fntPcMlU5zcB2pczeUS8H4q4xpws2slXcfShVXNC65R20U3y');