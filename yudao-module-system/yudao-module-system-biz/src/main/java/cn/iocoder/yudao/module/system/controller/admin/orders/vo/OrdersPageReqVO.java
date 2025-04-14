package cn.iocoder.yudao.module.system.controller.admin.orders.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 收款订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrdersPageReqVO extends PageParam {

    @Schema(description = "支付金额（CNY，保留2位小数）")
    private BigDecimal amount;

    @Schema(description = "订单状态（1: 等待支付，2: 支付成功，3: 已过期）", example = "1")
    private Integer status;

    @Schema(description = "异步回调地址", example = "https://www.iocoder.cn")
    private String notifyUrl;

    @Schema(description = "同步跳转地址", example = "https://www.iocoder.cn")
    private String redirectUrl;

    @Schema(description = "签名（MD5签名）")
    private String signature;


    @Schema(description = "区块订单号", example = "https://www.iocoder.cn")
    private String blockTransactionId;

    @Schema(description = "实际支付金额（USDT，保留四位小数）")
    private BigDecimal actualAmount;

    @Schema(description = "钱包地址")
    private String token;

    @Schema(description = "过期时间（时间戳秒）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] expirationTime;

    @Schema(description = "收银台地址", example = "https://www.iocoder.cn")
    private String paymentUrl;

    @Schema(description = "回调接收时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] callbackTime;

    @Schema(description = "订单/交易创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "订单/交易更新时间")
    private LocalDateTime updatedAt;

    @Schema(description = "租户名称", example = "张三")
    private String tenantName;

    @Schema(description = "用户名", example = "芋艿")
    private String username;

    @Schema(description = "昵称", example = "张三")
    private String nickname;

    @Schema(description = "密码（加密存储）")
    private String password;

}