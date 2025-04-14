drop table web3_projects;
create table web3_projects
(
    id                 int auto_increment comment '项目的唯一标识符'
        primary key,
    script_type        varchar(255)                          null comment '脚本类型',
    name               varchar(255)                          null comment '项目名称',
    url                varchar(255)                          null comment '项目官网',
    logo_url           varchar(255)                          null comment '项目Logo',
    short_description  text                                  null comment '简短描述',
    long_description   text                                  null comment '详细描述',
    establishment_year date                                  null comment '成立年份',
    total_funding      decimal(15, 2)                        null comment '融资金额',
    root_data_url      varchar(255)                          null comment '推特地址',
    web3_domain        text                                  null comment 'web3领域',
    token_symbol       varchar(64)                           null comment '代币符号',
    tokenomics         text                                  null comment '代币经济学',
    status             varchar(32)                           null comment '项目当前状态',
    platform_type      varchar(255)                          null comment '区块链平台类型',
    market_cap         decimal(15, 2)                        null comment '市场总值',
    social_media       text                                  null comment '社交媒体链接',
    audit_status       varchar(32)                           null comment '安全审计状态',
    partnerships       text                                  null comment '合作伙伴',
    use_cases          text                                  null comment '应用场景',
    community_size     int                                   null comment '社区规模',
    creator            varchar(64) default ''                null comment '创建者',
    create_time        datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater            varchar(64) default ''                null comment '更新者',
    update_time        datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted            bit         default b'0'              not null comment '是否删除',
    tenant_id          bigint      default 0                 not null comment '租户编号'
)
    comment 'Web3项目表';


-- auto-generated definition
drop table web3_scripts;
CREATE TABLE web3_scripts
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '脚本主键',
    name               VARCHAR(100)                          NOT NULL COMMENT '脚本名称',
    type               VARCHAR(100)                          NOT NULL COMMENT '脚本类型',
    description        TEXT                                  NULL COMMENT '脚本描述',
    project_id         BIGINT                                NULL COMMENT '项目ID',
    config_required    VARCHAR(100)                          NULL COMMENT '所需配置准备',
    functionality      TEXT                                  NULL COMMENT '功能说明',
    version            VARCHAR(50)                           NULL COMMENT '脚本版本号',
    local_script_path  VARCHAR(255)                          NULL COMMENT '本地脚本请求接口',
    execution_page_url VARCHAR(255)                          NULL COMMENT '脚本执行页面URL',
    status             TINYINT     DEFAULT 1                 NULL COMMENT '脚本状态：0=停用，1=在用',
    is_local_execution TINYINT     DEFAULT 1                 NULL COMMENT '是否为本地执行脚本：1=是，0=否',
    creator            varchar(64) default ''                null comment '创建者',
    create_time        datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updater            varchar(64) default ''                null comment '更新者',
    update_time        datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted            bit         default b'0'              not null comment '是否删除',
    tenant_id          bigint      default 0                 not null comment '租户编号'
) COMMENT 'Web3脚本表' ENGINE = InnoDB
                       COLLATE = utf8mb4_unicode_ci;


drop table if exists eth_address_generation;
CREATE TABLE eth_address_generation
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '唯一标识符',
    eth_address     VARCHAR(42) NOT NULL COMMENT '生成的ETH地址',
    private_key     VARCHAR(66) NOT NULL COMMENT '对应的私钥',
    generation_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
    description     TEXT COMMENT '备注或描述',
    file_location   VARCHAR(255) COMMENT '保存的文件路径'
) COMMENT 'ETH地址生成表' ENGINE = InnoDB
                          COLLATE = utf8mb4_unicode_ci;
