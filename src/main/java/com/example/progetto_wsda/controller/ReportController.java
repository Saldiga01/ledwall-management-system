package com.example.progetto_wsda.controller;

import com.example.progetto_wsda.model.Cartellone;
import com.example.progetto_wsda.model.Segnalazione;
import com.example.progetto_wsda.service.CartelloneService;
import com.example.progetto_wsda.service.SegnalazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ReportController {

    @Autowired
    private CartelloneService cartelloneService;
    @Autowired
    private SegnalazioneService segnalazioneService;

    @GetMapping("/report")
    public String getReport(
            @RequestParam(name="id", required = false) Integer id,
            @RequestParam(name="startDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @RequestParam(name="endDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
            Model model){

        List<Cartellone> cartelloni = cartelloneService.getAllCartelloni();
        model.addAttribute("cartellone", cartelloni);

        if (id != null && startDate != null && endDate != null){

            List<Segnalazione> segnalazioni = segnalazioneService.getSegnalazioniReportistica(id, startDate, endDate);
            Integer impressioni = segnalazioneService.getImpressioniTotali(id, startDate, endDate);
            Integer durata = segnalazioneService.getDurataTotale(id, startDate, endDate);
            Integer numImpianti = segnalazioneService.getNumeroImpianti(id, startDate, endDate);
            Map<Integer, Integer> mappaImpressioni = segnalazioneService.getImpressioniPerImpianto(id, startDate, endDate);

            List<Integer> keys = new ArrayList<>(mappaImpressioni.keySet());
            List<Integer> values = new ArrayList<>(mappaImpressioni.values());

            model.addAttribute("id", id);
            model.addAttribute("segnalazioni", segnalazioni);
            model.addAttribute("impressioni", impressioni);
            model.addAttribute("durataTotale", durata);
            model.addAttribute("numImpianti", numImpianti);
            model.addAttribute("idImpianti", keys);
            model.addAttribute("impressioniImpianti", values);
        }

        return "report";
    }


}
