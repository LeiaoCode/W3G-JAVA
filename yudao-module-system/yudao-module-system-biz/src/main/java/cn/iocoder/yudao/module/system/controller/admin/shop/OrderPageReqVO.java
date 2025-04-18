package cn.iocoder.yudao.module.system.controller.admin.shop;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 订单分页查询 Request VO
 */
@Data
@Schema(description = "订单分页查询 Request VO")
public class OrderPageReqVO extends PageParam {

    @Schema(description = "订单记录主键", example = "1")
    private Long id;

    @Schema(description = "订单编号", example = "ORD123456")
    private String orderId;

    @Schema(description = "订单状态", example = "1")
    private int paymentStatus;
    @Schema(description = "用户邮箱", example = "user@example.com")
    private String email;

    @Schema(description = "下单开始时间")
    private LocalDateTime startDate;

    @Schema(description = "下单结束时间")
    private LocalDateTime endDate;

}
