SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS score;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS t_test;
DROP TABLE IF EXISTS user_info;
DROP TABLE IF EXISTS user_login;




/* Create Tables */

CREATE TABLE score
(
	usreid int,
	reward int
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE student
(
	userid int,
	status varchar(10)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE t_test
(
	id int,
	username varchar(20),
	password varchar(32)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


CREATE TABLE user_info
(
	id int NOT NULL AUTO_INCREMENT,
	username varchar(20),
	password varchar(20),
	-- ///
	-- 1|男
	-- 2|女
	gender varchar(10) COMMENT '///
1|男
2|女',
	PRIMARY KEY (id)
);


CREATE TABLE user_login
(
	-- 用户序号
	user_no int NOT NULL AUTO_INCREMENT COMMENT '用户序号',
	-- 用户登录id
	login_id varchar(20) NOT NULL COMMENT '用户登录id',
	-- 手机号
	mobile varchar(11) NOT NULL COMMENT '手机号',
	-- 登录密码
	password varchar(45) NOT NULL COMMENT '登录密码',
	-- 注册邮箱
	email varchar(45) COMMENT '注册邮箱',
	-- 注册日期
	reg_time datetime COMMENT '注册日期',
	PRIMARY KEY (user_no),
	UNIQUE (login_id),
	UNIQUE (mobile)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;



