package com.example.progetto_wsda.controller;

import com.example.progetto_wsda.model.Segnalazione;
import com.example.progetto_wsda.service.SegnalazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class SegnalazioneController {

    @Autowired
    private SegnalazioneService segnalazioneService;

    public static class SegnalazioneRequest {
        public Integer idImpianto;
        public Integer idPalinsesto;
        public Integer idCartellone;
        public Integer durata;
    }

    @PostMapping("/segnalazioni")
    public ResponseEntity<String> salvaSegnalazione(@RequestBody SegnalazioneRequest request) {
        Segnalazione s = new Segnalazione();
        s.setRefIdimpianto(request.idImpianto);
        s.setRefIdpalinsesto(request.idPalinsesto);
        s.setRefIdcartellone(request.idCartellone);
        s.setDurata(request.durata);
        s.setTimestamps(new Timestamp(System.currentTimeMillis()));

        segnalazioneService.save(s);

        return ResponseEntity.ok("Segnalazione salvata con successo");
    }
}
