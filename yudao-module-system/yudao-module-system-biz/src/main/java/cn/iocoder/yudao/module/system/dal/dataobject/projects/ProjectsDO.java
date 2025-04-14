package cn.iocoder.yudao.module.system.dal.dataobject.projects;

import com.sun.xml.bind.v2.TODO;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * Web3项目 DO
 *
 * @author Awren
 */
@TableName("web3_projects")
@KeySequence("web3_projects_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectsDO extends BaseDO {

    /**
     * 项目的唯一标识符
     */
    @TableId
    private Integer id;
    /**
     * 脚本类型
     *
     * 枚举 {@link TODO script_type 对应的类}
     */
    private String scriptType;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 项目官网
     */
    private String url;
    /**
     * 项目Logo
     */
    private String logoUrl;
    /**
     * 简短描述
     */
    private String shortDescription;
    /**
     * 详细描述
     */
    private String longDescription;
    /**
     * 成立年份
     */
    private LocalDate establishmentYear;
    /**
     * 融资金额
     */
    private BigDecimal totalFunding;
    /**
     * 推特地址
     */
    private String rootDataUrl;
    /**
     * web3领域
     */
    private String web3Domain;
    /**
     * 代币符号
     */
    private String tokenSymbol;
    /**
     * 代币经济学
     */
    private String tokenomics;
    /**
     * 项目当前状态
     */
    private String status;
    /**
     * 区块链平台类型
     *
     * 枚举 {@link TODO WEB3_DOMAIN 对应的类}
     */
    private String platformType;
    /**
     * 市场总值
     */
    private BigDecimal marketCap;
    /**
     * 社交媒体链接
     */
    private String socialMedia;
    /**
     * 安全审计状态
     */
    private String auditStatus;
    /**
     * 合作伙伴
     */
    private String partnerships;
    /**
     * 应用场景
     */
    private String useCases;
    /**
     * 社区规模
     */
    private Integer communitySize;

}