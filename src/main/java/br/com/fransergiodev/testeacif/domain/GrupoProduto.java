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
public class GrupoProduto {
    private Long id;
    private String descricao;
    private Calendar dataCadastro;

    public GrupoProduto(Long id, String descricao, Calendar dataCadastro) {
        this.id = id;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    
}
