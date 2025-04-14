package cn.iocoder.yudao.module.system.service.orders;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.orders.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.orders.OrdersDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.system.dal.mysql.orders.OrdersMapper;

import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.ORDERS_NOT_EXISTS;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 收款订单 Service 实现类
 *
 * @author Awren
 */
@Service
@Validated
public class OrdersServiceImpl implements OrdersService {

    @Resource
    private OrdersMapper ordersMapper;

    @Override
    public String createOrders(OrdersSaveReqVO createReqVO) {
        // 插入
        OrdersDO orders = BeanUtils.toBean(createReqVO, OrdersDO.class);
        ordersMapper.insert(orders);
        // 返回
        return orders.getOrderId();
    }

    @Override
    public void updateOrders(OrdersSaveReqVO updateReqVO) {
        // 校验存在
        validateOrdersExists(updateReqVO.getOrderId());
        // 更新
        OrdersDO updateObj = BeanUtils.toBean(updateReqVO, OrdersDO.class);
        ordersMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrders(String id) {
        // 校验存在
        validateOrdersExists(id);
        // 删除
        ordersMapper.deleteById(id);
    }

    private void validateOrdersExists(String id) {
        if (ordersMapper.selectById(id) == null) {
            throw exception(ORDERS_NOT_EXISTS);
        }
    }

    @Override
    public OrdersDO getOrders(String id) {
        return ordersMapper.selectById(id);
    }

    @Override
    public PageResult<OrdersDO> getOrdersPage(OrdersPageReqVO pageReqVO) {
        return ordersMapper.selectPage(pageReqVO);
    }

    @Override
    public void updateOrderStatus(String orderId, String transactionId, boolean isSuccess) {
        OrdersDO order = ordersMapper.selectById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found: " + orderId);
        }

        // 更新订单状态
        order.setStatus(isSuccess ? 2 : 3); // 2: 支付成功, 3: 支付失败
        order.setBlockTransactionId(transactionId);
        ordersMapper.updateById(order);
    }
}