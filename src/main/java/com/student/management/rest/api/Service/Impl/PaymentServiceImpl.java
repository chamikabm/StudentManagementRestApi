package com.student.management.rest.api.Service.Impl;

import com.student.management.rest.api.Entity.PaymentEntity;
import com.student.management.rest.api.Manager.PaymentManager;
import com.student.management.rest.api.Model.Payment;
import com.student.management.rest.api.Repository.PaymentRepository;
import com.student.management.rest.api.Service.PaymentService;
import com.student.management.rest.api.Util.CustomErrorType;
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
        LOGGER.info("SMAPI - Payment - Service- findAllPayments method invoked.");

        List<PaymentEntity> paymentEntities = new ArrayList<>();
        this.paymentRepository.findAll().forEach(paymentEntities::add);

        LOGGER.info("SMAPI - Payment - Service- findAllPayments method processed.");
        return  paymentEntities.stream()
                .map(this::convertDaoToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Payment findById(Integer id) throws CustomErrorType {
        LOGGER.info("SMAPI - Payment - Service- findById method invoked.");

        PaymentEntity paymentEntity = this.paymentRepository.findOne(id);
        if (paymentEntity == null) {
            LOGGER.info("SMAPI - Payment - Service- findById method processed.");
            throw new CustomErrorType("Payment with id " + id + " not found");
        } else {
            LOGGER.info("SMAPI - Payment - Service- findById method processed.");
            return convertDaoToDto(paymentEntity);
        }
    }

    @Override
    public boolean isPaymentExist(Payment payment) {
        LOGGER.info("SMAPI - Payment - Service- isPaymentExist method invoked.");

        LOGGER.info("SMAPI - Payment - Service- isPaymentExist method processed.");

        return this.paymentRepository.exists(payment.getId());
    }

    @Override
    public void addNewPayment(Payment payment) throws CustomErrorType {

        LOGGER.info("SMAPI - Payment - Service- addNewPayment method invoked.");

        PaymentManager paymentManager = new PaymentManager();

        if (paymentManager.isValidPayment(payment)) {
            PaymentEntity paymentEntity = convertDtoToDao(payment);
            this.paymentRepository.save(paymentEntity);
            LOGGER.info("SMAPI - Payment - Service- addNewPayment method processed.");

        } else {
            LOGGER.info("SMAPI - Payment - Service- addNewPayment method processed.");

            throw new CustomErrorType("Invalid Payment Object Found!");
        }
    }

    @Override
    public void updatePayment(Payment currentPayment) {

    }

    @Override
    public void deletePaymentById(Integer id) {
        LOGGER.info("SMAPI - Payment - Service- deletePaymentById method invoked.");

        LOGGER.info("SMAPI - Payment - Service- deletePaymentById method processed.");

        this.paymentRepository.delete(id);

    }

    @Override
    public void deleteAllPayments() {
        LOGGER.info("SMAPI - Payment - Service- deleteAllPayments method invoked.");

        this.paymentRepository.deleteAll();

        LOGGER.info("SMAPI - Payment - Service- deleteAllPayments method processed.");
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
