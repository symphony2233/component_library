package org.example.controller;


import org.example.utils.HttpContextUtil;
import org.example.utils.IpPraseUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;



/**
 * @description: IP相关操作Controller
 * @author: symphony
 * @create: 2024/04/14
 **/
@RestController
public class IPController {

    // 获取ip
    @RequestMapping("/getIP")
    public String getIP(){
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IpPraseUtils.getIpAddr(request);
        System.out.println("IPController.getIP.ip:"  +  ip);
        return ip;
    }

    // 获取ip
    @RequestMapping("/getAddr")
    public String getAddr(){
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String addr = IpPraseUtils.getCityInfo(getIP());
        System.out.println("IPController.getIP.ip:"  +  addr);
        return addr;
    }
}
