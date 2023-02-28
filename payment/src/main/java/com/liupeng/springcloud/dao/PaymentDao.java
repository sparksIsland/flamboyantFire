package com.liupeng.springcloud.dao;

import com.liupeng.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    /**
     *  创建订单
     * @author liupeng
     * @date 2022/9/26
     * @param payment
     * @return int
     * @throws
     * @since
     * @update [序号][日期YYYY-MM-DD] [更改人姓名][变更描述]
     */
    public int create(Payment payment);

    /**
     * 通过 ID 查询订单
     * @author liupeng
     * @date 2022/9/26
     * @param id
     * @return com.liupeng.springcloud.entities.Payment
     * @throws
     * @since
     * @update [序号][日期YYYY-MM-DD] [更改人姓名][变更描述]
     */
    public Payment getPaymentById(@Param("id") Long id);

}
