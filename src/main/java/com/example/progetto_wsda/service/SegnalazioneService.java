package com.example.progetto_wsda.service;

import com.example.progetto_wsda.model.Segnalazione;
import com.example.progetto_wsda.repository.SegnalazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class SegnalazioneService {

    @Autowired
    private SegnalazioneRepository segnalazioneRepository;

    public List<Segnalazione> getAllSegnalazioni() {
        return segnalazioneRepository.findAll();
    }

    public Optional<Segnalazione> getSegnalazioneById(Integer id) {
        return segnalazioneRepository.findById(id);
    }

    public List<Segnalazione> getSegnalazioniByRefIdCartellone(Integer refIdcartellone) {
        return segnalazioneRepository.findByRefIdcartellone(refIdcartellone);
    }


    public List<Segnalazione> getSegnalazioniReportistica(Integer refIdcartellone, Date startDate, Date endDate) {
        List<Segnalazione> segnalazioni = segnalazioneRepository.findByRefIdcartellone(refIdcartellone); //ottengo tutte le segnalazioni
        return segnalazioni.stream()    //filtro le segnalazioni tramite i valori di data, dopodichè li rendo nuovamente una lista
                .filter(segnalazione -> segnalazione.getTimestamps().after(startDate) && segnalazione.getTimestamps().before(endDate))
                .collect(Collectors.toList());
    }

    public Integer getImpressioniTotali(Integer refIdcartellone, Date startDate, Date endDate){
        List<Segnalazione> segnalazioni = segnalazioneRepository.findByRefIdcartellone(refIdcartellone);
        return segnalazioni.size();
    }

    public Integer getDurataTotale(Integer refIdcartellone, Date startDate, Date endDate){
        List<Segnalazione> segnalazioni = this.getSegnalazioniReportistica(refIdcartellone, startDate, endDate);

        Integer durata = 0;
        for (Segnalazione segnalazione : segnalazioni) {
            durata += segnalazione.getDurata();
        }
        return durata;
    }

    public Integer getNumeroImpianti(Integer refIdcartellone , Date startDate, Date endDate){
        List<Segnalazione> segnalazioni = this.getSegnalazioniReportistica(refIdcartellone, startDate, endDate);

        Set<Integer> set = new HashSet<>();

        for (Segnalazione segnalazione : segnalazioni) {
            Integer impianto = segnalazione.getRefIdimpianto();
            set.add(impianto);
        }
        return set.size();
    }

    public HashMap<Integer, Integer> getImpressioniPerImpianto(Integer refIdcartellone , Date startDate, Date endDate){
        List<Segnalazione> segnalazioni = this.getSegnalazioniReportistica(refIdcartellone, startDate, endDate);

        HashMap<Integer, Integer> map = new HashMap<>();
        for (Segnalazione segnalazione : segnalazioni) {
            Integer idImpianto = segnalazione.getRefIdimpianto();
            map.merge(idImpianto, 1, Integer::sum);
        }
        return map;
    }

}
