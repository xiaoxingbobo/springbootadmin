insert into authority (create_time, is_deleted, `name`, update_time, value)
values (NOW(), 0, '所有权限', NOW(), 'all');
insert into role(create_time, update_time, is_deleted, `name`)
values (NOW(), NOW(), 0, '超级管理员');
insert into role_authority(create_time, update_time, is_deleted, role_id, authority_id)
values (NOW(), NOW(), 0, 1, 1);
insert into user (create_time, update_time, is_deleted, `name`, age, sex, role_id, username, password)
values (NOW(), NOW(), 0, '超级管理员', 18, 1, 1, 'admin',
        '$2a$10$yndC6fntPcMlU5zcB2pczeUS8H4q4xpws2slXcfShVXNC65R20U3y');
