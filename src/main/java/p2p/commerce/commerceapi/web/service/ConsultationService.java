package p2p.commerce.commerceapi.web.service;

import p2p.commerce.commerceapi.web.model.Consultations;

import java.util.List;

public interface ConsultationService {
    Consultations postConsultation();
    Consultations findById(int id);
    List<Consultations> findAllConsultation();
    Consultations findAllConsultationActive();
}
