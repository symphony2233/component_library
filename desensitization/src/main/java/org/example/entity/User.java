package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.desensitization.Desensitization;
import org.example.desensitization.DesensitizationTypeEnum;

/**
 * @description: 用户类
 * @author: symphony
 * @create: 2024/04/22
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 用户id
     */
    @Desensitization(type = DesensitizationTypeEnum.USER_ID)
    private Integer userId;
    /**
     * 用户名字
     */
    @Desensitization(type = DesensitizationTypeEnum.CHINESE_NAME)
    private String name;
    /**
     * 用户身份证号
     */
    @Desensitization(type = DesensitizationTypeEnum.ID_CARD)
    private String idCardNum;
    /**
     * 用户座机号
     */
    @Desensitization(type = DesensitizationTypeEnum.MOBILE_PHONE)
    private String mobilePhone;
    /**
     * 用户手机号
     */
    @Desensitization(type = DesensitizationTypeEnum.FIXED_PHONE)
    private String phone;
    /**
     * 用户地址
     */
    @Desensitization(type = DesensitizationTypeEnum.ADDRESS)
    private String address;
    /**
     * 用户邮箱
     */
    @Desensitization(type = DesensitizationTypeEnum.EMAIL)
    private String email;
    /**
     * 用户密码
     */
    @Desensitization(type = DesensitizationTypeEnum.PASSWORD)
    private String password;
    /**
     * 用户银行卡
     */
    @Desensitization(type = DesensitizationTypeEnum.BANK_CARD)
    private String bankcard;
}
