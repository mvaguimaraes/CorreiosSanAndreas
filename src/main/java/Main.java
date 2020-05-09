import controller.ControllerDeEncomendas;
import domain.Encomenda;
import domain.Cidade;
import utils.FileUtils;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        //Processando o arquivo de trechos
        Map<String, Cidade> trechos = FileUtils.lerArquivoTrechos("trechos");
        //Processando o arquivo de encomendas
        List<Encomenda> encomendas = FileUtils.lerArquivoEncomendas("encomendas");
        //Gerando o arquivo de rotas
        ControllerDeEncomendas controllerDeEncomendas = new ControllerDeEncomendas(trechos);
        controllerDeEncomendas.processarEncomendas(encomendas, "rotas");
    }
}
