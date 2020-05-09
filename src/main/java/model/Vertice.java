package model;

import java.util.HashMap;
import java.util.Map;

public class Vertice implements Comparable<Vertice> {
    private final String nome;
    private int distancia = Integer.MAX_VALUE;
    private Vertice anterior = null;
    private final Map<Vertice, Integer> vizinhos = new HashMap<>();

    public Vertice(String nome) {
        this.nome = nome;
    }

    public void addVizinho(Vertice vizinho, int distancia) {
        vizinhos.put(vizinho, distancia);

    }

    public String getNome() {
        return nome;
    }

    public int getDistancia() {
        return distancia;
    }

    void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public Vertice getAnterior() {
        return anterior;
    }

    void setAnterior(Vertice anterior) {
        this.anterior = anterior;
    }

    public Map<Vertice, Integer> getVizinhos() {
        return vizinhos;
    }

    public int compareTo(Vertice other) {
        if (distancia == other.distancia)
            return nome.compareTo(other.nome);

        return Integer.compare(distancia, other.distancia);
    }
}
