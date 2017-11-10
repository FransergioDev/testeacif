/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fransergiodev.testeacif.dao;

import br.com.fransergiodev.testeacif.domain.Produto;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author fransergio-dev
 */
public class ProdutoDaoTest {

    @Test
    public void search() throws ClassNotFoundException, SQLException, ParseException {
        String fild = "data_cadastro";
        String value = "09/11/2017 22:00";

        ProdutoDao produtoDao = new ProdutoDao();
        List<Produto> produtos = produtoDao.getSearch(fild, value);

        System.out.println("Resultado:");
        produtos.forEach((produto) -> {
            Date data = produto.getDataCadastro();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String sdate = sdf.format(data.getTime());
            System.out.println("id: " + produto.getId() + ",descrição:" + produto.getDescricao() + ",marca:" + produto.getMarca() + ",grupo:" + produto.getGrupoProduto().getDescricao() + ",data cadastro:" + sdate);
        });

    }

}
