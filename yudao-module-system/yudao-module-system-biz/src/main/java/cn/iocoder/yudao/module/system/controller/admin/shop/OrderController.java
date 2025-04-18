package cn.iocoder.yudao.module.system.controller.admin.shop;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.system.controller.admin.projects.vo.ProjectsRespVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

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
    public CommonResult<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        // 构造 Order 实体
        Order order = new Order();
        order.setOrderId(orderRequest.getOrderId());
        order.setEmail(orderRequest.getEmail());
        order.setTotal(orderRequest.getTotal());
        order.setDate(LocalDateTime.now());

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

        return success(order);
    }

    /**
     * 根据邮箱查询订单及其明细
     */
    @GetMapping("/by-email")
    public CommonResult<List<Order>> getOrdersByEmail(@RequestParam String email) {
        List<Order> orders = orderMapper.selectOrdersByEmail(email);
        for (Order order : orders) {
            List<OrderItem> items = orderMapper.selectItemsByOrderId(order.getId());
            order.setItems(items);
        }
        return success(orders);
    }

    /**
     * 获得订单分页
     */
    @GetMapping("/page")
    @Operation(summary = "获得订单分页")
    public CommonResult<List<Order>> getOrders(
            @RequestParam(required = false) String email) {
        List<Order> orders;
        if (StringUtils.hasText(email)) {
            // 有有效 email，就按邮箱过滤
            orders = orderMapper.selectOrdersByEmail(email);
        } else {
            // 未传 email 或空串，则返回所有订单
            orders = orderMapper.selectAllOrders();
        }
        // 为每个订单加载明细
        for (Order order : orders) {
            List<OrderItem> items = orderMapper.selectItemsByOrderId(order.getId());
            order.setItems(items);
        }
        return CommonResult.success(orders);
    }
}








