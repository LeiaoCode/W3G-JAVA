package cn.iocoder.yudao.module.system.dal.mysql.orders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.orders.OrdersDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.orders.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 收款订单 Mapper
 *
 * @author Awren
 */
@Mapper
public interface OrdersMapper extends BaseMapperX<OrdersDO> {

    default PageResult<OrdersDO> selectPage(OrdersPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrdersDO>()
                .eqIfPresent(OrdersDO::getAmount, reqVO.getAmount())
                .eqIfPresent(OrdersDO::getStatus, reqVO.getStatus())
                .eqIfPresent(OrdersDO::getNotifyUrl, reqVO.getNotifyUrl())
                .eqIfPresent(OrdersDO::getRedirectUrl, reqVO.getRedirectUrl())
                .eqIfPresent(OrdersDO::getSignature, reqVO.getSignature())
                .eqIfPresent(OrdersDO::getActualAmount, reqVO.getActualAmount())
                .eqIfPresent(OrdersDO::getToken, reqVO.getToken())
                .betweenIfPresent(OrdersDO::getExpirationTime, reqVO.getExpirationTime())
                .eqIfPresent(OrdersDO::getPaymentUrl, reqVO.getPaymentUrl())
                .betweenIfPresent(OrdersDO::getCallbackTime, reqVO.getCallbackTime())
                .eqIfPresent(OrdersDO::getCreatedAt, reqVO.getCreatedAt())
                .eqIfPresent(OrdersDO::getUpdatedAt, reqVO.getUpdatedAt())
                .likeIfPresent(OrdersDO::getTenantName, reqVO.getTenantName())
                .likeIfPresent(OrdersDO::getUsername, reqVO.getUsername())
                .likeIfPresent(OrdersDO::getNickname, reqVO.getNickname())
                .eqIfPresent(OrdersDO::getPassword, reqVO.getPassword())
                .orderByDesc(OrdersDO::getOrderId));
    }

    @Update("UPDATE payment_orders SET status = #{status}, block_transaction_id = #{transactionId}, actual_amount = #{actualAmount} , callback_time = #{callbackTime} WHERE order_id = #{orderId}")
    void updateStatus(
            @Param("orderId") String orderId,
            @Param("transactionId") String transactionId,
            @Param("callbackTime") LocalDateTime callbackTime,
            @Param("status") Integer status,
            @Param("actualAmount") Integer actualAmount
    );

    @Select("SELECT * FROM payment_orders WHERE order_id = #{orderId}")
    OrdersDO selectById(String orderId);

}