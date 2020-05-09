package model;

public class Encomenda {

    private String origem;
    private String destino;

    public Encomenda(String origem, String destino) {
        this.origem = origem;
        this.destino = destino;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }
}
