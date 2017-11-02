/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fransergiodev.testeacif.domain;

import java.util.Calendar;

/**
 *
 * @author fransergio-dev
 */
public class Produto {
    private Long id;
    private GrupoProduto grupoProduto;
    private String descricao;
    private String marca;
    private Calendar dataCadastro;

    public Produto(Long id, GrupoProduto grupoProduto, String descricao, String marca, Calendar dataCadastro) {
        this.id = id;
        this.grupoProduto = grupoProduto;
        this.descricao = descricao;
        this.marca = marca;
        this.dataCadastro = dataCadastro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    
}
