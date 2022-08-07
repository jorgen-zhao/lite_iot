-- 设备信息表
DROP TABLE IF EXISTS `device_info`;
CREATE TABLE `device_info`
(
    `id`             BIGINT ( 50 ) NOT NULL AUTO_INCREMENT,
    `imei`           VARCHAR(50) NOT NULL COMMENT 'imei',
    `device_name`    VARCHAR(50)                                                   DEFAULT NULL COMMENT '设备名称',
    `config`         VARCHAR(255)                                                  DEFAULT NULL COMMENT '配置项',
    `device_type`    INT ( 10 ) DEFAULT NULL COMMENT '设备类型',
    `group_id`       int(10) NOT NULL COMMENT '所属组织',
    `install_status` TINYINT ( 1 ) DEFAULT 0 COMMENT '安装状态(安装状态(0-未激活、1-已激活))',
    `running_status` TINYINT ( 1 ) DEFAULT NULL COMMENT '运行状态(运行状态(0-正常、1-报警、2-恢复、3-拆除)',
    `remark`         VARCHAR(512)                                                  DEFAULT NULL COMMENT '备注',
    `crt_time`       datetime(0) DEFAULT NULL COMMENT '创建时间',
    `crt_user`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
    `crt_name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人姓名',
    `crt_host`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建主机',
    `upd_time`       datetime(0) DEFAULT NULL COMMENT '更新时间',
    `upd_user`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
    `upd_name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新姓名',
    `upd_host`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新主机',
    PRIMARY KEY (`id`) USING BTREE,
    KEY              `idx_imei` ( `imei` ) USING BTREE
) ENGINE = INNODB  DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMMENT = '设备信息表';