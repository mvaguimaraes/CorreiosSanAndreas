package model;

import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Grafo {

    private final Map<String, Vertice> grafo;

    /**
     * Constrói um grafo a partir de um conjunto de arestas
     */
    public Grafo(Map<String, Vertice> grafo) {
        this.grafo = grafo;
    }

    public Map<String, Vertice> getGrafo() {
        return grafo;
    }

    /**
     * Implementação do Algoritmo de Dijkstra
     */
    public void dijkstra(String nomeOrigem) {
        //Verificando se o grafo contém o vértice de origem
        if (!grafo.containsKey(nomeOrigem)) {
            System.out.println(String.format("Grafo não contém o vértice de origem: %s", nomeOrigem));
            return;
        }
        final Vertice origem = grafo.get(nomeOrigem);
        NavigableSet<Vertice> q = new TreeSet<>();

        // Inicializando os vértices
        for (Vertice v : grafo.values()) {
            v.setAnterior(v == origem ? origem : null);
            v.setDistancia(v == origem ? 0 : Integer.MAX_VALUE);
            q.add(v);
        }

        Vertice u, v;
        while (!q.isEmpty()) {

            // Vértice com a menor distância
            u = q.pollFirst();

            if (u.getDistancia() == Integer.MAX_VALUE) {
                break;
            }

            // Verificando as distâncias entre cada vizinho
            for (Map.Entry<Vertice, Integer> a : u.getVizinhos().entrySet()) {

                // O vizinho dessa iteração
                v = a.getKey();

                final int distAlternativa = u.getDistancia() + a.getValue();
                if (distAlternativa < v.getDistancia()) {
                    // Caminho menor encontrado
                    q.remove(v);
                    v.setDistancia(distAlternativa);
                    v.setAnterior(u);
                    q.add(v);
                }
            }
        }
    }
}
