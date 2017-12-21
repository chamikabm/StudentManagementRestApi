package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Entity.PaymentEntity;
import com.student.management.rest.api.Model.Payment;
import com.student.management.rest.api.Repository.PaymentRepository;
import com.student.management.rest.api.Service.PaymentService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> findAllPayments() {
        List<PaymentEntity> paymentEntities = new ArrayList<>();
       this.paymentRepository.findAll().forEach(paymentEntities::add);

        return  paymentEntities.stream()
                .map(this::convertDaoToDto)
                .collect(Collectors.toList());
    }

    private Payment convertDaoToDto(PaymentEntity paymentEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(paymentEntity, Payment.class);
    }

    private PaymentEntity convertDtoToDao(Payment payment) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(payment, PaymentEntity.class);
    }
}