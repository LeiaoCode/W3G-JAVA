package cn.iocoder.yudao.module.system.controller.admin.projects.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - Web3项目 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ProjectsRespVO {

    @Schema(description = "项目的唯一标识符", requiredMode = Schema.RequiredMode.REQUIRED, example = "23961")
    @ExcelProperty("项目的唯一标识符")
    private Integer id;

    @Schema(description = "脚本类型", example = "2")
    @ExcelProperty(value = "脚本类型", converter = DictConvert.class)
    @DictFormat("script_type") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String scriptType;

    @Schema(description = "项目名称", example = "王五")
    @ExcelProperty("项目名称")
    private String name;

    @Schema(description = "项目官网", example = "https://www.iocoder.cn")
    @ExcelProperty("项目官网")
    private String url;

    @Schema(description = "项目Logo", example = "https://www.iocoder.cn")
    @ExcelProperty("项目Logo")
    private String logoUrl;

    @Schema(description = "简短描述", example = "你猜")
    @ExcelProperty("简短描述")
    private String shortDescription;

    @Schema(description = "详细描述", example = "随便")
    @ExcelProperty("详细描述")
    private String longDescription;

    @Schema(description = "成立年份")
    @ExcelProperty("成立年份")
    private LocalDate establishmentYear;

    @Schema(description = "融资金额")
    @ExcelProperty("融资金额")
    private BigDecimal totalFunding;

    @Schema(description = "推特地址", example = "https://www.iocoder.cn")
    @ExcelProperty("推特地址")
    private String rootDataUrl;

    @Schema(description = "web3领域")
    @ExcelProperty("web3领域")
    private String web3Domain;

    @Schema(description = "代币符号")
    @ExcelProperty("代币符号")
    private String tokenSymbol;

    @Schema(description = "代币经济学")
    @ExcelProperty("代币经济学")
    private String tokenomics;

    @Schema(description = "项目当前状态", example = "1")
    @ExcelProperty("项目当前状态")
    private String status;

    @Schema(description = "区块链平台类型", example = "2")
    @ExcelProperty(value = "区块链平台类型", converter = DictConvert.class)
    @DictFormat("WEB3_DOMAIN") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String platformType;

    @Schema(description = "市场总值")
    @ExcelProperty("市场总值")
    private BigDecimal marketCap;

    @Schema(description = "社交媒体链接")
    @ExcelProperty("社交媒体链接")
    private String socialMedia;

    @Schema(description = "安全审计状态", example = "2")
    @ExcelProperty("安全审计状态")
    private String auditStatus;

    @Schema(description = "合作伙伴")
    @ExcelProperty("合作伙伴")
    private String partnerships;

    @Schema(description = "应用场景")
    @ExcelProperty("应用场景")
    private String useCases;

    @Schema(description = "社区规模")
    @ExcelProperty("社区规模")
    private Integer communitySize;

}