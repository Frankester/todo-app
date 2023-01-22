package com.example.todoapp.models;

import com.example.todoapp.models.utils.FechaHora;
import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Document(collection = "tareas")
public class Tarea {

    @Id
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;

    private String nombre;

    private String descripcion;

    @DocumentReference(collection = "categorias", db = "app")
    @Field(value = "categoriaId", targetType = FieldType.OBJECT_ID)
    private Categoria categoria;

    private Prioridad prioridad;

    private FechaHora tiempoFinalizacionEstimado;

    private FechaHora tiempoInicio;

    private Etapa etapaActual;


    public Tarea(){
        this.etapaActual = Etapa.PENDIENTE;
        this.tiempoInicio = new FechaHora(LocalDate.now(), LocalTime.now());
    }

    public Tarea(String nombre, String descripcion,  Categoria categoria, Prioridad prioridad, FechaHora tiempoFinalizacionEstimado, FechaHora tiempoInicio, Etapa etapaActual) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.prioridad = prioridad;
        this.tiempoInicio = tiempoInicio;
        this.tiempoFinalizacionEstimado = tiempoFinalizacionEstimado;
        this.etapaActual = etapaActual;
        this.descripcion = descripcion;
    }
}
