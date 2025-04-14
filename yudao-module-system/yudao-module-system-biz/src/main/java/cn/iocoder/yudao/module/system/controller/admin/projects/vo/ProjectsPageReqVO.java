package cn.iocoder.yudao.module.system.controller.admin.projects.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - Web3项目分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProjectsPageReqVO extends PageParam {

    @Schema(description = "项目的唯一标识符", requiredMode = Schema.RequiredMode.REQUIRED, example = "23961")
    private Integer id;

    @Schema(description = "脚本类型", example = "2")
    private String scriptType;

    @Schema(description = "项目名称", example = "王五")
    private String name;

    @Schema(description = "项目官网", example = "https://www.iocoder.cn")
    private String url;

    @Schema(description = "区块链平台类型", example = "2")
    private String platformType;


}