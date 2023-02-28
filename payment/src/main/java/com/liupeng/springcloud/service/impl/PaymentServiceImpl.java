package com.liupeng.springcloud.service.impl;

import com.liupeng.springcloud.dao.PaymentDao;
import com.liupeng.springcloud.entities.Payment;
import com.liupeng.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassNmae PaymentServiceImpl：
 * TODO：
 * @Author liupeng、2022/9/26、7:09：
 * 1.0
 **/
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
