package utils;

import domain.Encomenda;
import domain.Cidade;

import java.io.*;
import java.util.*;

public class FileUtils {

    private static final String DELIMITADOR = " ";

    public static Map<String, Cidade> lerArquivoTrechos(String nomeArquivo) {

        Map<String, Cidade> arestas = new HashMap<>();

        if (!Objects.isNull(nomeArquivo)) {
            String linha;
            int numLinha = 1;

            try {
                BufferedReader br = new BufferedReader(new FileReader(String.format("src/main/resources/%s.txt", nomeArquivo)));
                while ((linha = br.readLine()) != null) {
                    String[] lineArray = linha.split(DELIMITADOR);

                    if (lineArray.length == 3) {
                        if (!arestas.containsKey(lineArray[0])) {
                            arestas.put(lineArray[0], new Cidade(lineArray[0]));
                        }
                        if (!arestas.containsKey(lineArray[1])) {
                            arestas.put(lineArray[1], new Cidade(lineArray[1]));
                        }
                        arestas.get(lineArray[0]).addVizinho(arestas.get(lineArray[1]), Integer.parseInt(lineArray[2]));
                        numLinha++;
                    } else {
                        System.out.println(String.format("A linha %d do arquivo trechos.txt foi pulada porque está no formato errado!", numLinha));
                        numLinha++;
                    }
                }

            } catch (FileNotFoundException e) {
                System.out.println(String.format("O arquivo de trechos %s.txt não foi encontrado!\n", nomeArquivo));
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("O nome do arquivo de trechos não pode ser nulo");
        }
        return arestas;
    }

    public static List<Encomenda> lerArquivoEncomendas(String nomeArquivo) {

        List<Encomenda> encomendas = new ArrayList<>();

        if (!Objects.isNull(nomeArquivo)) {

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

            } catch (FileNotFoundException e) {
                System.out.println(String.format("O arquivo de encomendas %s.txt não foi encontrado!\n", nomeArquivo));
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("O nome do arquivo de encomendas não pode ser nulo");
        }

        return encomendas;
    }

    public static void escreverArquivoRotas(Cidade cidade, String nomeArquivo) {

        if (!Objects.isNull(cidade) && !Objects.isNull(nomeArquivo)) {
            File log = new File(String.format("src/main/resources/%s.txt", nomeArquivo));
            try {
                if (!log.exists()) {
                    System.out.println("O arquivo de rotas foi criado!");
                    log.createNewFile();
                }
                PrintWriter out = new PrintWriter(new FileWriter(log, true));
                boolean success = imprimirRota(out, cidade);
                if (success) {
                    out.append(Integer.toString(cidade.getDistancia()));
                }
                out.append("\n");
                out.close();
            } catch (IOException e) {
                System.out.println("Não foi possível criar o arquivo de rotas!");
            }
        }
    }

    private static boolean imprimirRota(PrintWriter out, Cidade cidade) {
        if (!Objects.isNull(out)) {
            if (cidade.getAnterior() == cidade) {
                out.append(String.format("%s ", cidade.getNome()));
                return true;
            } else if (cidade.getAnterior() == null) {
                out.append(String.format("O vértice %s não é alcançável!", cidade.getNome()));
                return false;
            } else {
                imprimirRota(out, cidade.getAnterior());
                out.append(String.format("%s ", cidade.getNome()));
                return true;
            }
        } else {
            return false;
        }
    }
}
