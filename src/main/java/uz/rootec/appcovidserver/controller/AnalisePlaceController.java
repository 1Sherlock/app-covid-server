package uz.rootec.appcovidserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.rootec.appcovidserver.entity.AnalisePlace;
import uz.rootec.appcovidserver.payload.ApiResponse;
import uz.rootec.appcovidserver.payload.ReqNameId;
import uz.rootec.appcovidserver.repository.AnalisePlaceRepository;

import java.util.UUID;

/**
 * Created by Sherlock on 04.09.2021.
 */
@RestController
@RequestMapping("api/analise_place")
public class AnalisePlaceController {
    @Autowired
    private AnalisePlaceRepository analisePlaceRepository;

    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public HttpEntity<?> save(@RequestBody ReqNameId reqNameId) {
        try {
            AnalisePlace analisePlace;

            if (reqNameId.getId() == null)
                analisePlace = new AnalisePlace();
            else
                analisePlace = analisePlaceRepository.getOne(reqNameId.getId());

            analisePlace.setNameRu(reqNameId.getNameRu());
            analisePlace.setNameEn(reqNameId.getNameEn());

            analisePlaceRepository.save(analisePlace);

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
        return ResponseEntity.ok(new ApiResponse(true, "", analisePlaceRepository.findAllByOrderByCreatedAtDesc()));
    }

    //    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnalisePlace(@PathVariable UUID id) {
        try {
            analisePlaceRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse(true, "Удалено"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage()));
        }
    }

}
