package com.example.progetto_wsda.repository;

import com.example.progetto_wsda.model.Cartellone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartelloneRepository extends JpaRepository<Cartellone, Integer> {

}