insert into authority (create_time,is_deleted,`name`,update_time,value) values (NOW(),0, '所有权限',NOW(),'/all');
insert into role(create_time,update_time,is_deleted,`name`) values (NOW(),NOW(),0,'超级管理员');
insert into role_authority(create_time,update_time,is_deleted,role_id, authority_id) values (NOW(),NOW(),0,1, 1);
insert into user (create_time,update_time,is_deleted,`name`,age,sex) values (NOW(),NOW(),0,'超级管理员',18,1);
insert into account(create_time,update_time,is_deleted,role_id,user_id,account,email, `password`) values (NOW(),NOW(),0,1,1,'admin', 'admin@localhost', "ebe3b6f7928b5be6765fdf60085a75255b014f46db3742eb8463116c8b97ad7b");