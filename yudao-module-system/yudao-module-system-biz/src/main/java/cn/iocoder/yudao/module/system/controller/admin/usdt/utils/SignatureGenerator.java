package cn.iocoder.yudao.module.system.controller.admin.usdt.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
public class SignatureGenerator {

    private static final String ADDRESS = "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t";
    private static final String TRADE_TYPE = "usdt.trc20";
    private static final String NOTIFY_URL = "https://example.com/callback";
    private static final String REDIRECT_URL = "http://localhost/login";
    private static final String SIGN_KEY = "AAGftbbtqB7";
    private static final String externalApiUrl = "http://103.233.255.171:8080/api/v1/order/create-transaction";


    /**
     * 生成 MD5 签名
     *
     * @param params      包含所有需要签名的参数的 Map
     * @param apiToken    API 接口认证 token
     * @return            生成的签名（小写）
     * @throws IllegalArgumentException 如果参数为空或 API Token 为空
     */
    public static String generateSignature(Map<String, String> params, String apiToken) {
        log.info("generateSignature start:{}", params);
        if (params == null || params.isEmpty()) {
            throw new IllegalArgumentException("参数不能为空");
        }
        if (apiToken == null || apiToken.isEmpty()) {
            throw new IllegalArgumentException("API Token 不能为空");
        }

        // 第一步：按照参数名的 ASCII 字典序排序
        TreeMap<String, String> sortedParams = new TreeMap<>(params);

        // 构建待加密参数字符串
        StringBuilder signStrBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            // 如果参数值为空，则不参与签名
            if (value != null && !value.isEmpty()) {
                if (signStrBuilder.length() > 0) {
                    signStrBuilder.append("&");
                }
                signStrBuilder.append(key).append("=").append(value);
            }
        }

        // 拼接 API Token
        signStrBuilder.append(apiToken);

        String signStr = signStrBuilder.toString();

        // 第二步：进行 MD5 运算并转换为小写
        String signature = md5(signStr).toLowerCase();

        return signature;
    }

    /**
     * 计算字符串的 MD5 哈希值
     *
     * @param input 需要计算 MD5 的字符串
     * @return      MD5 哈希值的十六进制表示
     * @throws RuntimeException 如果 MD5 算法不可用
     */
    private static String md5(String input) {
        try {
            // 获取 MD5 消息摘要实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算 MD5 哈希值
            byte[] digest = md.digest(input.getBytes("UTF-8"));
            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException("MD5 计算失败", e);
        }
    }

    // 示例主方法
    public static void main(String[] args) {
        // 示例参数
        TreeMap<String, String> params = new TreeMap<>();
        params.put("address", ADDRESS);
        params.put("trade_type", TRADE_TYPE);
        params.put("order_id", "6921484f9211");
        params.put("amount", "100.0");
        params.put("notify_url", NOTIFY_URL);
        params.put("redirect_url", REDIRECT_URL);


        // 生成签名
        String signature = generateSignature(params, SIGN_KEY);

        // 输出结果
        System.out.println("生成的签名: " + signature);
    }
}