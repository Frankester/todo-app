package com.example.todoapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "categorias")
public class Categoria {

    @Id
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;

    private String nombre ;
    private String descripcion;

    @DocumentReference(db ="app" ,collection = "tareas")
    private List<Tarea> tareas;

    public Categoria(){
        this.tareas = new ArrayList<>();
    }
}
