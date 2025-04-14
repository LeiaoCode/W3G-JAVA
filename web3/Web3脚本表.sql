CREATE TABLE `web3_scripts`
(
    `id`                 bigint       NOT NULL AUTO_INCREMENT COMMENT '脚本主键' PRIMARY KEY,
    `name`               varchar(255) NOT NULL COMMENT '脚本名称',
    `type`               varchar(100) NOT NULL COMMENT '脚本类型',
    `description`        text COMMENT '脚本描述',
    `project_id`         bigint       NOT NULL COMMENT '项目ID',
    `config_required`    text COMMENT '所需配置准备',
    `functionality`      text COMMENT '功能说明',
    `version`            varchar(50)  NOT NULL                  DEFAULT '1.0.0' COMMENT '脚本版本号',
    `local_script_path`  varchar(512) COMMENT '本地脚本请求接口',
    `execution_page_url` varchar(512) COMMENT '脚本执行页面URL',
    `status`             tinyint      NOT NULL                  DEFAULT '1' COMMENT '脚本状态：0=停用，1=在用',
    `is_local_execution` tinyint      NOT NULL                  DEFAULT '0' COMMENT '是否为本地执行脚本：1=是，0=否',
    -- 以下是 BaseDO 的公共字段
    `creator`            varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
    `create_time`        datetime     NOT NULL                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`            varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
    `update_time`        datetime     NOT NULL                  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`            bit(1)       NOT NULL                  DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`          bigint(20)   NOT NULL                  DEFAULT '0' COMMENT '租户编号'
) comment 'Web3脚本表' collate = utf8mb4_unicode_ci;

-- 菜单 SQL
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status, component_name
)
VALUES (
           'Web3脚本管理', '', 2, 0, 2914,
           'scripts', '', 'web3/scripts/index', 0, 'Scripts'
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
           'Web3脚本查询', 'web3:scripts:query', 3, 1, @parentId,
           '', '', '', 0
       );
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
           'Web3脚本创建', 'web3:scripts:create', 3, 2, @parentId,
           '', '', '', 0
       );
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
           'Web3脚本更新', 'web3:scripts:update', 3, 3, @parentId,
           '', '', '', 0
       );
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
           'Web3脚本删除', 'web3:scripts:delete', 3, 4, @parentId,
           '', '', '', 0
       );
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
           'Web3脚本导出', 'web3:scripts:export', 3, 5, @parentId,
           '', '', '', 0
       );
