package org.example.Controller;

import cn.hutool.core.util.DesensitizedUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试Controller
 * @author: symphony
 * @create: 2024/04/22
 **/

@RestController
@Slf4j
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();


    // 获取用户信息
    @RequestMapping("/getUserInfo")
    public User getUserInfo(){
       User user = new User(1,"东方不败", "411021199901102321","12345678","13723231234",
               "湖北省武汉市冠山街道武汉大学","234324@qq.com","99989password","6217000130008255666");

       if (log.isInfoEnabled()) {
            log.info("PermissionController.add.dto:{}", JSON.toJSONString(user));// 数据未脱敏
        }
        log.info(user.toString()); // 数据未脱敏

        // 打印用户对象，先将对象序列化并脱敏，然后打印到日志中
        try {
            String serializedUser = objectMapper.writeValueAsString(user);
            logger.info("Serialized user: {}", serializedUser);
            log.info("log4j Serialized user: {}", serializedUser);
        } catch (Exception e) {
            logger.error("Error serializing user: {}", e.getMessage());
        }



        return user;

    }

    // 测试序列化
    @RequestMapping("/testDesensitization")
    public void testDesensitization(){
        String phone="13723231234";
        System.out.println(DesensitizedUtil.mobilePhone(phone)); //输出：137****1234

        String bankCard="6217000130008255666";
        System.out.println(DesensitizedUtil.bankCard(bankCard));// 输出：6217 **** **** *** 5666

        String idCardNum="411021199901102321";
        //只显示前4位和后2位
        System.out.println(DesensitizedUtil.idCardNum(idCardNum,4,2));//输出：4110************21

        String password="www.jd.com_35711";
        System.out.println(DesensitizedUtil.password(password)); //输出：****************
    }
}
