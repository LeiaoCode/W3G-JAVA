package cn.iocoder.yudao.module.system.controller.admin.shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    /**
     * 接收前端提交的订单请求，并保存到数据库
     */
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        // 构造 Order 实体
        Order order = new Order();
        order.setOrderId(orderRequest.getOrderId());
        order.setEmail(orderRequest.getEmail());
        order.setTotal(orderRequest.getTotal());
        order.setDate(orderRequest.getDate() != null ? orderRequest.getDate() : LocalDateTime.now());

        // 插入订单，order.id 会回写
        orderMapper.insertOrder(order);

        // 构造并插入 OrderItem
        List<OrderItem> items = orderRequest.getItems().stream().map(req -> {
            OrderItem item = new OrderItem();
            item.setOrderId(order.getId());
            item.setName(req.getName());
            item.setPrice(req.getPrice());
            item.setQuantity(req.getQuantity());
            return item;
        }).collect(Collectors.toList());
        orderMapper.insertOrderItems(items);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    /**
     * 根据邮箱查询订单及其明细
     */
    @GetMapping("/by-email")
    public ResponseEntity<List<Order>> getOrdersByEmail(@RequestParam String email) {
        List<Order> orders = orderMapper.selectOrdersByEmail(email);
        for (Order order : orders) {
            List<OrderItem> items = orderMapper.selectItemsByOrderId(order.getId());
            order.setItems(items);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}








