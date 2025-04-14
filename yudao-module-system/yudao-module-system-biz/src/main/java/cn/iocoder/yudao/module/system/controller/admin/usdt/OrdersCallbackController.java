package cn.iocoder.yudao.module.system.controller.admin.usdt;

import cn.iocoder.yudao.framework.common.util.collection.SetUtils;
import cn.iocoder.yudao.framework.tenant.core.util.TenantUtils;
import cn.iocoder.yudao.module.system.controller.admin.auth.vo.AuthLoginRespVO;
import cn.iocoder.yudao.module.system.controller.admin.auth.vo.AuthRegisterReqVO;
import cn.iocoder.yudao.module.system.controller.admin.usdt.domain.TransactionRequest;
import cn.iocoder.yudao.module.system.controller.admin.usdt.service.OrdersCallbackService;
import cn.iocoder.yudao.module.system.dal.dataobject.orders.OrdersDO;
import cn.iocoder.yudao.module.system.dal.mysql.orders.OrdersMapper;
import cn.iocoder.yudao.module.system.service.auth.AdminAuthService;
import cn.iocoder.yudao.module.system.service.permission.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersCallbackController {

    private final OrdersCallbackService ordersCallbackService;
    private final AdminAuthService authService;
    private final OrdersMapper ordersMapper;
    private final PermissionService permissionService;
    public OrdersCallbackController(OrdersCallbackService ordersCallbackService, AdminAuthService authService, OrdersMapper ordersMapper,PermissionService permissionService) {
        this.ordersCallbackService = ordersCallbackService;
        this.authService = authService;
        this.ordersMapper = ordersMapper;
        this.permissionService = permissionService;
    }

    /**
     * 异步回调接口，接收 Epusdt 的支付通知
     *
     * @param requestTransaction 回调请求体
     * @return 响应结果
     */
    @PostMapping("/callback")
    @PermitAll
    public ResponseEntity<?> handleCallback(@RequestBody TransactionRequest requestTransaction) {
        try {
            // 验证签名
            ordersCallbackService.verifySignature(requestTransaction);

            // 处理订单状态
            ordersCallbackService.handleOrderStatus(requestTransaction);
            OrdersDO order = ordersMapper.selectById(requestTransaction.getOrder_id());
            // 创建账号
            AuthRegisterReqVO createReqVO = new AuthRegisterReqVO();
            createReqVO.setNickname(order.getNickname());
            createReqVO.setPassword(order.getPassword());
            createReqVO.setUsername(order.getUsername());
            AuthLoginRespVO authLoginRespVO = authService.registerNoValidateCaptcha(createReqVO);
            permissionService.AutoAssignUserRole(authLoginRespVO.getUserId(), SetUtils.asSet(159L));
            // 返回成功响应
            return ResponseEntity.ok().body("ok");

        } catch (IllegalArgumentException e) {
            // 签名验证失败或其他业务逻辑错误
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            // 记录异常日志
            ordersCallbackService.logError(e);
            return ResponseEntity.internalServerError().body("Internal server error");
        }
    }
}