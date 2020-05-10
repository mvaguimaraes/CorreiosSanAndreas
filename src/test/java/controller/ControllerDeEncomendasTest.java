package controller;

import domain.Cidade;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ControllerDeEncomendasTest {

    @Test
    public void testDijkstraComDadosValidosDeveRetornarUmMenorCaminhoValido() {
        final String ORIGEM = "WS";
        final String DESTINO = "BC";
        final List<String> caminho = new ArrayList<>(Arrays.asList("BC", "LV", "SF", "WS"));
        final Map<String, Cidade> trechos = gerarMapaDeTrechos();
        final ControllerDeEncomendas controllerDeEncomendas = new ControllerDeEncomendas(trechos);
        controllerDeEncomendas.menorCaminhoDijkstra(ORIGEM);

        assertThat(5, is(equalTo(trechos.get(DESTINO).getDistancia())));
        for (int i = 0; i < caminho.size(); i++) {
            if (i == caminho.size()-1) {
                assertThat(trechos.get(caminho.get(i)), is(equalTo(trechos.get(caminho.get(i)).getAnterior())));
            } else {
                assertThat(trechos.get(caminho.get(i + 1)), is(equalTo(trechos.get(caminho.get(i)).getAnterior())));
            }
        }
    }

    @Test
    public void testDijkstraComVerticeDestinoAlcancavelNaoDeveRetornarUmMenorCaminhoValido() {
        final String ORIGEM = "WS";
        final String DESTINO = "A";
        final Map<String, Cidade> trechos = gerarMapaDeTrechos();
        final ControllerDeEncomendas controllerDeEncomendas = new ControllerDeEncomendas(trechos);
        controllerDeEncomendas.menorCaminhoDijkstra(ORIGEM);

        assertNull(trechos.get(DESTINO).getAnterior());
    }

    @Test
    public void testDijkstraComVerticeOrigemInexistenteNaoDeveRetornarUmMenorCaminhoValido() {
        final String ORIGEM = "C";
        final String DESTINO = "LS";
        final Map<String, Cidade> trechos = gerarMapaDeTrechos();
        final ControllerDeEncomendas controllerDeEncomendas = new ControllerDeEncomendas(trechos);
        controllerDeEncomendas.menorCaminhoDijkstra(ORIGEM);

        assertNull(trechos.get(DESTINO).getAnterior());
    }

    @Test
    public void testDijkstraComParametroNuloNaoDeveRetornarUmMenorCaminhoValido() {
        final String DESTINO = "LS";
        final Map<String, Cidade> trechos = gerarMapaDeTrechos();
        final ControllerDeEncomendas controllerDeEncomendas = new ControllerDeEncomendas(trechos);
        controllerDeEncomendas.menorCaminhoDijkstra(null);

        assertNull(trechos.get(DESTINO).getAnterior());
    }

    private Map<String, Cidade> gerarMapaDeTrechos() {
        Map<String, Cidade> grafo  = new HashMap<String, Cidade>() {{
            put("LS", new Cidade("LS"));
            put("SF", new Cidade("SF"));
            put("LV", new Cidade("LV"));
            put("RC", new Cidade("RC"));
            put("WS", new Cidade("WS"));
            put("BC", new Cidade("BC"));
            put("A", new Cidade("A"));
            put("B", new Cidade("B"));
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
