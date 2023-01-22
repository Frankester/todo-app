package com.example.todoapp.repositories;

import com.example.todoapp.models.Categoria;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "categorias")
public interface RepoCategorias extends MongoRepository<Categoria, String> {

    Optional<Categoria> findByNombre(String nombre);

}
