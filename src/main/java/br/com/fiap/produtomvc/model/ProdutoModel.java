package br.com.fiap.produtomvc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Objects;


@Entity
@Table(name="tb_produto")
public class ProdutoModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Campo requerido")
    @Positive(message = "O valor deve ser positivo")
    private double valor;

    @NotBlank(message = "Campo requerido")
    @Size(min = 3, message = "O nome ter no minimo 3 caracteres")
    private String nome;

    @NotBlank(message = "Campo requerido")
    private String descricao;

    public ProdutoModel() {
    }

    public ProdutoModel(int id, double valor, String nome, String descricao) {
        this.id = id;
        this.valor = valor;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoModel that = (ProdutoModel) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProdutoModel{" +
                "id=" + id +
                ", valor=" + valor +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
