package com.example.todoapp.models.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class FechaHora {

    private LocalDate fecha;
    private LocalTime hora;
}
