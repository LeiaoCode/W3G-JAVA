package cn.iocoder.yudao.module.system.dal.dataobject.scripts;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * Web3脚本 DO
 *
 * @author Awren
 */
@TableName("web3_scripts")
@KeySequence("web3_scripts_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScriptsDO extends BaseDO {

    /**
     * 脚本主键
     */
    @TableId
    private Long id;
    /**
     * 脚本名称
     */
    private String name;
    /**
     * 脚本类型
     *
     * 枚举 {@link TODO script_type 对应的类}
     */
    private String type;
    /**
     * 脚本描述
     */
    private String description;
    /**
     * 项目ID
     *
     * 枚举 {@link TODO infra_config_type 对应的类}
     */
    private Long projectId;
    /**
     * 所需配置准备
     *
     * 枚举 {@link TODO system_notice_type 对应的类}
     */
    private String configRequired;
    /**
     * 功能说明
     */
    private String functionality;
    /**
     * 脚本版本号
     */
    private String version;
    /**
     * 本地脚本请求接口
     */
    private String localScriptPath;
    /**
     * 脚本执行页面URL
     */
    private String executionPageUrl;
    /**
     * 脚本状态：0=停用，1=在用
     *
     * 枚举 {@link TODO system_notice_type 对应的类}
     */
    private Integer status;
    /**
     * 是否为本地执行脚本：1=是，0=否
     *
     * 枚举 {@link TODO system_notice_type 对应的类}
     */
    private Integer isLocalExecution;

}