/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fransergiodev.testeacif.bean;

import br.com.fransergiodev.testeacif.dao.GrupoProdutoDao;
import br.com.fransergiodev.testeacif.dao.ProdutoDao;
import br.com.fransergiodev.testeacif.domain.GrupoProduto;
import br.com.fransergiodev.testeacif.domain.Produto;
import br.com.fransergiodev.testeacif.util.JsfUtil;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author fransergio-dev
 */
@ManagedBean
@ViewScoped
public class ProdutoBean implements java.io.Serializable {

    public Produto produto;
    public List<Produto> produtos;
    public ProdutoDao produtoDao;
    public Produto prod_selecionado;
    public boolean edit;
    public Long idGrupoSelecionado;
    public String field;
    public String value;
    public int quantRegistrosLocalizados;

    public GrupoProduto grupoProduto;
    public List<GrupoProduto> gruposProduto;
    public GrupoProdutoDao grupoProdutoDao;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
    }

    public List<GrupoProduto> getGruposProduto() {
        return gruposProduto;
    }

    public void setGruposProdutos(List<GrupoProduto> gruposProduto) {
        this.gruposProduto = gruposProduto;
    }

    public Long getIdGrupoSelecionado() {
        return idGrupoSelecionado;
    }

    public void setIdGrupoSelecionado(Long idGrupoSelecionado) {
        this.idGrupoSelecionado = idGrupoSelecionado;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getQuantRegistrosLocalizados() {
        return quantRegistrosLocalizados;
    }

    public void setQuantRegistrosLocalizados(int quantRegistrosLocalizados) {
        this.quantRegistrosLocalizados = quantRegistrosLocalizados;
    }
    
    
    
   
    @PostConstruct
    public void init() {
        this.produto = new Produto();
        this.grupoProduto = new GrupoProduto();
        this.produtoDao = new ProdutoDao();
        this.grupoProdutoDao = new GrupoProdutoDao();
        this.produtos = new ArrayList<>();
        this.gruposProduto = new ArrayList<>();
        this.prod_selecionado = new Produto();
        this.edit = false;
        this.quantRegistrosLocalizados = 0;
        this.listGroupProd();
    }

    public void listGroupProd() {
        try {
            this.gruposProduto = this.grupoProdutoDao.getAll();
        } catch (SQLException | ClassNotFoundException e) {
            JsfUtil.addMsgErro("Erro ao listar grupos de produto");
            System.out.println("ERRO:" + e.getMessage());
        }
    }

    public void list() {
        try {
            this.produtos = this.produtoDao.getAll();
        } catch (SQLException | ClassNotFoundException e) {
            JsfUtil.addMsgErro("Erro ao listar produtos");
            System.out.println("ERRO:" + e.getMessage());
        }
    }

    public void save() {
        if (!this.edit) {
            this.create();
        } else {
            this.update();
        }
        this.cancel();
    }

    public void cancel() {
        this.produto = new Produto();
        this.prod_selecionado = new Produto();
        this.edit = false;
    }

    private void create() {
        try {
            this.produto.setDataCadastro(new Date());
            this.produtoDao.save(this.produto);
            JsfUtil.addMsgSucesso("Produto salvo com sucesso!");
        } catch (SQLException | ClassNotFoundException e) {
            JsfUtil.addMsgErro("Erro ao salvar produto");
            System.out.println("ERRO:" + e.getMessage());
        }
    }

    public void select(Produto produto) {
        this.produto = produto;
        this.prod_selecionado = produto;
        this.edit = true;
    }

    public void selectIdGrupo() {
        try {
            this.produto.setGrupoProduto(this.grupoProdutoDao.getById(this.grupoProduto));
        } catch (SQLException | ClassNotFoundException e) {
            JsfUtil.addMsgErro("Erro ao selecionar grupo de produtos");
            System.out.println("ERRO:" + e.getMessage());
        }
    }

    private void update() {
        try {
            this.produtoDao.update(this.produto);
            this.produtoDao.log(this.prod_selecionado);
            this.cancel();
            JsfUtil.addMsgSucesso("Produto alterado com sucesso!");
        } catch (SQLException | ClassNotFoundException e) {
            JsfUtil.addMsgErro("Erro ao alterar produto");
            System.out.println("ERRO:" + e.getMessage());
        }
    }

    public void getId() {
        try {
            this.produto = this.produtoDao.getById(this.produto);
            this.prod_selecionado = this.produto;
        } catch (SQLException | ClassNotFoundException e) {
            JsfUtil.addMsgErro("Erro ao selecionar o produto");
            System.out.println("ERRO:" + e.getMessage());
        }
    }

    public void delete() {
        try {
            this.produtoDao.delete(this.produto);
            this.produtoDao.log(this.prod_selecionado);
            this.cancel();
            JsfUtil.addMsgSucesso("Produto apagado com sucesso!");
        } catch (SQLException | ClassNotFoundException e) {
            JsfUtil.addMsgErro("Erro ao apagar produto");
            System.out.println("ERRO:" + e.getMessage());
        }
    }

    public void search() throws ParseException {
        try {
            if(this.field.equals("")){
                this.field = "produto";
            }
            this.produtos = this.produtoDao.getSearch(this.field, this.value);
            this.quantRegistrosLocalizados =  this.produtos.size();
            JsfUtil.addMsgSucesso("Pesquisa realizada com sucesso!");
        } catch (SQLException | ClassNotFoundException e) {
            JsfUtil.addMsgErro("Erro ao pesquisar produtos");
            System.out.println("ERRO:" + e.getMessage());
        }
    }
    
    public void clearSearch(){
       this.produtos = new ArrayList<>();
       this.field= "";
       this.value = "";
       this.quantRegistrosLocalizados =  this.produtos.size();
    }
}
