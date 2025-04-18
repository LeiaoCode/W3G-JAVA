package cn.iocoder.yudao.module.system.controller.admin.usdt;

import cn.hutool.core.lang.UUID;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.controller.admin.orders.vo.OrdersSaveReqVO;
import cn.iocoder.yudao.module.system.controller.admin.usdt.utils.SignatureGenerator;
import cn.iocoder.yudao.module.system.service.orders.OrdersService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.error;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class TransactionController {


    @Resource
    private OrdersService ordersService;

    private static final String ADDRESS = "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t";
    private static final String TRADE_TYPE = "usdt.trc20";
    private static final String NOTIFY_URL = "http://103.233.255.171:48080/api/v1/orders/callback";
    private static final String REDIRECT_URL = "";
    private static final String SIGN_KEY = "AAGftbbtqB7";
    private static final String externalApiUrl = "http://103.233.255.171:8080/api/v1/order/create-transaction";

    @PostMapping("/create-transaction")
    @PermitAll
    public CommonResult<String> createTransaction(@RequestBody @Validated OrdersSaveReqVO reqVO) {
        try {


            // 动态生成订单号
            String orderId = UUID.randomUUID().toString().replace("-", "").substring(0, 12);


            // 构造 OrdersSaveReqVO 对象
            OrdersSaveReqVO createReqVO = new OrdersSaveReqVO();
            createReqVO.setTenantName("1");
            createReqVO.setUsername(reqVO.getUsername());
            createReqVO.setNickname(reqVO.getNickname());
            createReqVO.setPassword(reqVO.getPassword());
            createReqVO.setOrderId(orderId);
            createReqVO.setAmount(reqVO.getAmount());
            createReqVO.setStatus(1);
            createReqVO.setNotifyUrl(NOTIFY_URL);
            createReqVO.setRedirectUrl(REDIRECT_URL);
            createReqVO.setToken(ADDRESS);
            createReqVO.setExpirationTime(600);
            createReqVO.setCallbackTime(LocalDateTime.now());
            createReqVO.setCreatedAt(LocalDateTime.now());
            createReqVO.setUpdatedAt(LocalDateTime.now());


            // 构造请求参数
            BigDecimal amount = BigDecimal.valueOf(reqVO.getAmount()).setScale(2, RoundingMode.HALF_UP);
            Map<String, String> params = new HashMap<>();
            // 根据 token 的值是否存在，决定是否添加 address 参数
            if (reqVO.getToken() != null && !reqVO.getToken().isEmpty()) {
                params.put("address", reqVO.getToken());
            }
            params.put("trade_type", TRADE_TYPE);
            params.put("order_id", orderId);
            params.put("amount", amount.toString());
            params.put("notify_url", NOTIFY_URL);
            params.put("redirect_url", REDIRECT_URL);

            // 生成签名
            String signature = SignatureGenerator.generateSignature(params, SIGN_KEY);
            params.put("signature", signature);
            createReqVO.setSignature(signature); // 签名稍后补充
            // 将参数转换为 JSON 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(params);

            // 调用外部接口
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(externalApiUrl, requestBody, String.class);
            log.debug(String.valueOf(response));
            // 解析 JSON 响应
            ObjectMapper objectMappers = new ObjectMapper();
            JsonNode rootNode = objectMappers.readTree(response.getBody());
            JsonNode dataNode = rootNode.get("data");

            if (dataNode != null && dataNode.has("payment_url")) {
                String paymentUrl = dataNode.get("payment_url").asText();
                String actualAmount = dataNode.get("actual_amount").asText();
                createReqVO.setPaymentUrl(paymentUrl);
                createReqVO.setActualAmount(BigDecimal.valueOf(Double.parseDouble(actualAmount)));

                ordersService.createOrders(createReqVO);
                // 返回 payment_url 给前端
                return success(paymentUrl);
            } else {
                return error(290, "Payment URL not found in response");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return error(500, "Error occurred: " + e.getMessage());
        }
    }
}
