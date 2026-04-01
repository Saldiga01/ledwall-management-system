package com.example.progetto_wsda.controller;
import com.example.progetto_wsda.model.Impianto;
import com.example.progetto_wsda.model.Palinsesto;
import com.example.progetto_wsda.service.ImpiantoService;
import com.example.progetto_wsda.service.PalinsestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

@Controller
public class GestioneImpiantiController {

    @Autowired
    private PalinsestoService palinsestoService;
    @Autowired
    private ImpiantoService impiantoService;

    @GetMapping("/gestione")
    public String viewImpianti(Model model) {
        List<Impianto> impianti = impiantoService.getAllImpianti();
        List<Palinsesto> palinsesti = palinsestoService.getAllPalinsesti();

        // Estraggo il nome del Palinsesto dal path perchè mi serve nella pagina di Gestione per mostrarlo
        for (Palinsesto palinsesto : palinsesti) {
            String nomePalinsesto = StringUtils.substringBeforeLast(StringUtils.substringAfterLast(palinsesto.getPathPalinsesto(), "/"), ".");
            palinsesto.setNomePalinsesto(nomePalinsesto);
        }

        model.addAttribute("impianti", impianti);
        model.addAttribute("palinsesti", palinsesti);

        model.addAttribute("title", "Gestione Impianti");
        return "gestioneImpianti";
    }

    @PostMapping("/updateImpianto")
    public ResponseEntity<String> updateImpianto(@RequestParam Integer id,
                                                 @RequestParam Integer stato,
                                                 @RequestParam Integer palinsestoId,
                                                 @RequestParam String descrizione ) {
        Impianto impianto = impiantoService.getImpiantoById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid impianto Id:" + id));
        Palinsesto palinsesto = palinsestoService.getPalinsestoById(palinsestoId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid palinsesto Id:" + palinsestoId));

        System.out.println("Stato ricevuto: " + stato);
        System.out.println("Stato attuale dell'impianto: " + impianto.getStato());
        System.out.println("ID del palinsesto ricevuto: " + palinsesto.getPathPalinsesto());
        System.out.println("ID del palinsesto attuale dell'impianto: " + impianto.getPathPalinsesto());
        System.out.println("Stato ricevuto: " + descrizione);
        System.out.println("Stato ricevuto: " + impianto.getDescrizione());


        // Controllo se i dati inviati sono identici ai dati attuali dell'impianto
        if (impianto.getStato().equals(stato) && palinsesto.getPathPalinsesto().equals(impianto.getPathPalinsesto()) && impianto.getDescrizione().equals(descrizione)) {
            return ResponseEntity.ok("DATI_PRECEDENTI");
        }

        impianto.setStato(stato);
        impianto.setDescrizione(descrizione);
        impianto.setPathPalinsesto(palinsesto.getPathPalinsesto());
        impiantoService.saveImpianto(impianto);

        // Restituisci una risposta con successo
        return ResponseEntity.ok("SUCCESSO");
    }

    @PostMapping("/addImpianto")
    public ResponseEntity<String> addImpianto(@RequestParam String descrizione,
                                              @RequestParam Double latitudine,
                                              @RequestParam Double longitudine,
                                              @RequestParam Integer palinsestoId,
                                              @RequestParam Integer stato) {


        Palinsesto palinsesto = palinsestoService.getPalinsestoById(palinsestoId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid palinsesto Id:" + palinsestoId));

        Impianto impianto = new Impianto();
        impianto.setDescrizione(descrizione);
        impianto.setStato(stato);
        impianto.setLatitudine(latitudine);
        impianto.setLongitudine(longitudine);
        impianto.setPathPalinsesto(palinsesto.getPathPalinsesto());
        impiantoService.saveImpianto(impianto);

        return ResponseEntity.ok("SUCCESSO");
    }

    @PostMapping("/deleteImpianto")
    public ResponseEntity<String> deleteImpianto(@RequestParam Integer idImpianto) {
        Impianto impianto = impiantoService.getImpiantoById(idImpianto)
                .orElseThrow(() -> new IllegalArgumentException("Invalid impianto Id:" + idImpianto));

        Integer id= impianto.getId();
        impiantoService.deleteImpianto(id);

        return ResponseEntity.ok("SUCCESSO");
    }
}
