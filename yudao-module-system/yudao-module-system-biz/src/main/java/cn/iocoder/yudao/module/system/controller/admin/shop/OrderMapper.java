package cn.iocoder.yudao.module.system.controller.admin.shop;

import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Select("SELECT id, order_id AS orderId, email, total, date FROM orders WHERE email = #{email}")
    List<Order> selectOrdersByEmail(@Param("email") String email);

    @Select("SELECT id, order_id AS orderId, name, price, quantity FROM order_items WHERE order_id = #{orderId}")
    List<OrderItem> selectItemsByOrderId(@Param("orderId") Long orderId);
}
