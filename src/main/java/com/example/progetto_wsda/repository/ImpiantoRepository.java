package com.example.progetto_wsda.repository;


import com.example.progetto_wsda.model.Impianto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpiantoRepository extends JpaRepository<Impianto, Integer> {

}
