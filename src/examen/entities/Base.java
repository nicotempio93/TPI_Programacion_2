/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.entities;

import java.time.LocalDateTime;

/**
 *
 * @author nicoyshansho
 */
public abstract class Base {

    private static long contadorId = 1;

    private Long id;
    private boolean eliminado;
    private LocalDateTime createdAt;

    public Base() {
        this.id = contadorId++; //Aca mejore el contador, haciendo un contador automatico, eliminado la posibilidad de errores de repeticion por parametro. Es un contador global, pero tambien podriamos hacer uno en cada clase.
        this.eliminado = false;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public abstract String toString();

}
