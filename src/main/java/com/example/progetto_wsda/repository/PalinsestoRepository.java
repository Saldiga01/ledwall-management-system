package com.example.progetto_wsda.repository;

import com.example.progetto_wsda.model.Palinsesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalinsestoRepository extends JpaRepository<Palinsesto, Integer> {
}