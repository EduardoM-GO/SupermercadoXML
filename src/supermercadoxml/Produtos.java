/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercadoxml;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

/**
 *
 * @author Eduardo M
 */
@Entity
public class Produtos {

    @Id
    private int codigo;

    @Column(nullable = false)
    private String Descricao;
    @Column(nullable = false)
    private int Quantidade;
    @Column(nullable = false)
    private double PrecoDeCompra;
    @Column(nullable = false)
    private double PrecoDeVenda;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public double getPrecoDeCompra() {
        return PrecoDeCompra;
    }

    public void setPrecoDeCompra(double PrecoDeCompra) {
        this.PrecoDeCompra = PrecoDeCompra;
    }

    public double getPrecoDeVenda() {
        return PrecoDeVenda;
    }

    public void setPrecoDeVenda(double PrecoDeVenda) {
        this.PrecoDeVenda = PrecoDeVenda;
    }

}
