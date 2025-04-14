package cn.iocoder.yudao.module.system.controller.admin.orders.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 收款订单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OrdersRespVO {

    @Schema(description = "请求支付订单号", requiredMode = Schema.RequiredMode.REQUIRED, example = "22366")
    @ExcelProperty("请求支付订单号")
    private String orderId;

    @Schema(description = "支付金额（CNY，保留2位小数）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("支付金额（CNY，保留2位小数）")
    private BigDecimal amount;

    @Schema(description = "订单状态（1: 等待支付，2: 支付成功，3: 已过期）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("订单状态（1: 等待支付，2: 支付成功，3: 已过期）")
    private Integer status;

    @Schema(description = "异步回调地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @ExcelProperty("异步回调地址")
    private String notifyUrl;

    @Schema(description = "同步跳转地址", example = "https://www.iocoder.cn")
    @ExcelProperty("同步跳转地址")
    private String redirectUrl;

    @Schema(description = "签名（MD5签名）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("签名（MD5签名）")
    private String signature;

    @Schema(description = "实际支付金额（USDT，保留四位小数）")
    @ExcelProperty("实际支付金额（USDT，保留四位小数）")
    private BigDecimal actualAmount;

    @Schema(description = "钱包地址")
    @ExcelProperty("钱包地址")
    private String token;

    @Schema(description = "区块订单号", example = "https://www.iocoder.cn")
    private String blockTransactionId;

    @Schema(description = "过期时间（时间戳秒）")
    @ExcelProperty("过期时间（时间戳秒）")
    private Integer expirationTime;

    @Schema(description = "收银台地址", example = "https://www.iocoder.cn")
    @ExcelProperty("收银台地址")
    private String paymentUrl;

    @Schema(description = "回调接收时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("回调接收时间")
    private LocalDateTime callbackTime;

    @Schema(description = "订单/交易创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("订单/交易创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "订单/交易更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("订单/交易更新时间")
    private LocalDateTime updatedAt;

    @Schema(description = "租户名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("租户名称")
    private String tenantName;

    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("用户名")
    private String username;

    @Schema(description = "昵称", example = "张三")
    @ExcelProperty("昵称")
    private String nickname;

    @Schema(description = "密码（加密存储）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("密码（加密存储）")
    private String password;

}