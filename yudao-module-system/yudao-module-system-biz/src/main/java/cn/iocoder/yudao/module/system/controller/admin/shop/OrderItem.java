package cn.iocoder.yudao.module.system.controller.admin.shop;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {
    private Long id;
    private Long orderId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
