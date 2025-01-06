package com.example.todoapp.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

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

    public void agregarTarea(Tarea tarea){
        this.tareas.add(tarea);
    }

    public void quitarTarea(Tarea tarea){
        this.tareas.remove(tarea);
    }
}
