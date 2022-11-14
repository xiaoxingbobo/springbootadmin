--开发设置1
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)
values (NOW(), 0, 'development', NOW(), 'sys:development:setting', 'vaadin:code', 'Layout', '/development', '开发设置',
        0,
        'ROUTER');
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)
values (NOW(), 0, 'API', NOW(), 'sys:development:api', 'vaadin:code', '',
        '/development', '接口文档', 1,
        'ROUTER');
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)
values (NOW(), 0, 'element-plus-admin-doc', NOW(), 'sys:development:doc', 'vaadin:code',
        'https://element-plus-admin-doc.cn/', '/doc', 'UI文档', 1,
        'ROUTER');

--系统设置4
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, 'setting', NOW(), 'sys:system:setting', 'ep:setting', 'Layout', '/setting', '系统设置', 0,
        'ROUTER');
--权限管理
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, 'authority', NOW(), 'sys:authority:view', null,
        '() => import(''@/views/System/Permission/index.vue'')', '/authority', '权限管理', 4,
        'ROUTER');
--增加
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:authority:view', null, null, null, '权限管理-增加', 4,
        'API');
--删除
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:authority:delete', null, null, null, '权限管理-删除', 4,
        'API');
--查询
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:authority:select', null, null, null, '权限管理-查询', 4,
        'API');
--修改
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:authority:update', null, null, null, '权限管理-查询', 4,
        'API');



--角色管理10
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, 'role', NOW(), 'sys:role:view', null, '() => import(''@/views/System/Role/index.vue'')', '/role',
        '角色管理', 4,
        'ROUTER');

--增加
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:role:add', null, null, null, '角色管理-查询', 10,
        'API');
--删除
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:role:delete', null, null, null, '角色管理-删除', 10,
        'API');
--查询
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:role:select', null, null, null, '角色管理-查询', 10,
        'API');
--修改
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:role:update', null, null, null, '角色管理-修改', 10,
        'API');

--用户管理15
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, 'user', NOW(), 'sys:user:view', null, '() => import(''@/views/System/User/index.vue'')', '/user',
        '用户管理', 4,
        'ROUTER');

--增加
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:user:add', null, null, null, '用户管理-增加', 15,
        'API');
--删除
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:user:delete', null, null, null, '用户管理-删除', 15,
        'API');
--查询
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:user:select', null, null, null, '用户管理-查询', 15,
        'API');
--修改
insert into authority (create_time, is_deleted, `name`, update_time, value, icon, component, path, title, parent_id,
                       authority_type)

values (NOW(), 0, null, NOW(), 'sys:user:update', null, null, null, '用户管理-修改', 15,
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
insert into user (create_time, update_time, is_deleted, `name`, age, sex, role_id, username, password)
values (NOW(), NOW(), 0, '超级管理员', 18, 1, 1, 'admin',
        '$2a$10$yndC6fntPcMlU5zcB2pczeUS8H4q4xpws2slXcfShVXNC65R20U3y');

