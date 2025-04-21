package cn.iocoder.yudao.module.system.controller.admin.shop;

import cn.hutool.core.lang.UUID;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils;
import cn.iocoder.yudao.module.system.controller.admin.orders.vo.OrdersSaveReqVO;
import cn.iocoder.yudao.module.system.controller.admin.projects.vo.ProjectsRespVO;
import cn.iocoder.yudao.module.system.controller.admin.usdt.utils.SignatureGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.error;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderMapper orderMapper;
    private static final String ADDRESS = "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t";
    private static final String TRADE_TYPE = "usdt.trc20";
    private static final String NOTIFY_URL = "http://103.233.255.171:48080/api/v1/orders/orderCallback";
    private static final String REDIRECT_URL = "http://localhost/login";
    private static final String SIGN_KEY = "AAGftbbtqB7";
    private static final String externalApiUrl = "https://pay.liandongstar.live/api/v1/order/create-transaction";

    @Autowired
    public OrderController(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    /**
     * 接收前端提交的订单请求，并保存到数据库
     */
    @PostMapping
    public CommonResult<String> createOrder(@RequestBody OrderRequest orderRequest) {
        // 构造 Order 实体
        Order order = new Order();
        Long userId = WebFrameworkUtils.getLoginUserId();
        System.out.println(userId);
        order.setOrderId(orderRequest.getOrderId());
        order.setEmail(orderRequest.getEmail());
        order.setTotal(orderRequest.getTotal());
        order.setDate(LocalDateTime.now());
        order.setUserId(userId);
        String paymentUrl = createTransaction(orderRequest);
        order.setPaymentUrl(paymentUrl);

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
        return success(paymentUrl);
    }

    /**
     * 根据邮箱查询订单及其明细
     */
    @GetMapping("/by-email")
    public CommonResult<List<Order>> getOrdersByEmail(
            @RequestParam(required = false) String email) {
        Long userId = WebFrameworkUtils.getLoginUserId();

        List<Order> orders;
        if (StringUtils.hasText(email)) {
            orders = orderMapper.selectOrdersByEmailAndUserId(email, userId);
        } else {
            orders = orderMapper.selectOrdersByUserId(userId);
        }
        // 加载明细
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


    /**
     * 创建usdt订单
     *
     * @param orderRequest
     * @return {@link String }
     */
    public String createTransaction(OrderRequest orderRequest) {
        try {
            // 构造请求参数
            BigDecimal amount = orderRequest.getTotal().setScale(2, RoundingMode.HALF_UP);
            Map<String, String> params = new HashMap<>();
            // 根据 token 的值是否存在，决定是否添加 address 参数
            if (orderRequest.getToken() != null && !orderRequest.getToken().isEmpty()) {
                params.put("address", orderRequest.getToken());
            }
            params.put("trade_type", TRADE_TYPE);
            params.put("order_id", orderRequest.getOrderId());
            params.put("amount", amount.toString());
            params.put("notify_url", NOTIFY_URL);
            params.put("redirect_url", REDIRECT_URL);
            System.out.println(params);
            // 生成签名
            String signature = SignatureGenerator.generateSignature(params, SIGN_KEY);
            params.put("signature", signature);
            // 将参数转换为 JSON 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(params);

            // 调用外部接口
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(externalApiUrl, requestBody, String.class);
            // 解析 JSON 响应
            ObjectMapper objectMappers = new ObjectMapper();
            System.out.println(response.getBody());
            JsonNode rootNode = objectMappers.readTree(response.getBody());
            JsonNode dataNode = rootNode.get("data");
            if (dataNode != null && dataNode.has("payment_url")) {
                String paymentUrl = dataNode.get("payment_url").asText();
                // 返回 payment_url 给前端
                return paymentUrl;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}








