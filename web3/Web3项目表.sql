CREATE TABLE `web3_projects`
(
    `id`                 int          NOT NULL AUTO_INCREMENT COMMENT '项目的唯一标识符' primary key,
    `script_type`        varchar(255)                           DEFAULT NULL COMMENT '脚本类型',
    `name`               varchar(255) NOT NULL COMMENT '项目名称',
    `url`                varchar(512)                           DEFAULT NULL COMMENT '项目官网',
    `logo_url`           varchar(512)                           DEFAULT NULL COMMENT '项目Logo',
    `short_description`  varchar(500)                           DEFAULT NULL COMMENT '简短描述',
    `long_description`   text                                   DEFAULT NULL COMMENT '详细描述',
    `establishment_year` date                                   DEFAULT NULL COMMENT '成立年份',
    `total_funding`      decimal(36, 18)                        DEFAULT NULL COMMENT '融资金额',
    `root_data_url`      varchar(512)                           DEFAULT NULL COMMENT '推特地址',
    `web3_domain`        varchar(255)                           DEFAULT NULL COMMENT 'web3领域',
    `token_symbol`       varchar(50)                            DEFAULT NULL COMMENT '代币符号',
    `tokenomics`         text                                   DEFAULT NULL COMMENT '代币经济学',
    `status`             varchar(100)                           DEFAULT NULL COMMENT '项目当前状态',
    `platform_type`      varchar(255)                           DEFAULT NULL COMMENT '区块链平台类型',
    `market_cap`         decimal(36, 18)                        DEFAULT NULL COMMENT '市场总值',
    `social_media`       text                                   DEFAULT NULL COMMENT '社交媒体链接',
    `audit_status`       varchar(100)                           DEFAULT NULL COMMENT '安全审计状态',
    `partnerships`       text                                   DEFAULT NULL COMMENT '合作伙伴',
    `use_cases`          text                                   DEFAULT NULL COMMENT '应用场景',
    `community_size`     int                                    DEFAULT NULL COMMENT '社区规模',
    -- 以下是 BaseDO 的公共字段
    `creator`            varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
    `create_time`        datetime     NOT NULL                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`            varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
    `update_time`        datetime     NOT NULL                  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`            bit(1)       NOT NULL                  DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`          bigint(20)   NOT NULL                  DEFAULT '0' COMMENT '租户编号'
) comment 'Web3项目表' collate = utf8mb4_unicode_ci;



-- 菜单 SQL
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status, component_name
)
VALUES (
           'Web3项目管理', '', 2, 0, 2913,
           'projects', '', 'web3/projects/index', 0, 'Projects'
       );

-- 按钮父菜单ID
-- 暂时只支持 MySQL。如果你是 Oracle、PostgreSQL、SQLServer 的话，需要手动修改 @parentId 的部分的代码
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
           'Web3项目查询', 'web3:projects:query', 3, 1, @parentId,
           '', '', '', 0
       );
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
           'Web3项目创建', 'web3:projects:create', 3, 2, @parentId,
           '', '', '', 0
       );
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
           'Web3项目更新', 'web3:projects:update', 3, 3, @parentId,
           '', '', '', 0
       );
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
           'Web3项目删除', 'web3:projects:delete', 3, 4, @parentId,
           '', '', '', 0
       );
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
           'Web3项目导出', 'web3:projects:export', 3, 5, @parentId,
           '', '', '', 0
       );
