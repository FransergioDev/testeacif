/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fransergiodev.testeacif.dao;

import br.com.fransergiodev.testeacif.domain.GrupoProduto;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author fransergio-dev
 */
public class GrupoProdutoDaoTest {

    @Ignore
    public void insert() throws SQLException, ClassNotFoundException {
        GrupoProdutoDao grupoProdutoDao = new GrupoProdutoDao();
        GrupoProduto grupoProduto = new GrupoProduto();
        grupoProduto.setDescricao("Roupas");
        Date now = new Date();
        grupoProduto.setDataCadastro(now);
        grupoProdutoDao.save(grupoProduto);
        System.out.println("Grupo de produto adicionado com sucesso!");
    }

    @Ignore
    public void remove() throws SQLException, ClassNotFoundException {
        GrupoProdutoDao grupoProdutoDao = new GrupoProdutoDao();
        GrupoProduto grupoProduto = new GrupoProduto();
        grupoProduto.setId(1L);
        grupoProdutoDao.delete(grupoProduto);
        System.out.println("Grupo de produto removido com sucesso!");
    }

    @Ignore
    public void list() throws SQLException, ClassNotFoundException {
        GrupoProdutoDao grupoProdutoDao = new GrupoProdutoDao();
        List<GrupoProduto> gruposProduto;
        gruposProduto = grupoProdutoDao.getAll();
        gruposProduto.forEach((grupoProduto) -> {
            Date data = grupoProduto.getDataCadastro();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String sdate = sdf.format(data.getTime());

            System.out.println(
                    "ID:" + grupoProduto.getId()
                    + ", Descrição:" + grupoProduto.getDescricao()
                    + ",Data de cadastro:" + sdate
            );
        });
    }
    
    @Test
    public void getId() throws SQLException, ClassNotFoundException {
        GrupoProdutoDao grupoProdutoDao = new GrupoProdutoDao();
        GrupoProduto grupoProduto = new GrupoProduto();
        grupoProduto.setId(7L);
        grupoProduto = grupoProdutoDao.getById(grupoProduto);
        System.out.println("Grupo de produto:"+grupoProduto.getDescricao());
    }

}
