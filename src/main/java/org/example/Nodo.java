package org.example;

public class Nodo {
    private char caracter;
    private int frecuencia;
    private Nodo izquierda;
    private Nodo derecha;

    Nodo(char caracter, int frecuencia) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
        this.izquierda = null;
        this.derecha = null;
    }

    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }
}