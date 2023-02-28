package com.liupeng.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @ClassNmae CommonResult：规范返回格式
 *
 * 返回代码
 * 返回信息
 * 返回数据
 *
 * TODO：
 * @Author liupeng、2022/9/23、6:46：
 * 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
