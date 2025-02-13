package org.example;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {
    public static void construirArbolHuffman(String texto) {
        LinkedHashMap<Character, Integer> frecuencias = new LinkedHashMap<>()   ;
        for (char c : texto.toCharArray()) {
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);

        }
        PriorityQueue<Nodo> cola = new PriorityQueue<>(new ComparadorNodo());
        for (Map.Entry<Character, Integer> entrada : frecuencias.entrySet()) {
            cola.add(new Nodo(entrada.getKey(), entrada.getValue()));
        }
        while (cola.size() > 1) {
            Nodo izquierda = cola.poll();
            Nodo derecha = cola.poll();

            Nodo nuevoNodo = new Nodo('-', izquierda.getFrecuencia() + derecha.getFrecuencia());
            nuevoNodo.setIzquierda(izquierda);
            nuevoNodo.setDerecha(derecha);

            cola.add(nuevoNodo);
        }
        Nodo raiz = cola.poll();
        Map<Character, String> mapaCodigos = new HashMap<>();
        imprimirCodigos(raiz, "", mapaCodigos);
        System.out.println("Códigos de Huffman:");
        for (Map.Entry<Character, String> entrada : mapaCodigos.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue());
        }
        StringBuilder textoCodificado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            textoCodificado.append(mapaCodigos.get(c));
        }

        System.out.println("Texto original: " + texto);
        System.out.println("Texto codificado: " + textoCodificado);

        calcularEntropia(texto);
        calcularLongitudMedia(mapaCodigos, frecuencias, texto.length());
    }
    public static void imprimirCodigos(Nodo raiz, String codigo, Map<Character, String> mapaCodigos) {
        if (raiz == null) {
            return;
        }

        if (raiz.getIzquierda() == null && raiz.getDerecha() == null) {
            mapaCodigos.put(raiz.getCaracter(), codigo);
        }
        imprimirCodigos(raiz.getIzquierda(), codigo + "0", mapaCodigos);
        imprimirCodigos(raiz.getDerecha(), codigo + "1", mapaCodigos);
    }



    public static void calcularEntropia(String texto) {
        Map<Character, Integer> frecuencias = new HashMap<>();
        for (char c : texto.toCharArray()) {
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }

        int totalCaracteres = texto.length();
        double entropia = 0.0;

        for (Map.Entry<Character, Integer> entrada : frecuencias.entrySet()) {
            double probabilidad = (double) entrada.getValue() / totalCaracteres;
            entropia += probabilidad * (Math.log(probabilidad) / Math.log(2));
        }

        entropia *= -1;
        System.out.println("Entropía de la fuente: " + entropia + " bits/caracter");
    }

    public static void calcularLongitudMedia(Map<Character, String> mapaCodigos, Map<Character, Integer> frecuencias, int totalCaracteres) {
        double longitudMedia = 0.0;
        for (Map.Entry<Character, String> entrada : mapaCodigos.entrySet()) {
            char caracter = entrada.getKey();
            String codigo = entrada.getValue();
            double probabilidad = (double) frecuencias.get(caracter) / totalCaracteres;
            longitudMedia += probabilidad * codigo.length();
        }

        System.out.println("Longitud media del código de Huffman: " + longitudMedia + " bits/caracter");
        System.out.println(""); //intervalo
    }
}