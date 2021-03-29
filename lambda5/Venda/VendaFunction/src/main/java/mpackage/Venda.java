package mpackage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Venda implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("produto")
    private String produto;

    public Venda() {
    }

    public Venda(Long id, String produto) {
        this.id = id;
        this.produto = produto;
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
}
