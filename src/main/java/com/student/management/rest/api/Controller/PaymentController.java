package com.student.management.rest.api.Controller;

import com.student.management.rest.api.Model.Payment;
import com.student.management.rest.api.Service.PaymentService;
import com.student.management.rest.api.Util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("payment")
public class PaymentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // -------------------Retrieve All Payments--------------------------------------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Payment>> listAllPayments() {
        List<Payment> payments = paymentService.findAllPayments();

        if (payments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    // -------------------Retrieve Single Payment------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPayment(@PathVariable("id") Integer id) {

        Payment payment = paymentService.findById(id);
        if (payment == null) {
            return new ResponseEntity<>(new CustomErrorType("Payment with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Payment>(payment, HttpStatus.OK);
    }

    // -------------------Create a Payment-------------------------------------------
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createPayment(@RequestBody Payment payment, UriComponentsBuilder ucBuilder) {

        if (paymentService.isPaymentExist(payment)) {
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Payment with name " +
                    payment.getId() + " already exist."),HttpStatus.CONFLICT);
        }

        paymentService.addNewPayment(payment);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/studentapi/payment/{id}").buildAndExpand(payment.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Payment ------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePayment(@PathVariable("id") Integer id, @RequestBody Payment payment) {

        Payment currentPayment = paymentService.findById(id);

        if (currentPayment == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Payment with id " +
                    "" + id + " not found."), HttpStatus.NOT_FOUND);
        }

        currentPayment.setAmount(payment.getAmount());
        currentPayment.setCourseId(payment.getCourseId());
        currentPayment.setPaymentDate(payment.getPaymentDate());
        currentPayment.setPaymentStatus(payment.getPaymentStatus());
        currentPayment.setSemester(payment.getSemester());
        currentPayment.setStudentId(payment.getStudentId());

        paymentService.updatePayment(currentPayment);
        return new ResponseEntity<>(currentPayment, HttpStatus.OK);
    }

    // ------------------- Delete a Payment-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePayment(@PathVariable("id") Integer id) {

        Payment payment = paymentService.findById(id);

        if (payment == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. " +
                    "Payment with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        paymentService.deletePaymentById(id);
        return new ResponseEntity<Payment>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Payments-----------------------------
    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity<Payment> deleteAllPayments() {

        paymentService.deleteAllPayments();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
