package cn.iocoder.yudao.module.system.controller.admin.usdt.domain;

import lombok.Data;

@Data
public class TransactionRequest {

    private String trade_id; // 交易ID

    private String order_id; // 订单号

    private double amount; // 支付金额（单位：CNY，保留两位小数）

    private Integer actual_amount; // 实际支付金额（单位：USDT，保留四位小数）

    private String token; // 钱包地址

    private String block_transaction_id; // 区块链交易ID

    private String signature; // 签名（MD5签名）

    private int status; // 订单状态（1: 等待支付, 2: 支付成功, 3: 已过期）
}