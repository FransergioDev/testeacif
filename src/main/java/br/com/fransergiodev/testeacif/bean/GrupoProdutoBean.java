/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fransergiodev.testeacif.bean;

import br.com.fransergiodev.testeacif.dao.GrupoProdutoDao;
import br.com.fransergiodev.testeacif.domain.GrupoProduto;
import br.com.fransergiodev.testeacif.util.JsfUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;

/**
 *
 * @author fransergio-dev
 */
@ManagedBean
@ViewScoped
public class GrupoProdutoBean implements java.io.Serializable {

    public GrupoProduto grupoProduto;
    public GrupoProduto grupoProdutoSelecionado;
    public List<GrupoProduto> gruposProduto;
    public GrupoProdutoDao grupoProdutoDao;
    public boolean edit;

    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
    }

    public List<GrupoProduto> getGruposProduto() {
        return gruposProduto;
    }

    public void setGruposProduto(List<GrupoProduto> gruposProduto) {
        this.gruposProduto = gruposProduto;
    }    
    

    @PostConstruct
    public void init() {
        this.grupoProduto = new GrupoProduto();
        this.grupoProdutoDao = new GrupoProdutoDao();
        this.gruposProduto = new ArrayList<>();
        this.grupoProdutoSelecionado = new GrupoProduto();
        this.edit = false;
    }

    public void list() {
        try {
            this.gruposProduto = this.grupoProdutoDao.getAll();
        } catch (SQLException | ClassNotFoundException e) {
            JsfUtil.addMsgErro("Erro ao listar grupos de produtos");
            System.out.println("ERRO:" + e.getMessage());
        }
    }

    public void save() {
        if (!this.edit) {
            this.create();
        }else{
            this.update();
        }
        this.cancel();
    }

    public void cancel() {
        this.grupoProduto = new GrupoProduto();
        this.grupoProdutoSelecionado = new GrupoProduto();
        this.edit = false;
    }

    private void create() {
        try {
            this.grupoProduto.setDataCadastro(new Date());
            this.grupoProdutoDao.save(this.grupoProduto);
            JsfUtil.addMsgSucesso("Grupo de produto adicionado com sucesso!");
        } catch (SQLException | ClassNotFoundException e) {
            JsfUtil.addMsgErro("Erro ao salvar grupo de produtos");
            System.out.println("ERRO:" + e.getMessage());
        }
    }

    private void update() {
        try {
            this.grupoProdutoDao.update(this.grupoProduto);
            this.grupoProdutoDao.log(this.grupoProdutoSelecionado);
            this.cancel();
            JsfUtil.addMsgSucesso("Grupo de produto alterado com sucesso!");
        } catch (SQLException | ClassNotFoundException e) {
            JsfUtil.addMsgErro("Erro ao alterar grupo de produtos");
            System.out.println("ERRO:" + e.getMessage());
        }
    }

    public void select(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
        this.grupoProdutoSelecionado = grupoProduto;
        this.edit = true;
    }

    public void selectId() {
        try {
            this.grupoProduto=this.grupoProdutoDao.getById(this.grupoProduto);
        } catch (SQLException | ClassNotFoundException e) {
            JsfUtil.addMsgErro("Erro ao selecionar grupo de produtos");
            System.out.println("ERRO:" + e.getMessage());
        }
    }

    public void delete() {
        try {
            this.grupoProdutoDao.delete(this.grupoProduto);
            this.grupoProdutoDao.log(this.grupoProdutoSelecionado);
            this.cancel();
            JsfUtil.addMsgSucesso("Grupo de produto apagado com sucesso!");
        } catch (SQLException | ClassNotFoundException e) {
            JsfUtil.addMsgErro("Erro ao apagar grupo de produtos");
            System.out.println("ERRO:" + e.getMessage());
        }
    }

}
