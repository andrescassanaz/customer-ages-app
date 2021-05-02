package com.andrescassanaz.customeragesapp.adapter.controller.validators;

import org.apache.tomcat.jni.Local;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Past;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class BirthdateValidator implements
        ConstraintValidator<BirthdateConstraint, String> {

    final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(BirthdateConstraint birthdate) {
    }

    @Override
    public boolean isValid(String birthdate, ConstraintValidatorContext cxt) {
        return isDateValid(birthdate) && LocalDate.parse(birthdate).isBefore(LocalDate.now());
    }

    private boolean isDateValid(String date) {
        try {
            LocalDate.parse(date, dtf);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

}