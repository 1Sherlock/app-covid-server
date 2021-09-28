package uz.rootec.appcovidserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.rootec.appcovidserver.entity.Attachment;
import uz.rootec.appcovidserver.repository.AttachmentContentRepository;
import uz.rootec.appcovidserver.repository.AttachmentRepository;
import uz.rootec.appcovidserver.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Sherlock on 15.04.2020.
 */
@RestController
@RequestMapping("api/file")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @PostMapping("/save")
    public Attachment save(MultipartHttpServletRequest request) {
        return attachmentService.saveFile(request);
    }

    @GetMapping("/get/{id}")
    public void getFile(HttpServletResponse response, @PathVariable String id) {
        attachmentService.getFile(response, id);
    }
}
