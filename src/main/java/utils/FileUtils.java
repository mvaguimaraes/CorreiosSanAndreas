package utils;

import model.Encomenda;
import model.Grafo;
import model.Vertice;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtils {

    private static final String DELIMITADOR = " ";

    public static Map<String, Vertice> lerArquivoTrechos(String nomeArquivo) {
        String linha;
        int numLinha = 1;
        Map<String, Vertice> arestas = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(String.format("src/main/resources/%s.txt", nomeArquivo)))) {
            while ((linha = br.readLine()) != null) {
                String[] lineArray = linha.split(DELIMITADOR);

                if (lineArray.length == 3) {
                    if (!arestas.containsKey(lineArray[0])) {
                        arestas.put(lineArray[0], new Vertice(lineArray[0]));
                    }
                    if (!arestas.containsKey(lineArray[1])) {
                        arestas.put(lineArray[1], new Vertice(lineArray[1]));
                    }
                    arestas.get(lineArray[0]).addVizinho(arestas.get(lineArray[1]), Integer.parseInt(lineArray[2]));
                    numLinha++;
                } else {
                    System.out.println(String.format("A linha %d do arquivo trechos.txt foi pulada porque está no formato errado!", numLinha));
                    numLinha++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return arestas;
    }

    public static List<Encomenda> lerArquivoEncomendas(String nomeArquivo) {

        List<Encomenda> encomendas = new ArrayList<>();

        String linha;
        int numLinha = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(String.format("src/main/resources/%s.txt", nomeArquivo)))) {
            while ((linha = br.readLine()) != null) {
                String[] vetorLinha = linha.split(DELIMITADOR);

                if (vetorLinha.length == 2) {
                    Encomenda encomenda = new Encomenda(vetorLinha[0], vetorLinha[1]);
                    encomendas.add(encomenda);
                } else {
                    System.out.println(String.format("A linha %d do arquivo %s.txt foi pulada porque está no formato errado!", numLinha, nomeArquivo));
                    numLinha++;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return encomendas;
    }

    public static void processarEncomendas(Map<String, Vertice> arestas, List<Encomenda> encomendas, String nomeArquivoRotas) {

        Grafo g = new Grafo(arestas);

        for (Encomenda encomenda : encomendas) {
            g.dijkstra(encomenda.getOrigem());
            if (!g.getGrafo().containsKey(encomenda.getOrigem())) {
                System.out.println(String.format("Grafo não contém o vértice de origem: \"%s\"\n", encomenda.getOrigem()));
            } else {
                escreverArquivoRotas(g.getGrafo().get(encomenda.getDestino()), nomeArquivoRotas);
            }
        }

    }

    private static void escreverArquivoRotas(Vertice vertice, String nomeArquivo) {
        File log = new File(String.format("src/main/resources/%s.txt", nomeArquivo));
        try{
            if(!log.exists()){
                System.out.println("O arquivo de rotas foi criado!");
                log.createNewFile();
            }
            PrintWriter out = new PrintWriter(new FileWriter(log, true));
            boolean success = imprimirRota(out, vertice);
            if (success) {
                out.append(Integer.toString(vertice.getDistancia()));
            }
            out.append("\n");
            out.close();
        }catch(IOException e){
            System.out.println("Não foi possível criar o arquivo de rotas!");
        }
    }

    private static boolean imprimirRota(PrintWriter out, Vertice vertice) {
        if (vertice.getAnterior() == vertice) {
            out.append(String.format("%s ", vertice.getNome()));
            return true;
        } else if (vertice.getAnterior() == null) {
            out.append(String.format("O vértice %s não é alcançável!", vertice.getNome()));
            return false;
        } else {
            imprimirRota(out, vertice.getAnterior());
            out.append(String.format("%s ", vertice.getNome()));
            return true;
        }
    }
}
