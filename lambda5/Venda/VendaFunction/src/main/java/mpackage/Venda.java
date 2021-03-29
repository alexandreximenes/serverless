package mpackage;

public class Venda {

    private Long id;
    private String produto;
    private Double preco;

    public Venda() {
    }

    public Venda(Long id, String produto, Double preco) {
        this.id = id;
        this.produto = produto;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
