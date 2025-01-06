package com.example.todoapp.services;

import com.example.todoapp.exceptions.CategoriaNoExisteException;
import com.example.todoapp.models.Categoria;
import com.example.todoapp.models.Tarea;
import com.example.todoapp.models.dtos.TareaDTO;
import com.example.todoapp.repositories.RepoCategorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    RepoCategorias repoCategorias;


    public Categoria getCategoriaByTarea(Tarea tarea) throws CategoriaNoExisteException {
        if(tarea.getCategoria() == null){

            List<Categoria> categorias = repoCategorias.findAll();

            Optional<Categoria> categoriaActualOp = categorias.stream()
                    .filter(c -> c.getTareas().stream()
                            .anyMatch(t -> t.getId().equals(tarea.getId()))
                    ).findFirst();

            if(categoriaActualOp.isEmpty()){
                throw new CategoriaNoExisteException("la categoria actual no se encontro");
            }

            return categoriaActualOp.get();

        } else {
            return tarea.getCategoria();
        }
    }


    public void updateCategoria(Categoria categoriaReq, Tarea tarea) throws CategoriaNoExisteException {
        categoriaReq.quitarTarea(tarea);

        Categoria categoriaOld = this.getCategoriaByTarea(tarea);
        categoriaOld.quitarTarea(tarea);

        categoriaReq.agregarTarea(tarea);

        repoCategorias.saveAll(List.of(categoriaOld, categoriaReq));
        tarea.setCategoria(categoriaReq);
    }
}
