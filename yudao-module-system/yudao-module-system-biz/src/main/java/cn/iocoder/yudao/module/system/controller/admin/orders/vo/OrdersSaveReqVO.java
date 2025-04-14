package cn.iocoder.yudao.module.system.controller.admin.orders.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 收款订单新增/修改 Request VO")
@Data
public class OrdersSaveReqVO {

    @Schema(description = "请求支付订单号", requiredMode = Schema.RequiredMode.REQUIRED, example = "22366")
    private String orderId;

    @Schema(description = "支付金额（CNY，保留2位小数）", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer amount;

    @Schema(description = "订单状态（1: 等待支付，2: 支付成功，3: 已过期）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer status;

    @Schema(description = "异步回调地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    private String notifyUrl;

    @Schema(description = "同步跳转地址", example = "https://www.iocoder.cn")
    private String redirectUrl;

    @Schema(description = "签名（MD5签名）", requiredMode = Schema.RequiredMode.REQUIRED)
    private String signature;

    @Schema(description = "实际支付金额（USDT，保留四位小数）")
    private BigDecimal actualAmount;

    @Schema(description = "钱包地址")
    private String token;

    @Schema(description = "过期时间（时间戳秒）")
    private Integer expirationTime;

    @Schema(description = "收银台地址", example = "https://www.iocoder.cn")
    private String paymentUrl;

    @Schema(description = "区块订单号", example = "https://www.iocoder.cn")
    private String blockTransactionId;

    @Schema(description = "回调接收时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime callbackTime;

    @Schema(description = "订单/交易创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createdAt;

    @Schema(description = "订单/交易更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime updatedAt;

    @Schema(description = "租户名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    private String tenantName;

    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @Schema(description = "昵称", example = "张三")
    @NotEmpty(message = "昵称不能为空")
    private String nickname;

    @Schema(description = "密码（加密存储）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "密码不能为空")
    private String password;

}