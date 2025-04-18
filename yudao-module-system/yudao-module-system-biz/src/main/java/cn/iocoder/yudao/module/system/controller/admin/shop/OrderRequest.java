package cn.iocoder.yudao.module.system.controller.admin.shop;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequest {
    private String orderId;
    private String email;
    private BigDecimal total;
    private LocalDateTime date;
    private List<OrderItemRequest> items;
}
