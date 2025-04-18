package cn.iocoder.yudao.module.system.controller.admin.shop;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {
    private Long id;
    private Long orderId;
    private String name;
    private int paymentStatus;
    private BigDecimal price;
    private Integer quantity;
}
