package model;

import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GrafoTest {

    @Test
    public void testDijkstraComDadosValidosDeveRetornarUmMenorCaminhoValido() {
        final String ORIGEM = "WS";
        final String DESTINO = "BC";
        final List<String> caminho = new ArrayList<>(Arrays.asList("BC", "LV", "SF", "WS"));
        final Grafo grafo = new Grafo(gerarMapaDeVertices());
        grafo.dijkstra(ORIGEM);

        assertThat(5, is(equalTo(grafo.getGrafo().get(DESTINO).getDistancia())));
        for (int i = 0; i < caminho.size(); i++) {
            if (i == caminho.size()-1) {
                assertThat(grafo.getGrafo().get(caminho.get(i)), is(equalTo(grafo.getGrafo().get(caminho.get(i)).getAnterior())));
            } else {
                assertThat(grafo.getGrafo().get(caminho.get(i + 1)), is(equalTo(grafo.getGrafo().get(caminho.get(i)).getAnterior())));
            }
        }
    }

    @Test
    public void testDijkstraComVerticeDestinoAlcancavelNaoDeveRetornarUmMenorCaminhoValido() {
        final String ORIGEM = "WS";
        final String DESTINO = "A";
        final Grafo grafo = new Grafo(gerarMapaDeVertices());
        grafo.dijkstra(ORIGEM);

        assertNull(grafo.getGrafo().get(DESTINO).getAnterior());
    }

    @Test
    public void testDijkstraComVerticeOrigemInexistenteNaoDeveRetornarUmMenorCaminhoValido() {
        final String ORIGEM = "C";
        final String DESTINO = "LS";
        final Grafo grafo = new Grafo(gerarMapaDeVertices());
        grafo.dijkstra(ORIGEM);

        assertNull(grafo.getGrafo().get(DESTINO).getAnterior());
    }

    private Map<String, Vertice> gerarMapaDeVertices() {
        Map<String, Vertice> grafo  = new HashMap<String, Vertice>() {{
            put("LS", new Vertice("LS"));
            put("SF", new Vertice("SF"));
            put("LV", new Vertice("LV"));
            put("RC", new Vertice("RC"));
            put("WS", new Vertice("WS"));
            put("BC", new Vertice("BC"));
            put("A", new Vertice("A"));
            put("B", new Vertice("B"));
        }};

        grafo.get("LS").addVizinho(grafo.get("SF"), 1);
        grafo.get("SF").addVizinho(grafo.get("LS"), 2);
        grafo.get("LS").addVizinho(grafo.get("LV"), 1);
        grafo.get("LV").addVizinho(grafo.get("LS"), 1);
        grafo.get("SF").addVizinho(grafo.get("LV"), 2);
        grafo.get("LV").addVizinho(grafo.get("SF"), 2);
        grafo.get("LS").addVizinho(grafo.get("RC"), 1);
        grafo.get("RC").addVizinho(grafo.get("LS"), 2);
        grafo.get("SF").addVizinho(grafo.get("WS"), 1);
        grafo.get("WS").addVizinho(grafo.get("SF"), 2);
        grafo.get("LV").addVizinho(grafo.get("BC"), 1);
        grafo.get("BC").addVizinho(grafo.get("LV"), 1);
        grafo.get("A").addVizinho(grafo.get("B"), 1);

        return grafo;
    }
}
