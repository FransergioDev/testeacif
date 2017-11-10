/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fransergiodev.testeacif.dao;

import br.com.fransergiodev.testeacif.domain.GrupoProduto;
import br.com.fransergiodev.testeacif.domain.Produto;
import br.com.fransergiodev.testeacif.util.ConnectionFactory;
import br.com.fransergiodev.testeacif.util.DaoUtil;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fransergio-dev
 */
public class ProdutoDao implements DaoGeneric<Produto, Serializable> {

    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rset = null;

    @Override
    public void save(Object T) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO produto(id_grupo,descricao,marca,data_cadastro) VALUES(?,?,?,?)";
        try {
            this.conn = ConnectionFactory.getConnection();
            Produto produto = (Produto) T;
            //Cria um PreparedStatment, classe usada para executar a query
            this.pstm = conn.prepareStatement(sql);

            //Adicionando valor aos parametros da sql
            this.pstm.setInt(1, Integer.parseInt(produto.getGrupoProduto().getId().toString()));
            this.pstm.setString(2, produto.getDescricao());
            this.pstm.setString(3, produto.getMarca());
            this.pstm.setDate(4, new Date(produto.getDataCadastro().getTime()));
            //Executa a sql para inserção dos dados
            this.pstm.execute();

        } finally {
            DaoUtil.closeConn(this.pstm, this.conn);
        }
    }

    @Override
    public void update(Object T) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE produto SET id_grupo=?,descricao=?,marca=?,data_cadastro=? WHERE id=?";
        try {
            this.conn = ConnectionFactory.getConnection();
            Produto produto = (Produto) T;
            //Cria um PreparedStatment, classe usada para executar a query
            this.pstm = conn.prepareStatement(sql);

            //Adicionando valor aos parametros da sql
            this.pstm.setInt(1, Integer.parseInt(produto.getGrupoProduto().getId().toString()));
            this.pstm.setString(2, produto.getDescricao());
            this.pstm.setString(3, produto.getMarca());
            this.pstm.setDate(4, new Date(produto.getDataCadastro().getTime()));

            this.pstm.setInt(5, Integer.parseInt(produto.getId().toString()));
            //Executa a sql para inserção dos dados
            this.pstm.execute();

        } finally {
            DaoUtil.closeConn(this.pstm, this.conn);
        }
    }

    public void log(Produto produto) throws SQLException, ClassNotFoundException {
        try {
            this.conn = ConnectionFactory.getConnection();
            CallableStatement cal = conn.prepareCall("{call logProduto(?,?,?,?,?,?)}");
            cal.setInt(1, Integer.parseInt(produto.getId().toString()));
            cal.setInt(2, Integer.parseInt(produto.getGrupoProduto().getId().toString()));
            cal.setString(3, produto.getDescricao());
            cal.setString(4, produto.getMarca());
            cal.setDate(5, new Date(produto.getDataCadastro().getTime()));
            cal.setDate(6, new Date(new java.util.Date().getTime()));
            cal.executeQuery();
        } finally {
            DaoUtil.closeConn(this.pstm, this.conn);
        }

    }

    @Override
    public void delete(Object T) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM produto WHERE id=?";
        try {
            this.conn = ConnectionFactory.getConnection();
            Produto produto = (Produto) T;
            //Cria um PreparedStatment, classe usada para executar a query
            this.pstm = conn.prepareStatement(sql);

            //Adicionando valor aos parametros da sql
            this.pstm.setInt(1, Integer.valueOf(produto.getId().toString()));

            //Executa a sql para inserção dos dados
            this.pstm.execute();

        } finally {
            DaoUtil.closeConn(this.pstm, this.conn);
        }
    }

    @Override
    public Produto getById(Object T) throws SQLException, ClassNotFoundException {
        String sql = "SELECT id,id_grupo,descricao,marca,data_cadastro FROM produto WHERE id=?";
        try {
            this.conn = ConnectionFactory.getConnection();

            Produto produto = (Produto) T;

            //Cria um PreparedStatment, classe usada para executar a query
            this.pstm = conn.prepareStatement(sql);
            //Adicionando valor aos parametros da sql
            this.pstm.setInt(1, Integer.parseInt(produto.getId().toString()));

            //Executa a sql para inserção dos dados
            this.rset = this.pstm.executeQuery();

            GrupoProdutoDao grupoProdutoDao = new GrupoProdutoDao();

            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                GrupoProduto grupoProduto = new GrupoProduto();
                grupoProduto.setId(Long.valueOf(rset.getInt("id_grupo")));
                grupoProduto = grupoProdutoDao.getById(grupoProduto);

                produto = new Produto();
                produto.setId(Long.valueOf(rset.getInt("id")));
                produto.setGrupoProduto(grupoProduto);
                produto.setDescricao(rset.getString("descricao"));
                produto.setMarca(rset.getString("marca"));
                produto.setDataCadastro(rset.getDate("data_cadastro"));

            }
            return produto;
        } finally {
            DaoUtil.closeConn(this.pstm, this.conn);
        }
    }

    @Override
    public List<Produto> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT id,id_grupo,descricao,marca,data_cadastro FROM produto";
        List<Produto> produtos = new ArrayList<>();
        try {
            this.conn = ConnectionFactory.getConnection();
            this.pstm = conn.prepareStatement(sql);

            this.rset = pstm.executeQuery();
            GrupoProdutoDao grupoProdutoDao = new GrupoProdutoDao();

            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                GrupoProduto grupoProduto = new GrupoProduto();
                grupoProduto.setId(Long.valueOf(rset.getInt("id_grupo")));
                grupoProduto = grupoProdutoDao.getById(grupoProduto);

                Produto produto = new Produto();
                produto.setId(Long.valueOf(rset.getInt("id")));
                produto.setGrupoProduto(grupoProduto);
                produto.setDescricao(rset.getString("descricao"));
                produto.setMarca(rset.getString("marca"));
                produto.setDataCadastro(rset.getDate("data_cadastro"));

                //Adiciono o contato recuperado, a lista de contatos
                produtos.add(produto);
            }
        } finally {
            DaoUtil.closeConn(this.pstm, this.conn);
        }
        return produtos;
    }

    public List<Produto> getSearch(String field, String value) throws SQLException, ClassNotFoundException, ParseException {

        String sql;
        switch (field) {
            case "descricao":
                sql = "SELECT id,id_grupo,descricao,marca,data_cadastro FROM produto WHERE descricao LIKE ?";
                break;
            case "marca":
                sql = "SELECT id,id_grupo,descricao,marca,data_cadastro FROM produto WHERE marca LIKE ?";
                break;
            case "grupo":
                sql = "SELECT produto.id,produto.id_grupo,produto.descricao,produto.marca,produto.data_cadastro,grupo_produto.descricao FROM produto \n" +
                "INNER JOIN grupo_produto ON produto.id_grupo = grupo_produto.id WHERE grupo_produto.descricao LIKE ?";
                break;
            case "data_cadastro":
                sql = "SELECT id,id_grupo,descricao,marca,data_cadastro FROM produto WHERE data_cadastro=?";
                break;
            default:
                sql = "SELECT id,id_grupo,descricao,marca,data_cadastro FROM produto WHERE descricao LIKE ?";
                break;
        }

        List<Produto> produtos = new ArrayList<>();
        try {
            this.conn = ConnectionFactory.getConnection();
            this.pstm = conn.prepareStatement(sql);

            if (!field.equals("data_cadastro")) {
                //Adicionando valor aos parametros da sql
                this.pstm.setString(1, "%" + value + "%");
            } else {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date dc = formato.parse(value);
                this.pstm.setDate(1, new Date(dc.getTime()));
            }

            this.rset = pstm.executeQuery();
            GrupoProdutoDao grupoProdutoDao = new GrupoProdutoDao();

            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                GrupoProduto grupoProduto = new GrupoProduto();
                grupoProduto.setId(Long.valueOf(rset.getInt("id_grupo")));
                grupoProduto = grupoProdutoDao.getById(grupoProduto);

                Produto produto = new Produto();
                produto.setId(Long.valueOf(rset.getInt("id")));
                produto.setGrupoProduto(grupoProduto);
                produto.setDescricao(rset.getString("descricao"));
                produto.setMarca(rset.getString("marca"));
                produto.setDataCadastro(rset.getDate("data_cadastro"));

                //Adiciono o contato recuperado, a lista de contatos
                produtos.add(produto);
            }
        } finally {
            DaoUtil.closeConn(this.pstm, this.conn);
        }
        return produtos;
    }
}
