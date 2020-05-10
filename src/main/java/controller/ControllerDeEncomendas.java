package controller;

import domain.Cidade;
import domain.Encomenda;
import utils.FileUtils;

import java.util.*;

public class ControllerDeEncomendas {

    private Map<String, Cidade> trechos;

    public ControllerDeEncomendas(Map<String, Cidade> trechos) {
        this.trechos = trechos;
    }

    /**
     * Processar encomendas e descobrir a rota de menor custo entre as cidades
     */
    public void processarEncomendas(List<Encomenda> encomendas, String nomeArquivoRotas) {

        if (!Objects.isNull(encomendas) && !Objects.isNull(nomeArquivoRotas)) {
            for (Encomenda encomenda : encomendas) {
                menorCaminhoDijkstra(encomenda.getOrigem());
                if (!trechos.containsKey(encomenda.getOrigem())) {
                    System.out.println(String.format("Mapa de trechos não contém o vértice de origem: \"%s\"\n", encomenda.getOrigem()));
                } else {
                    FileUtils.escreverArquivoRotas(trechos.get(encomenda.getDestino()), nomeArquivoRotas);
                }
            }
        } else {
            System.out.println("A lista de encomendas e/ou nome do arquivo de rotas não podem ser nulos");
        }
    }

    /**
     * Implementação do Algoritmo de Dijkstra para menor caminho entre pontos
     */
    public Map<String, Cidade> menorCaminhoDijkstra(String nomeOrigem) {

        if(!Objects.isNull(nomeOrigem) && verificarExistenciaCidadeOrigem(nomeOrigem)) {
            final Cidade origem = trechos.get(nomeOrigem);
            NavigableSet<Cidade> cidades = new TreeSet<>();

            // Inicializando as cidades
            for (Cidade cidade : trechos.values()) {
                cidade.setAnterior(cidade == origem ? origem : null);
                cidade.setDistancia(cidade == origem ? 0 : Integer.MAX_VALUE);
                cidades.add(cidade);
            }

            Cidade cidade, vizinho;
            while (!cidades.isEmpty()) {

                // Cidade com a menor distância
                cidade = cidades.pollFirst();

                if (cidade.getDistancia() == Integer.MAX_VALUE) {
                    break;
                }

                // Verificando as distâncias entre cada cidade vizinha
                for (Map.Entry<Cidade, Integer> a : cidade.getVizinhos().entrySet()) {

                    // O vizinho dessa iteração
                    vizinho = a.getKey();

                    final int distAlternativa = cidade.getDistancia() + a.getValue();
                    if (distAlternativa < vizinho.getDistancia()) {
                        // Menor caminho encontrado
                        cidades.remove(vizinho);
                        vizinho.setDistancia(distAlternativa);
                        vizinho.setAnterior(cidade);
                        cidades.add(vizinho);
                    }
                }
            }
        } else {
            System.out.println("O nome da cidade de origem não podem ser nula");
        }

        return trechos;
    }

    private boolean verificarExistenciaCidadeOrigem(String nomeOrigem) {
        if (!trechos.containsKey(nomeOrigem)) {
            System.out.println(String.format("Mapa de trechos não contém a cidade de origem: %s", nomeOrigem));
            return false;
        }
        return true;
    }
}
