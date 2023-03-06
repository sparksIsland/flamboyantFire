package com.spark.springcloud.service;

import com.spark.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @param null
 * @author liupeng
 * @date 2022/9/26
 * @return
 * @throws
 * @update [序号][日期YYYY-MM-DD] [更改人姓名][变更描述]
 * @since
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
