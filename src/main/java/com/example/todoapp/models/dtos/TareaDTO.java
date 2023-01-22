package com.example.todoapp.models.dtos;

import com.example.todoapp.models.Etapa;
import com.example.todoapp.models.Prioridad;
import com.example.todoapp.models.utils.FechaHora;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class TareaDTO {

    private String nombre;

    private String descripcion;

    private Prioridad prioridad;

    private FechaHora tiempoFinalizacionEstimado;

    private FechaHora tiempoInicio;

    private Etapa etapaActual;

    private CategoriaDTO categoria;

    public TareaDTO(){
        this.tiempoInicio = new FechaHora(LocalDate.now(), LocalTime.now());
        this.etapaActual = Etapa.PENDIENTE;
    }
}
