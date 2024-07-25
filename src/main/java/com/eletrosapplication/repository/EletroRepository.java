package com.eletrosapplication.repository;

import com.eletrosapplication.domain.Eletro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EletroRepository extends JpaRepository<Eletro, String> {
    List<Eletro> findByIsDeletedNull();
}