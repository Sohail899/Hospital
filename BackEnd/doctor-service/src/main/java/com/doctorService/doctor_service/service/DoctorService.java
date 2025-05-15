package com.doctorService.doctor_service.service;

import com.doctorService.doctor_service.exceptions.DoctorNotFoundException;
import com.doctorService.doctor_service.model.Doctor;
import com.doctorService.doctor_service.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor getDoctor(int id) throws DoctorNotFoundException {
        return doctorRepository.findById(id).orElseThrow(()-> new DoctorNotFoundException("Doctor not found"));
    }

    public Doctor addDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(int id,Doctor update) throws DoctorNotFoundException{
        Optional<Doctor> tempDoc = doctorRepository.findById(id);
        if(tempDoc.isPresent()){
            Doctor updatedDoc = tempDoc.get();
            updatedDoc.setDoctorFirstName(update.getDoctorFirstName());
            updatedDoc.setDoctorMiddleName(update.getDoctorMiddleName());
            updatedDoc.setDoctorLastName(update.getDoctorLastName());

            updatedDoc.setDoctorSpecialization(update.getDoctorSpecialization());
            updatedDoc.setDoctorEmail(update.getDoctorEmail());
            updatedDoc.setPhone(update.getPhone());
            return doctorRepository.save(updatedDoc);
        }
        throw new DoctorNotFoundException("Doctor Not found");
    }

    public String deleteDoctor(int id) throws DoctorNotFoundException{
        Optional<Doctor> doc = doctorRepository.findById(id);
        if(doc.isPresent()){
            Doctor deletedDoc = doc.get();
            doctorRepository.delete(deletedDoc);
            return "Doctor Deleted";
        }
        throw new DoctorNotFoundException("Doctor Not Found");
    }
}
