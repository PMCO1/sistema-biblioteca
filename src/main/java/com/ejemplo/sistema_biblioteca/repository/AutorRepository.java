package com.ejemplo.sistema_biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.sistema_biblioteca.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
