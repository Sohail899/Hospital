package com.doctorService.doctor_service.exceptions;

public class DoctorNotFoundException extends Exception{
    public DoctorNotFoundException(String message){
        super(message);
    }
}
