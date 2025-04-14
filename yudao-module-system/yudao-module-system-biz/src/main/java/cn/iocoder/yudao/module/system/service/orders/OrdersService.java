package cn.iocoder.yudao.module.system.service.orders;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.orders.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.orders.OrdersDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 收款订单 Service 接口
 *
 * @author Awren
 */
public interface OrdersService {

    /**
     * 创建收款订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    String createOrders(@Valid OrdersSaveReqVO createReqVO);

    /**
     * 更新收款订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrders(@Valid OrdersSaveReqVO updateReqVO);

    /**
     * 删除收款订单
     *
     * @param id 编号
     */
    void deleteOrders(String id);

    /**
     * 获得收款订单
     *
     * @param id 编号
     * @return 收款订单
     */
    OrdersDO getOrders(String id);

    /**
     * 获得收款订单分页
     *
     * @param pageReqVO 分页查询
     * @return 收款订单分页
     */
    PageResult<OrdersDO> getOrdersPage(OrdersPageReqVO pageReqVO);


    /**
     * 更新订单状态为支付成功
     *
     * @param orderId       订单号
     * @param transactionId 交易ID
     * @param isSuccess     是否支付成功
     */
    void updateOrderStatus(String orderId, String transactionId, boolean isSuccess);
}