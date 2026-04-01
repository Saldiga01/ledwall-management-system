package com.example.progetto_wsda.service;

import com.example.progetto_wsda.model.Palinsesto;
import com.example.progetto_wsda.repository.PalinsestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PalinsestoService {

    @Autowired
    private PalinsestoRepository palinsestoRepository;

    public Optional<Palinsesto> getPalinsestoById(Integer id) {
        return palinsestoRepository.findById(id);
    }

    public List<Palinsesto> getAllPalinsesti() {
        return palinsestoRepository.findAll();
    }
}
