import model.Encomenda;
import model.Grafo;
import model.Vertice;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import utils.FileUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class FileUtilsTest {

    @Test
    public void testLerTrechosComDadosValidosDeveRetornarUmMapaDeArestasValido() {

        final Map<String, Vertice> arestasEsperadas = gerarMapaDeArestas();
        final Map<String, Vertice> arestasLidas = FileUtils.lerArquivoTrechos("testes/trechos");

        assertThat(arestasEsperadas.size(), is(equalTo(arestasLidas.size())));

        for (Map.Entry<String, Vertice> arestaLida : arestasLidas.entrySet()) {
            assertThat(arestasEsperadas.get(arestaLida.getKey()).getNome(), is(equalTo(arestaLida.getValue().getNome())));
            assertThat(arestasEsperadas.get(arestaLida.getKey()).getVizinhos().size(), is(equalTo(arestaLida.getValue().getVizinhos().size())));
        }
    }

    @Test
    public void testLerEncomendasComDadosValidosDeveRetornarUmaListaValida() {

        final List<Encomenda> encomendasEsperadas = gerarEncomendas();
        final List<Encomenda> encomendasLidas = FileUtils.lerArquivoEncomendas("testes/encomendas");

        assertThat(encomendasEsperadas.size(), is(equalTo(encomendasLidas.size())));

        for (int i = 0; i < encomendasLidas.size(); i++) {
            assertThat(encomendasEsperadas.get(i).getOrigem(), is(equalTo(encomendasLidas.get(i).getOrigem())));
            assertThat(encomendasEsperadas.get(i).getDestino(), is(equalTo(encomendasLidas.get(i).getDestino())));
        }
    }

    private Map<String, Vertice> gerarMapaDeArestas() {
        Map<String, Vertice> grafo  = new HashMap<String, Vertice>() {{
            put("LS", new Vertice("LS"));
            put("SF", new Vertice("SF"));
            put("LV", new Vertice("LV"));
            put("RC", new Vertice("RC"));
            put("WS", new Vertice("WS"));
            put("BC", new Vertice("BC"));
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

        return grafo;
    }

    private List<Encomenda> gerarEncomendas() {
        return new ArrayList<>(Arrays.asList(
                new Encomenda("SF", "WS"),
                new Encomenda("LS", "BC"),
                new Encomenda("WS", "BC")));
    }
}
