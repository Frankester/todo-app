package com.example.todoapp.controllers;

import com.example.todoapp.exceptions.CategoriaNoExisteException;
import com.example.todoapp.models.Categoria;
import com.example.todoapp.models.Tarea;
import com.example.todoapp.models.dtos.CategoriaDTO;
import com.example.todoapp.models.dtos.TareaDTO;
import com.example.todoapp.repositories.RepoCategorias;
import com.example.todoapp.repositories.RepoTareas;
import com.example.todoapp.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;
import java.util.Optional;

@RepositoryRestController
public class TareaControllerComplement {

    @Autowired
    RepoTareas repoTareas;

    @Autowired
    RepoCategorias repoCategorias;

    @Autowired
    CategoriaService service;

    @PostMapping(path = {"/tareas", "/tareas/"})
    public ResponseEntity<Object> saveTarea(@RequestBody TareaDTO tareaReq) {

        CategoriaDTO categoria = tareaReq.getCategoria();

       Optional<Categoria> categoriaOp =  repoCategorias.findByNombre(categoria.getNombre());

        if(categoriaOp.isEmpty()){
            return ResponseEntity.badRequest().body("la categoria " + categoria.getNombre() + " no existe");
        }

        Categoria categoriaGuardada = categoriaOp.get();

        Tarea tarea = new Tarea(
                tareaReq.getNombre(),
                tareaReq.getDescripcion(),
                categoriaGuardada,
                tareaReq.getPrioridad(),
                tareaReq.getTiempoFinalizacionEstimado(),
                tareaReq.getTiempoInicio(),
                tareaReq.getEtapaActual()
        );

        repoTareas.save(tarea);


        repoCategorias.save(categoriaGuardada);

        return ResponseEntity.ok(tareaReq);
    }

    @PutMapping("/tareas/{idTareas}")
    public ResponseEntity<Object> updateTareas(
            @RequestBody TareaDTO tareaReq,
            @PathVariable("idTareas") String IdTarea
    ) throws CategoriaNoExisteException {

        Optional<Tarea> tareaOp = repoTareas.findById(IdTarea);

        if(tareaOp.isEmpty()){
            return ResponseEntity.badRequest().body("Esa tarea no existe");
        }

        Tarea tarea = tareaOp.get();

        Optional<Categoria> categoriaOp = repoCategorias.findByNombre(tareaReq.getCategoria().getNombre());

        if(categoriaOp.isPresent()){
            Categoria categoriaReq = categoriaOp.get();

            Categoria categoriaActual = service.getCategoriaByTarea(tarea);

            if(!Objects.equals(categoriaActual.getNombre(), categoriaReq.getNombre())){
                service.updateCategoria(categoriaReq,tarea);
            }

            tarea.setNombre(tareaReq.getNombre());
            tarea.setDescripcion(tareaReq.getDescripcion());
            tarea.setEtapaActual(tareaReq.getEtapaActual());
            tarea.setTiempoFinalizacionEstimado(tareaReq.getTiempoFinalizacionEstimado());
            tarea.setTiempoInicio(tareaReq.getTiempoInicio());
            tarea.setPrioridad(tareaReq.getPrioridad());

            repoTareas.save(tarea);

            return ResponseEntity.ok(tareaReq);
        }

        return ResponseEntity.badRequest().body("La tarea debe tener estar asignada a una categoria");
    }
}
