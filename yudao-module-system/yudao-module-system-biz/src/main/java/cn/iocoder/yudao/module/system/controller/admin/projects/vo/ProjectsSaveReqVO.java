package cn.iocoder.yudao.module.system.controller.admin.projects.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - Web3项目新增/修改 Request VO")
@Data
public class ProjectsSaveReqVO {

    @Schema(description = "项目的唯一标识符", requiredMode = Schema.RequiredMode.REQUIRED, example = "23961")
    private Integer id;

    @Schema(description = "脚本类型", example = "2")
    private String scriptType;

    @Schema(description = "项目名称", example = "王五")
    private String name;

    @Schema(description = "项目官网", example = "https://www.iocoder.cn")
    private String url;

    @Schema(description = "项目Logo", example = "https://www.iocoder.cn")
    private String logoUrl;

    @Schema(description = "简短描述", example = "你猜")
    private String shortDescription;

    @Schema(description = "详细描述", example = "随便")
    private String longDescription;

    @Schema(description = "成立年份")
    private LocalDate establishmentYear;

    @Schema(description = "融资金额")
    private BigDecimal totalFunding;

    @Schema(description = "推特地址", example = "https://www.iocoder.cn")
    private String rootDataUrl;

    @Schema(description = "web3领域")
    private String web3Domain;

    @Schema(description = "代币符号")
    private String tokenSymbol;

    @Schema(description = "代币经济学")
    private String tokenomics;

    @Schema(description = "项目当前状态", example = "1")
    private String status;

    @Schema(description = "区块链平台类型", example = "2")
    private String platformType;

    @Schema(description = "市场总值")
    private BigDecimal marketCap;

    @Schema(description = "社交媒体链接")
    private String socialMedia;

    @Schema(description = "安全审计状态", example = "2")
    private String auditStatus;

    @Schema(description = "合作伙伴")
    private String partnerships;

    @Schema(description = "应用场景")
    private String useCases;

    @Schema(description = "社区规模")
    private Integer communitySize;

}