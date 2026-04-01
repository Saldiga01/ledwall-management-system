package com.example.progetto_wsda.repository;

import com.example.progetto_wsda.model.Segnalazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegnalazioneRepository  extends JpaRepository<Segnalazione, Integer> {

    List<Segnalazione> findByRefIdcartellone(Integer refIdCartellone);
}
