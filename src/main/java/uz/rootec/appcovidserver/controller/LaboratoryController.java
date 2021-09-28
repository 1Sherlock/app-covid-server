package uz.rootec.appcovidserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.rootec.appcovidserver.entity.AnalisePlace;
import uz.rootec.appcovidserver.entity.Laboratory;
import uz.rootec.appcovidserver.payload.ApiResponse;
import uz.rootec.appcovidserver.payload.ReqNameId;
import uz.rootec.appcovidserver.repository.LaboratoryRepository;

import java.util.UUID;

/**
 * Created by Sherlock on 04.09.2021.
 */
@RestController
@RequestMapping("api/laboratory")
public class LaboratoryController {
    @Autowired
    private LaboratoryRepository laboratoryRepository;

    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public HttpEntity<?> save(@RequestBody ReqNameId reqNameId) {
        try {
            Laboratory laboratory;

            if (reqNameId.getId() == null)
                laboratory = new Laboratory();
            else
                laboratory = laboratoryRepository.getOne(reqNameId.getId());

            laboratory.setNameRu(reqNameId.getNameRu());
            laboratory.setNameEn(reqNameId.getNameEn());

            laboratoryRepository.save(laboratory);

            if (reqNameId.getId() == null) {
                return ResponseEntity.ok(new ApiResponse(true, "Сохранено"));
            } else {
                return ResponseEntity.ok(new ApiResponse(true, "Изменено"));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage()));
        }
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok(new ApiResponse(true, "", laboratoryRepository.findAllByOrderByCreatedAtDesc()));
    }

    //    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLaboratory(@PathVariable UUID id) {
        try {
            laboratoryRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse(true, "Удалено"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage()));
        }
    }

}
