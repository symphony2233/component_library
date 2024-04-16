package org.example.utils;


import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * @description: ip地址解析 工具了
 * @author: symphony
 * @create: 2024/04/14
 **/
public class IpPraseUtils {
    private static final String UNKNOWN = "unknown";


    public IpPraseUtils() {
    }

    /**
     * 根据IP地址查询登录来源
     *
     * @param ip
     * @return
     */
    public static String getCityInfo(String ip) {
        try {
            // Resource resource = new ClassPathResource("ip2region/ip2region.xdb");
            // Searcher searcher = Searcher.newWithFileOnly(resource.getFile().getPath());
            // 获取资源输入流
            InputStream inputStream = IpPraseUtils.class.getResourceAsStream("/ip2region/ip2region.xdb");
            // 创建临时文件
            File tempFile = File.createTempFile("ip2region", ".xdb");
            // 将资源复制到临时文件中
            Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            // 使用临时文件路径创建 Searcher
            Searcher searcher = Searcher.newWithFileOnly(tempFile.getPath());
            //开始查询
            return searcher.searchByStr(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //默认返回空字符串
        return "";
    }





    /**
     * 获取 IP地址
     * 使用 Nginx等反向代理软件， 则不能通过 request.getRemoteAddr()获取 IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，
     * X-Forwarded-For中第一个非 unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP，多个IP按照','分割
        if (!StringUtils.isEmpty(ip) && ip.contains(",")) {
            String[] ipArray = ip.split(",");
            if (ArrayUtils.isNotEmpty(ipArray)) {
                ip = ipArray[0];
            }
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    // 测试方法
    public static void main(String[] args) {
        //204.16.111.255
        System.out.println(getCityInfo("204.16.111.255"));
        System.out.println(getCityInfo("103.152.113.207"));
        System.out.println(getCityInfo("210.42.151.13"));
        System.out.println(getCityInfo("103.20.198.102"));
        System.out.println(getCityInfo("103.20.198.102"));
        System.out.println(getCityInfo("164.114.53.60"));
        System.out.println(getCityInfo("220.248.12.158"));
        System.out.println(getCityInfo("7.52.236.180"));
        String s = getCityInfo("220.248.12.158");
        System.out.println("s is " + s);
        String[] strs = s.split("\\|");
        for (String st : strs){
            System.out.println(st);
        }

    }

}
