package com.patientService.patient_service.service;

import com.patientService.patient_service.exceptions.PatientNotFoundException;
import com.patientService.patient_service.model.Patient;
import com.patientService.patient_service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public Patient getPatient (int id) throws PatientNotFoundException{
        return patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient Not Found"));
    }

    public Patient addPatient(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient updatePatient(int id,Patient updated) throws PatientNotFoundException{
        Optional<Patient> tempPatient = patientRepository.findById(id);
        if(tempPatient.isPresent()){
            Patient p = tempPatient.get();
            p.setPatientName(updated.getPatientName());
            p.setPatientAge(updated.getPatientAge());
            p.setPatientAddress(updated.getPatientAddress());
            return patientRepository.save(p);
        }
        throw new PatientNotFoundException("Patient Not Found");

    }

    public String deletePatient(int id) throws PatientNotFoundException{
        Optional<Patient> tempPatient = patientRepository.findById(id);
        if(tempPatient.isPresent()){
            Patient p = tempPatient.get();
            patientRepository.deleteById(id);
            return "Patient deleted with Id :" + id;
        }
        throw new PatientNotFoundException("Patient Not Found");
    }
}
