package com.hro.core.cloudbase.enums;

import java.util.Arrays;
import java.util.List;

/**
 * 结果集状态码
 */
public enum ResultCodeEnum {

    UNKNOW(-1, "未知异常"),
    FORBIDDEN(403, "没有权限访问"),
    RESOURCE_NOT_FOUND(404, "资源不存在"),
    SUCCESS(100, "成功"),
    FAILURE(-100, "异常");

    private int code;
    private String desc;
    private static ResultCodeEnum[] values = values();

    ResultCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public static ResultCodeEnum getResult(int code) {
        ResultCodeEnum result = UNKNOW;
        for(ResultCodeEnum codeEnum : values) {
            if(codeEnum.getCode() == code) {
                result = codeEnum;
                break;
            }
        }
        return result;
    }

    public static List<ResultCodeEnum> getResultCodes() {
        ResultCodeEnum[] enums = values();
        List<ResultCodeEnum> list = Arrays.asList(enums);
        return list;
    }

}
