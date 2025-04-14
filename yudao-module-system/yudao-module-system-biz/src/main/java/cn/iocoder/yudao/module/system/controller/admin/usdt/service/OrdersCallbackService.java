package cn.iocoder.yudao.module.system.controller.admin.usdt.service;

import cn.iocoder.yudao.module.system.controller.admin.usdt.utils.SignatureGenerator;
import cn.iocoder.yudao.module.system.controller.admin.usdt.domain.TransactionRequest;
import cn.iocoder.yudao.module.system.dal.dataobject.orders.OrdersDO;
import cn.iocoder.yudao.module.system.dal.mysql.orders.OrdersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.TreeMap;

@Service
public class OrdersCallbackService {

    private static final Logger logger = LoggerFactory.getLogger(OrdersCallbackService.class);

    private static final String TRADE_TYPE = "usdt.trc20";
    private static final String NOTIFY_URL = "https://example.com/callback";
    private static final String REDIRECT_URL = "http://localhost/login";
    private static final String SIGN_KEY = "AAGftbbtqB7";
    private static final String ADDRESS = "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t";

    private final OrdersMapper ordersMapper;

    public OrdersCallbackService(OrdersMapper ordersMapper) {
        this.ordersMapper = ordersMapper;
    }

    /**
     * 验证签名
     *
     * @param requestTransaction 回调请求体
     */
    public void verifySignature(TransactionRequest requestTransaction) {
        BigDecimal amount = BigDecimal.valueOf(requestTransaction.getAmount()).setScale(2, RoundingMode.HALF_UP);

        // 拼接参数
        TreeMap<String, String> params = new TreeMap<>();
        params.put("address", ADDRESS);
        params.put("trade_type", TRADE_TYPE);
        params.put("order_id", requestTransaction.getOrder_id());
        params.put("amount", amount.toString());
        params.put("notify_url", NOTIFY_URL);
        params.put("redirect_url", REDIRECT_URL);
        // 计算 MD5 签名
        String calculatedSignature = SignatureGenerator.generateSignature(params, SIGN_KEY);
        logger.info("calculated signature: {}", calculatedSignature);
        // 验证签名
        if (!calculatedSignature.equalsIgnoreCase(requestTransaction.getSignature())) {
            throw new IllegalArgumentException("Invalid signature");
        }
    }

    /**
     * 处理订单状态
     *
     * @param requestTransaction 回调请求体
     */
    public void handleOrderStatus(TransactionRequest requestTransaction) {
        String orderId = requestTransaction.getOrder_id();
        int status = requestTransaction.getStatus();
        String transactionId = requestTransaction.getBlock_transaction_id();

        // 根据状态更新订单
        OrdersDO order = ordersMapper.selectById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found: " + orderId);
        }
        ordersMapper.updateStatus(
                orderId,
                transactionId,
                LocalDateTime.now(),
                status,
                requestTransaction.getActual_amount()
        );
    }

    /**
     * 记录错误日志
     *
     * @param e 异常
     */
    public void logError(Exception e) {
        logger.error("Error occurred in callback processing", e);
    }
}