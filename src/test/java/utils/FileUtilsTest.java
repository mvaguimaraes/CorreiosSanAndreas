package utils;

import domain.Cidade;
import domain.Encomenda;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class FileUtilsTest {

    @Test
    public void testLerTrechosComDadosValidosDeveRetornarUmMapaDeArestasValido() {

        final Map<String, Cidade> arestasEsperadas = gerarMapaDeArestas();
        final Map<String, Cidade> arestasLidas = FileUtils.lerArquivoTrechos("testes/trechos_teste");

        assertThat(arestasEsperadas.size(), is(equalTo(arestasLidas.size())));

        for (Map.Entry<String, Cidade> arestaLida : arestasLidas.entrySet()) {
            assertThat(arestasEsperadas.get(arestaLida.getKey()).getNome(), is(equalTo(arestaLida.getValue().getNome())));
            assertThat(arestasEsperadas.get(arestaLida.getKey()).getVizinhos().size(), is(equalTo(arestaLida.getValue().getVizinhos().size())));
        }
    }

    @Test
    public void testLerTrechosComArestasPesoNegativoDeveRetornarMapaDeTrechosVazio() {

        final Map<String, Cidade> arestasLidas = FileUtils.lerArquivoTrechos("testes/trechos_negativos_teste");

        assertThat(0, is(equalTo(arestasLidas.size())));
    }

    @Test
    public void testLerTrechosComParametroNuloDeveRetornarMapaVazio() {

        final Map<String, Cidade> arestasLidas = FileUtils.lerArquivoTrechos(null);

        assertThat(0, is(equalTo(arestasLidas.size())));
    }

    @Test
    public void testLerEncomendasComDadosValidosDeveRetornarUmaListaValida() {

        final List<Encomenda> encomendasEsperadas = gerarEncomendas();
        final List<Encomenda> encomendasLidas = FileUtils.lerArquivoEncomendas("testes/encomendas_teste");

        assertThat(encomendasEsperadas.size(), is(equalTo(encomendasLidas.size())));

        for (int i = 0; i < encomendasLidas.size(); i++) {
            assertThat(encomendasEsperadas.get(i).getOrigem(), is(equalTo(encomendasLidas.get(i).getOrigem())));
            assertThat(encomendasEsperadas.get(i).getDestino(), is(equalTo(encomendasLidas.get(i).getDestino())));
        }
    }

    @Test
    public void testLerEncomendasComParametroNuloDeveRetornarListaVazia() {

        final List<Encomenda> encomendasLidas = FileUtils.lerArquivoEncomendas(null);

        assertThat(0, is(equalTo(encomendasLidas.size())));
    }

    private Map<String, Cidade> gerarMapaDeArestas() {
        Map<String, Cidade> grafo  = new HashMap<String, Cidade>() {{
            put("LS", new Cidade("LS"));
            put("SF", new Cidade("SF"));
            put("LV", new Cidade("LV"));
            put("RC", new Cidade("RC"));
            put("WS", new Cidade("WS"));
            put("BC", new Cidade("BC"));
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
