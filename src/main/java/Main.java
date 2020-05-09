import model.Encomenda;
import model.Vertice;
import utils.FileUtils;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        //Processando o arquivo de trechos
        Map<String, Vertice> trechos = FileUtils.lerArquivoTrechos("trechos");
        //Processando o arquivo de encomendas.txt
        List<Encomenda> encomendas = FileUtils.lerArquivoEncomendas("encomendas");
        //Gerando o arquivo de rotas
        FileUtils.processarEncomendas(trechos, encomendas, "rotas");
    }
}
