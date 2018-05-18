package com.student.management.rest.api.Controller;

import com.student.management.rest.api.Model.Payment;
import com.student.management.rest.api.Service.DepartmentService;
import com.student.management.rest.api.Service.PaymentService;
import com.student.management.rest.api.Service.RegistrationService;
import com.student.management.rest.api.Service.StudentService;
import com.student.management.rest.api.Util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("payment")
public class PaymentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;
    private final DepartmentService departmentService;
    private final StudentService studentService;
    private final RegistrationService registrationService;

    @Autowired
    public PaymentController(PaymentService paymentService, DepartmentService departmentService,
                             StudentService studentService, RegistrationService registrationService) {
        this.paymentService = paymentService;
        this.departmentService = departmentService;
        this.studentService = studentService;
        this.registrationService = registrationService;
    }

    // -------------------Retrieve All Payments--------------------------------------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Payment>> listAllPayments() {
        LOGGER.info("SMAPI - Payment - Controller- listAllPayments request received.");

        List<Payment> payments = paymentService.findAllPayments();

        if (payments.isEmpty()) {
            LOGGER.info("SMAPI - Payment - Controller- listAllPayments request processed.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LOGGER.info("SMAPI - Payment - Controller- listAllPayments request processed.");
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    // -------------------Retrieve Single Payment------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPayment(@PathVariable("id") Integer id) {
        LOGGER.info("SMAPI - Payment - Controller- getPayment request received.");

        Payment payment;

        try {
            payment = paymentService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Payment - Controller- getPayment request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        LOGGER.info("SMAPI - Payment - Controller- getPayment request processed.");
        return new ResponseEntity<Payment>(payment, HttpStatus.OK);
    }

    // -------------------Create a Payment-------------------------------------------
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createPayment(@RequestBody Payment payment, UriComponentsBuilder ucBuilder) {
        LOGGER.info("SMAPI - Payment - Controller- createPayment request received. data id : {} ", payment.getId());

        payment.setPaymentDate(new Date());

        try {
            paymentService.addNewPayment(payment);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Payment - Controller- createPayment request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/studentapi/payment/{id}").buildAndExpand(payment.getId()).toUri());

        LOGGER.info("SMAPI - Payment - Controller- createPayment request processed.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Payment ------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePayment(@PathVariable("id") Integer id, @RequestBody Payment payment) {
        LOGGER.info("SMAPI - Payment - Controller- updatePayment request received.");

        Payment currentPayment;

        try {
            currentPayment = paymentService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Payment - Controller- updatePayment request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        currentPayment.setAmount(payment.getAmount());
        currentPayment.setCourseId(payment.getCourseId());
        currentPayment.setPaymentDate(new Date());
        currentPayment.setPaymentStatus(payment.getPaymentStatus());
        currentPayment.setSemester(payment.getSemester());
        currentPayment.setStudentId(payment.getStudentId());

        paymentService.updatePayment(currentPayment);

        LOGGER.info("SMAPI - Payment - Controller- updatePayment request processed.");
        return new ResponseEntity<>(currentPayment, HttpStatus.OK);
    }

    // ------------------- Delete a Payment-----------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePayment(@PathVariable("id") Integer id) {
        LOGGER.info("SMAPI - Payment - Controller- deletePayment request received.");

        try {
           paymentService.findById(id);
        } catch (CustomErrorType customError) {
            LOGGER.info("SMAPI - Payment - Controller- deletePayment request processed.");
            return new ResponseEntity<>(customError.getErrorMessage(), HttpStatus.NOT_FOUND);
        }

        paymentService.deletePaymentById(id);

        LOGGER.info("SMAPI - Payment - Controller- deletePayment request processed.");
        return new ResponseEntity<Payment>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Payments-----------------------------
    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity<Payment> deleteAllPayments() {
        LOGGER.info("SMAPI - Payment - Controller- deletePayment request received.");

        paymentService.deleteAllPayments();

        LOGGER.info("SMAPI - Payment - Controller- deletePayment request processed.");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // -------Retrieve All Registered Student who made payments-----------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Payment>> listAllRegisteredStudentsPayments() {
        LOGGER.info("SMAPI - Payment - Controller- listAllRegisteredStudentsPayments request received.");

        List<Payment> payments = paymentService.findAllPayments();

        if (payments.isEmpty()) {
            LOGGER.info("SMAPI - Payment - Controller- listAllRegisteredStudentsPayments request processed.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            departmentService.findAllDepartments();
            studentService.findAllStudents();
            registrationService.findAllRegistrations();
        }

        LOGGER.info("SMAPI - Payment - Controller- listAllRegisteredStudentsPayments request processed.");
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
}
