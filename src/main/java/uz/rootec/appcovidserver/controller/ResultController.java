package uz.rootec.appcovidserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.rootec.appcovidserver.repository.PatientRepository;

import java.util.UUID;

/**
 * Created by Sherlock on 05.09.2021.
 */
@Controller
@RequestMapping("/sertificate.html")
public class ResultController {

    @Autowired
    private PatientRepository patientRepository;

    @RequestMapping("/{id}")
    public String getAll(@RequestParam UUID id, Model model) {
        model.addAttribute("data", patientRepository.getOne(id));
        return "sertificate.html";
    }
}
