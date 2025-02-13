package org.example;

import java.util.Comparator;

class ComparadorNodo implements Comparator<Nodo> {
    public int compare(Nodo n1, Nodo n2) {
        return n1.getFrecuencia() - n2.getFrecuencia();
    }
}