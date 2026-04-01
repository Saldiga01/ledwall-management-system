package com.example.progetto_wsda.service;

import com.example.progetto_wsda.model.Impianto;
import com.example.progetto_wsda.repository.ImpiantoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ImpiantoService {

    @Autowired //L'annotazione @Autowired in Spring Framework viene utilizzata per l'iniezione automatica delle dipendenze.
    private ImpiantoRepository impiantoRepository;

    public List<Impianto> getAllImpianti() {
        return impiantoRepository.findAll();
    }

    public Optional<Impianto> getImpiantoById(Integer id) {
        return impiantoRepository.findById(id);
    }

    public Impianto saveImpianto(Impianto impianto) {
        return impiantoRepository.save(impianto);
    }

    public void deleteImpianto(Integer id) {
        impiantoRepository.deleteById(id);
    }


}