package domain;

import java.util.HashMap;
import java.util.Map;

public class Cidade implements Comparable<Cidade> {
    private final String nome;
    private int distancia = Integer.MAX_VALUE;
    private Cidade anterior = null;
    private final Map<Cidade, Integer> vizinhos = new HashMap<>();

    public Cidade(String nome) {
        this.nome = nome;
    }

    public void addVizinho(Cidade vizinho, int distancia) {
        vizinhos.put(vizinho, distancia);

    }

    public String getNome() {
        return nome;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public Cidade getAnterior() {
        return anterior;
    }

    public void setAnterior(Cidade anterior) {
        this.anterior = anterior;
    }

    public Map<Cidade, Integer> getVizinhos() {
        return vizinhos;
    }

    public int compareTo(Cidade other) {
        if (distancia == other.distancia)
            return nome.compareTo(other.nome);

        return Integer.compare(distancia, other.distancia);
    }
}
