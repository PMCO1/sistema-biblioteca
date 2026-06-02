package com.ejemplo.sistema_biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.sistema_biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
