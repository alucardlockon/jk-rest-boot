drop table if exists  account;
CREATE TABLE account
(
    id int(10) PRIMARY KEY AUTO_INCREMENT,
    username varchar(100) COMMENT '用户名',
    password varchar(200) COMMENT '密码',
    email varchar(200) COMMENT '邮件',
    status tinyint(2) COMMENT '状态:0.未验证,1.已验证,2.注销',
    create_ip varchar(20) COMMENT '创建ip',
    create_date datetime DEFAULT current_time,
    create_user int(10),
    update_date datetime DEFAULT current_time,
    update_user int(10),
    last_ip varchar(20) COMMENT '最后登录ip',
    roles varchar(100) COMMENT '角色'
);
ALTER TABLE account COMMENT = '用户账户';



