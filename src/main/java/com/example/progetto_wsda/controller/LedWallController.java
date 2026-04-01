package com.example.progetto_wsda.controller;
import com.example.progetto_wsda.model.Impianto;
import com.example.progetto_wsda.service.ImpiantoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@Controller
public class LedWallController {

    @Autowired
    private ImpiantoService impiantoService;

    @GetMapping("/ledWall")
    public String getImpiantoById(@RequestParam(name="id", required=false) Integer id, Model model) {
        Impianto impianto = impiantoService.getImpiantoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Impianto non trovato con id: " + id));

        model.addAttribute("impianto", impianto);

        return "ledWall";
    }


    @GetMapping("/inattivo")
    public String inattivo(@RequestParam(name = "id", required = false, defaultValue = "") String id, Model model) {
        model.addAttribute("idImpianto", id);
        return "inattivo";
    }
}