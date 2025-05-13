package com.patientService.patient_service.exceptions;

public class PatientNotFoundException extends Exception{
    public PatientNotFoundException(String message){
        super(message);
    }
}
