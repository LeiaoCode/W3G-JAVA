package cn.iocoder.yudao.module.system.controller.admin.shop;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO orders(order_id, email, total, date) VALUES(#{orderId}, #{email}, #{total}, #{date})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertOrder(Order order);

    @Insert({
            "<script>",
            "INSERT INTO order_items(order_id, name, price, quantity) VALUES",
            "<foreach collection='items' item='item' separator=','>",
            "(#{item.orderId}, #{item.name}, #{item.price}, #{item.quantity})",
            "</foreach>",
            "</script>"
    })
    int insertOrderItems(@Param("items") List<OrderItem> items);

    @Select("SELECT id, order_id AS orderId, email,payment_status as paymentStatus,total,payment_url as paymentUrl, date FROM orders WHERE email = #{email}")
    List<Order> selectOrdersByEmail(@Param("email") String email);

    @Select("SELECT id, order_id AS orderId,name, price, quantity FROM order_items WHERE order_id = #{orderId}")
    List<OrderItem> selectItemsByOrderId(@Param("orderId") Long orderId);
    @Select("SELECT id, order_id AS orderId, email, total, payment_status as paymentStatus,payment_url as paymentUrl,date FROM orders")
    List<Order> selectAllOrders();

    /**
     * 根据订单号更新付款状态
     *
     * @param orderId 订单编号
     * @param status  付款状态(0=未支付,1=已支付,2=已退款等)
     * @return 更新记录数
     */
    @Update("UPDATE orders SET payment_status = #{status} WHERE order_id = #{orderId}")
    int updatePaymentStatusByOrderId(@Param("orderId") String orderId, @Param("status") Integer status);
}
