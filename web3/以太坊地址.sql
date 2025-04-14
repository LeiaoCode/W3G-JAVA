CREATE TABLE eth_addresses
(
    id          INT AUTO_INCREMENT PRIMARY KEY COMMENT '自增ID',
    address     CHAR(42)    NOT NULL UNIQUE COMMENT '以太坊地址',
    private_key VARCHAR(66) NOT NULL COMMENT '以太坊私钥',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '地址创建时间',
    note        TEXT COMMENT '可选的备注信息'
) COMMENT = '以太坊地址表';


-- 菜单 SQL
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status, component_name
)
VALUES (
           '以太坊地址管理', '', 2, 0, 2916,
           'addresses', '', 'eth/addresses/index', 0, 'Addresses'
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
           '以太坊地址查询', 'eth:addresses:query', 3, 1, @parentId,
           '', '', '', 0
       );
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
           '以太坊地址创建', 'eth:addresses:create', 3, 2, @parentId,
           '', '', '', 0
       );
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
           '以太坊地址更新', 'eth:addresses:update', 3, 3, @parentId,
           '', '', '', 0
       );
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
           '以太坊地址删除', 'eth:addresses:delete', 3, 4, @parentId,
           '', '', '', 0
       );
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
           '以太坊地址导出', 'eth:addresses:export', 3, 5, @parentId,
           '', '', '', 0
       );
