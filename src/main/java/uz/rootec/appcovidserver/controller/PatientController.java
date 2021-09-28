package uz.rootec.appcovidserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.rootec.appcovidserver.entity.Patient;
import uz.rootec.appcovidserver.payload.ApiResponse;
import uz.rootec.appcovidserver.payload.ReqPatient;
import uz.rootec.appcovidserver.repository.AnalisePlaceRepository;
import uz.rootec.appcovidserver.repository.LaboratoryRepository;
import uz.rootec.appcovidserver.repository.PatientRepository;

import java.util.UUID;

/**
 * Created by Sherlock on 04.09.2021.
 */
@RestController
@RequestMapping("api/patient")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Autowired
    private AnalisePlaceRepository analisePlaceRepository;

    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public HttpEntity<?> save(@RequestBody ReqPatient reqPatient) {
        try {
            Patient patient;

            if (reqPatient.getId() == null)
                patient = new Patient();
            else
                patient = patientRepository.getOne(reqPatient.getId());

            patient.setAddress(reqPatient.getAddress());
            patient.setAnaliseDate(reqPatient.getAnaliseDate());
            patient.setBirthDate(reqPatient.getBirthDate());
            patient.setGender(reqPatient.getGender());
            patient.setLaboratory(laboratoryRepository.getOne(reqPatient.getLaboratory()));
            patient.setFullName(reqPatient.getFullName());
            patient.setMethodEn(reqPatient.getMethodEn());
            patient.setMethodRu(reqPatient.getMethodRu());
            patient.setPlace(analisePlaceRepository.getOne(reqPatient.getPlace()));
            patient.setSerial(reqPatient.getSerial());
            patient.setPassportNumber(reqPatient.getPassportNumber());
            patient.setStatus(reqPatient.getStatus());

            patientRepository.save(patient);



            if (reqPatient.getId() == null) {
                return ResponseEntity.ok(new ApiResponse(true, "Сохранено"));
            } else {
                return ResponseEntity.ok(new ApiResponse(true, "Изменено"));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage()));
        }
    }

    @GetMapping
    public HttpEntity<?> getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(new ApiResponse(true, "", patientRepository.findAllByFullNameContainsOrderByCreatedAtDesc(search, PageRequest.of(page, size))));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(new ApiResponse(true, "", patientRepository.getOne(id)));
    }

    //    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable UUID id) {
        try {
            patientRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse(true, "Удалено"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage()));
        }
    }
}
