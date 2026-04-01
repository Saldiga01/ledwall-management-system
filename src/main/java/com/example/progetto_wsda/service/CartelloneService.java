package com.example.progetto_wsda.service;

import com.example.progetto_wsda.model.Cartellone;
import com.example.progetto_wsda.repository.CartelloneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartelloneService {

    @Autowired
    private CartelloneRepository cartelloneRepository;

    public List<Cartellone> getAllCartelloni() {
        return cartelloneRepository.findAll();
    }

    public Optional<Cartellone> getCartelloneById(Integer id) {
        return cartelloneRepository.findById(id);
    }

    public Cartellone saveCartellone(Cartellone cartellone) {
        return cartelloneRepository.save(cartellone);
    }

    public void deleteCartellone(Integer id) {
        cartelloneRepository.deleteById(id);
    }

}