package cn.iocoder.yudao.module.system.controller.admin.scripts.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - Web3脚本分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ScriptsPageReqVO extends PageParam {
    @Schema(description = "项目的唯一标识符", requiredMode = Schema.RequiredMode.REQUIRED, example = "23961")
    private Long id;
    @Schema(description = "脚本名称", example = "赵六")
    private String name;

    @Schema(description = "脚本类型", example = "2")
    private String type;

    @Schema(description = "项目ID", example = "12115")
    private Long projectId;

    @Schema(description = "脚本状态：0=停用，1=在用", example = "2")
    private Integer status;

    @Schema(description = "是否为本地执行脚本：1=是，0=否")
    private Integer isLocalExecution;

}