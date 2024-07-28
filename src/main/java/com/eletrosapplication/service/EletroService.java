package com.eletrosapplication.service;

import org.springframework.stereotype.Service;
import com.eletrosapplication.domain.Eletro;
import com.eletrosapplication.repository.EletroRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EletroService {

    private final EletroRepository repository;

    public EletroService(EletroRepository repository) {
        this.repository = repository;
    }

    public Optional<Eletro> findById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        Optional<Eletro> eletro = repository.findById(id);
        eletro.ifPresent(e -> {
            e.setIsDeleted(LocalDate.now());
            repository.save(e);
        });
    }

    public Eletro update(Eletro eletro) {
        return repository.saveAndFlush(eletro);
    }

    public Eletro create(Eletro eletro) {
        return repository.save(eletro);
    }

    public List<Eletro> findAll() {
        return repository.findByIsDeletedNull();
    }
}
