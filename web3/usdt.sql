-- 创建支付订单交易回调表，并添加注释
drop table if exists payment_orders;
CREATE TABLE payment_orders
(
    order_id             VARCHAR(255) PRIMARY KEY COMMENT '请求支付订单号',                                                    -- 请求支付订单号
    amount               DECIMAL(10, 2)                        NOT NULL COMMENT '支付金额（CNY，保留2位小数）',                   -- 支付金额（CNY，保留2位小数）
    status               INT                                   NOT NULL COMMENT '订单状态（1: 等待支付，2: 支付成功，3: 已过期）', -- 订单状态
    notify_url           VARCHAR(255)                          NOT NULL COMMENT '异步回调地址',                                -- 异步回调地址
    redirect_url         VARCHAR(255) COMMENT '同步跳转地址',                                                                  -- 同步跳转地址
    signature            VARCHAR(32)                           NOT NULL COMMENT '签名（MD5签名）',                               -- 签名（MD5签名）
    actual_amount        DECIMAL(10, 4) COMMENT '实际支付金额（USDT，保留四位小数）',                                             -- 实际支付金额（USDT，保留四位小数）
    token                VARCHAR(255) COMMENT '钱包地址',                                                                      -- 钱包地址
    expiration_time      INT COMMENT '过期时间（时间戳秒）',                                                                     -- 过期时间（时间戳秒）
    payment_url          VARCHAR(255) COMMENT '收银台地址',                                                                    -- 收银台地址
    block_transaction_id VARCHAR(255) COMMENT '区块交易号',                                                                    -- 收银台地址
    callback_time        TIMESTAMP   DEFAULT CURRENT_TIMESTAMP COMMENT '回调接收时间',                                         -- 回调接收时间
    created_at           TIMESTAMP   DEFAULT CURRENT_TIMESTAMP COMMENT '订单/交易创建时间',                                    -- 订单/交易创建时间
    updated_at           TIMESTAMP   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '订单/交易更新时间',        -- 订单/交易更新时间
    tenant_name          VARCHAR(20)                           NOT NULL COMMENT '租户名称',                                    -- 租户名称，长度为2到20
    username             VARCHAR(30)                           NOT NULL COMMENT '用户名',                                      -- 用户名，长度为4到30
    nickname             VARCHAR(30) COMMENT '昵称',                                                                           -- 昵称，长度为0到30
    password             VARCHAR(255)                          NOT NULL COMMENT '密码（加密存储）',                              -- 密码，加密后存储
    creator              varchar(64) default ''                null comment '创建者',
    create_time          datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater              varchar(64) default ''                null comment '更新者',
    update_time          datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted              bit         default b'0'              not null comment '是否删除',
    tenant_id            bigint      default 0                 not null comment '租户编号'
) COMMENT = '收款订单';;


-- 菜单 SQL
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status, component_name
)
VALUES (
           '收款订单管理', '', 2, 0, 2947,
           'orders', '', 'payment/orders/index', 0, 'Orders'
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
           '收款订单查询', 'payment:orders:query', 3, 1, @parentId,
           '', '', '', 0
       );
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
           '收款订单创建', 'payment:orders:create', 3, 2, @parentId,
           '', '', '', 0
       );
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
           '收款订单更新', 'payment:orders:update', 3, 3, @parentId,
           '', '', '', 0
       );
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
           '收款订单删除', 'payment:orders:delete', 3, 4, @parentId,
           '', '', '', 0
       );
INSERT INTO system_menu(
    name, permission, type, sort, parent_id,
    path, icon, component, status
)
VALUES (
           '收款订单导出', 'payment:orders:export', 3, 5, @parentId,
           '', '', '', 0
       );
