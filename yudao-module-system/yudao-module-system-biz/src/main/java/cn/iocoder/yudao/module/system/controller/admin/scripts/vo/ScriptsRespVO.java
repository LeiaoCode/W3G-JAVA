package cn.iocoder.yudao.module.system.controller.admin.scripts.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - Web3脚本 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ScriptsRespVO {
    @Schema(description = "项目的唯一标识符", requiredMode = Schema.RequiredMode.REQUIRED, example = "23961")
    private Long id;
    @Schema(description = "脚本名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("脚本名称")
    private String name;

    @Schema(description = "脚本类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty(value = "脚本类型", converter = DictConvert.class)
    @DictFormat("script_type") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String type;

    @Schema(description = "脚本描述", example = "你猜")
    @ExcelProperty("脚本描述")
    private String description;

    @Schema(description = "项目ID", example = "12115")
    @ExcelProperty(value = "项目ID", converter = DictConvert.class)
    @DictFormat("infra_config_type") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Long projectId;

    @Schema(description = "所需配置准备")
    @ExcelProperty(value = "所需配置准备", converter = DictConvert.class)
    @DictFormat("REQUIRED_CONFIGURATION") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String configRequired;

    @Schema(description = "功能说明")
    @ExcelProperty("功能说明")
    private String functionality;

    @Schema(description = "脚本版本号")
    @ExcelProperty("脚本版本号")
    private String version;

    @Schema(description = "本地脚本请求接口")
    @ExcelProperty("本地脚本请求接口")
    private String localScriptPath;

    @Schema(description = "脚本执行页面URL", example = "https://www.iocoder.cn")
    @ExcelProperty("脚本执行页面URL")
    private String executionPageUrl;

    @Schema(description = "脚本状态：0=停用，1=在用", example = "2")
    @ExcelProperty(value = "脚本状态：0=停用，1=在用", converter = DictConvert.class)
    @DictFormat("system_notice_type") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer status;

    @Schema(description = "是否为本地执行脚本：1=是，0=否")
    @ExcelProperty(value = "是否为本地执行脚本：1=是，0=否", converter = DictConvert.class)
    @DictFormat("system_notice_type") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer isLocalExecution;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}