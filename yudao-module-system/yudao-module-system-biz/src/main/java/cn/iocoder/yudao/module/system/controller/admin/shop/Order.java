package cn.iocoder.yudao.module.system.controller.admin.shop;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@TableName("orders")
@Data
public class Order {
    @TableId
    private Long id;

    private String orderId;

    private String email;

    private BigDecimal total;
    private int paymentStatus;
    private String paymentUrl;

    private LocalDateTime date;

    private List<OrderItem> items;
}
