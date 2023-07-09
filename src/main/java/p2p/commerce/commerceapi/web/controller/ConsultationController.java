package p2p.commerce.commerceapi.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.ChatRequest;
import p2p.commerce.commerceapi.web.model.ChatMessages;
import p2p.commerce.commerceapi.web.model.Consultations;
import p2p.commerce.commerceapi.web.service.ConsultationService;

import java.util.List;

@RestController
@RequestMapping("/api/consultation")
@AllArgsConstructor
public class ConsultationController {
    private ConsultationService consultationService;

    @PreAuthorize("hasAuthority('Client')")
    @PostMapping
    public CommonResponse<Consultations> createConsultation() {
        return ResponseHelper.ok(consultationService.postConsultation());
    }

    @GetMapping("/{id}")
    public CommonResponse<Consultations> detailConsultation(@PathVariable("id") int id) {
        return ResponseHelper.ok(consultationService.findById(id));
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping
    public CommonResponse<List<Consultations>> findAllConsultation() {
        return ResponseHelper.ok(consultationService.findAllConsultation());
    }

    @PreAuthorize("hasAuthority('Client')")
    @GetMapping("/active")
    public CommonResponse<Consultations> findAllConsultationActive() {
        return ResponseHelper.ok(consultationService.findAllConsultationActive());
    }
}
