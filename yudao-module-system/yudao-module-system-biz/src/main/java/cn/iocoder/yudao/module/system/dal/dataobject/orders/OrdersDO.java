package cn.iocoder.yudao.module.system.dal.dataobject.orders;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 收款订单 DO
 *
 * @author Awren
 */
@TableName("payment_orders")
@KeySequence("payment_orders_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDO extends BaseDO {

    /**
     * 请求支付订单号
     */
    @TableId(type = IdType.INPUT)
    private String orderId;
    /**
     * 支付金额（CNY，保留2位小数）
     */
    private BigDecimal amount;
    /**
     * 订单状态（1: 等待支付，2: 支付成功，3: 已过期）
     */
    private Integer status;
    /**
     * 异步回调地址
     */
    private String notifyUrl;
    /**
     * 同步跳转地址
     */
    private String redirectUrl;
    /**
     * 签名（MD5签名）
     */
    private String signature;
    /**
     * 实际支付金额（USDT，保留四位小数）
     */
    private BigDecimal actualAmount;
    /**
     * 钱包地址
     */
    private String token;
    /**
     * 过期时间（时间戳秒）
     */
    private Integer expirationTime;
    /**
     * 收银台地址
     */
    private String paymentUrl;
    /**
     * 回调接收时间
     */
    private LocalDateTime callbackTime;
    /**
     * 订单/交易创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 订单/交易更新时间
     */
    private LocalDateTime updatedAt;
    /**
     * 租户名称
     */
    private String tenantName;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码（加密存储）
     */
    private String password;

    private String blockTransactionId;
}