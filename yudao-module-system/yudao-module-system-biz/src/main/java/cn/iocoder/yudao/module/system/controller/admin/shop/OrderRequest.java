package cn.iocoder.yudao.module.system.controller.admin.shop;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequest extends PageParam {
    private String orderId;
    private String email;
    private Long userId;

    private String token;
    private BigDecimal total;
    private LocalDateTime date;
    private List<OrderItemRequest> items;
}
