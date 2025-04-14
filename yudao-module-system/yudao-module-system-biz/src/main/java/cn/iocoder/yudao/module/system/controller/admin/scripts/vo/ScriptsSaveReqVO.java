package cn.iocoder.yudao.module.system.controller.admin.scripts.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - Web3脚本新增/修改 Request VO")
@Data
public class ScriptsSaveReqVO {

    @Schema(description = "项目的唯一标识符", requiredMode = Schema.RequiredMode.REQUIRED, example = "23961")
    private Long id;
    @Schema(description = "脚本名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "脚本名称不能为空")
    private String name;

    @Schema(description = "脚本类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "脚本类型不能为空")
    private String type;

    @Schema(description = "脚本描述", example = "你猜")
    private String description;

    @Schema(description = "项目ID", example = "12115")
    private Long projectId;

    @Schema(description = "所需配置准备")
    private List<String> configRequired;

    @Schema(description = "功能说明")
    private String functionality;

    @Schema(description = "脚本版本号")
    private String version;

    @Schema(description = "本地脚本请求接口")
    private String localScriptPath;

    @Schema(description = "脚本执行页面URL", example = "https://www.iocoder.cn")
    private String executionPageUrl;

    @Schema(description = "脚本状态：0=停用，1=在用", example = "2")
    private Integer status;

    @Schema(description = "是否为本地执行脚本：1=是，0=否")
    private Integer isLocalExecution;

}