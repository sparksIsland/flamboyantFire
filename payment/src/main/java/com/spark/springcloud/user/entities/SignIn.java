package com.spark.springcloud.user.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassNmae Payment：
 * TODO：
 * @Author liupeng、2022/9/23、6:45：
 * 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignIn implements Serializable {
    private Long id;
    private String userName;
}
