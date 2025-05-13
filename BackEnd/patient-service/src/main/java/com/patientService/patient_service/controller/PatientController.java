package com.patientService.patient_service.controller;

import com.patientService.patient_service.exceptions.PatientNotFoundException;
import com.patientService.patient_service.model.Patient;
import com.patientService.patient_service.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "http://localhost:5173")
public class PatientController {

    @Autowired
    PatientService patientService;

    //@PreAuthorize("hasAnyRole('ADMIN','DOCTOR',''RECEPTIONIST)")
    @GetMapping
    public ResponseEntity<List<Patient>> getAll(){
        return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK);
    }

    //@PreAuthorize("hasAnyRole('ADMIN','DOCTOR','RECEPTIONIST')")
    @PostMapping("/addPatient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        return new ResponseEntity<>(patientService.addPatient(patient),HttpStatus.CREATED);
    }

    //@PreAuthorize("hasAnyRole('ADMIN','DOCTOR','RECEPTIONIST')")
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int id) throws PatientNotFoundException {
        Patient patient = patientService.getPatient(id);
        return ResponseEntity.ok(patient);
    }

    //@PreAuthorize("hasAnyRole('ADMIN','DOCTOR','RECEPTIONIST')")
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable int id,@RequestBody Patient patient) throws PatientNotFoundException {
        try{
            Patient updated = patientService.updatePatient(id,patient);
            return ResponseEntity.ok(updated);
        } catch (PatientNotFoundException e) {
            new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable int id) throws PatientNotFoundException{
        try{
            String deleted = patientService.deletePatient(id);
            return ResponseEntity.ok(deleted);
        }
        catch (PatientNotFoundException pe){
            new ResponseEntity<>(pe,HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }





}
