package com.doctorService.doctor_service.controller;

import com.doctorService.doctor_service.exceptions.DoctorNotFoundException;
import com.doctorService.doctor_service.model.Doctor;
import com.doctorService.doctor_service.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "http://localhost:5173")
public class DoctorContoller {
    @Autowired
    DoctorService doctorService;

    //@PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    //@PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable int id) throws DoctorNotFoundException {
        return ResponseEntity.ok(doctorService.getDoctor(id));
    }

    //@PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @PostMapping("/addDoctor")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor){
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }

    //@PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable int id, @RequestBody Doctor doctor) throws DoctorNotFoundException{
        return ResponseEntity.ok(doctorService.updateDoctor(id,doctor));
    }

    //@PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable int id) throws DoctorNotFoundException{
        return ResponseEntity.ok(doctorService.deleteDoctor(id));
    }


}
