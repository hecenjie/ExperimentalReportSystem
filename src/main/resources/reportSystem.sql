CREATE DATABASE  IF NOT EXISTS `reportSystem`;
USE `reportSystem`;

CREATE TABLE `user`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `stu_num` int(11) DEFAULT NULL COMMENT '学号',
    `stu_class` int(11) DEFAULT NULL COMMENT '学生班级',
    `password` char(48) NOT NULL COMMENT '哈希加密密码',
    `role` smallint(6) DEFAULT '0' COMMENT '用户权限：0-学生，1-管理员',
    `major_id` int(11) DEFAULT NULL COMMENT '学生专业id',
    PRIMARY KEY (`id`),
    UNIQUE KEY `stu_num` (`stu_num`),
    KEY `stu_class_index` (`stu_class`) COMMENT '班级索引',
    KEY `major_id_index` (`major_id`) COMMENT '专业索引'
);

CREATE TABLE `major`(
	`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '专业id',
    `name` varchar(20) NOT NULL COMMENT '专业名',
     PRIMARY KEY (`id`)
);
insert into major(name) values ('农机');
insert into major(name) values ('车辆');
insert into major(name) values ('机制');
insert into major(name) values ('材控');
insert into major(name) values ('工设');
insert into major(name) values ('电气');
insert into major(name) values ('自动');
insert into major(name) values ('信息');
insert into major(name) values ('工管');
insert into major(name) values ('工程');
insert into major(name) values ('物流');

CREATE TABLE `experiment`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(20) NOT NULL COMMENT '实验名',
    `open` smallint(6) DEFAULT '0' COMMENT '开放状态：0-未开放，1-开放',
    primary key (`id`) 
);

CREATE TABLE `score`(
	`stu_id` int(11) NOT NULL COMMENT '学号',
    `exp_id` int(11) NOT NULL COMMENT '实验id',
    `score`  smallint(6) NOT NULL COMMENT '学生成绩',
    primary key (`stu_id`, `exp_id`) 
);