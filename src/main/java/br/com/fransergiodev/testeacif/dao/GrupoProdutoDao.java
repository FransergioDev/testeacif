/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fransergiodev.testeacif.dao;

import br.com.fransergiodev.testeacif.domain.GrupoProduto;
import br.com.fransergiodev.testeacif.util.ConnectionFactory;
import br.com.fransergiodev.testeacif.util.DaoUtil;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fransergio-dev
 */
public class GrupoProdutoDao implements DaoGeneric<GrupoProduto, Serializable> {

    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rset = null;

    @Override
    public void save(Object T) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO grupo_produto(descricao,data_cadastro) VALUES(?,?)";
        try {
            this.conn = ConnectionFactory.getConnection();
            GrupoProduto grupoProduto = (GrupoProduto) T;
            //Cria um PreparedStatment, classe usada para executar a query
            this.pstm = conn.prepareStatement(sql);

            //Adicionando valor aos parametros da sql
            this.pstm.setString(1, grupoProduto.getDescricao());
            this.pstm.setDate(2, new Date(grupoProduto.getDataCadastro().getTime()));
            //Executa a sql para inserção dos dados
            this.pstm.execute();

        } finally {
            DaoUtil.closeConn(this.pstm, this.conn);
        }
    }

    @Override
    public void update(Object T) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE grupo_produto SET descricao=?,data_cadastro=? WHERE id=?";
        try {
            this.conn = ConnectionFactory.getConnection();
            GrupoProduto grupoProduto = (GrupoProduto) T;
            //Cria um PreparedStatment, classe usada para executar a query
            this.pstm = conn.prepareStatement(sql);

            //Adicionando valor aos parametros da sql
            this.pstm.setString(1, grupoProduto.getDescricao());
            this.pstm.setDate(2, new Date(grupoProduto.getDataCadastro().getTime()));

            this.pstm.setInt(3, Integer.parseInt(grupoProduto.getId().toString()));
            //Executa a sql para inserção dos dados
            this.pstm.execute();

        } finally {
            DaoUtil.closeConn(this.pstm, this.conn);
        }
    }

    @Override
    public void delete(Object T) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM grupo_produto WHERE id=?";
        try {
            this.conn = ConnectionFactory.getConnection();
            GrupoProduto grupoProduto = (GrupoProduto) T;
            //Cria um PreparedStatment, classe usada para executar a query
            this.pstm = conn.prepareStatement(sql);

            //Adicionando valor aos parametros da sql
            this.pstm.setInt(1, Integer.valueOf(grupoProduto.getId().toString()));

            //Executa a sql para inserção dos dados
            this.pstm.execute();

        } finally {
            DaoUtil.closeConn(this.pstm, this.conn);
        }
    }

    public void log(GrupoProduto grupoProduto) throws SQLException, ClassNotFoundException {
        try {
            this.conn = ConnectionFactory.getConnection();
            CallableStatement cal = conn.prepareCall("{call logGrupoProduto(?,?,?,?)}");
            cal.setInt(1, Integer.parseInt(grupoProduto.getId().toString()));
            cal.setString(2, grupoProduto.getDescricao());
            cal.setDate(3, new Date(grupoProduto.getDataCadastro().getTime()));
            cal.setDate(4, new Date(new java.util.Date().getTime()));
            cal.executeQuery();
        } finally {
            DaoUtil.closeConn(this.pstm, this.conn);
        }

    }

    @Override
    public GrupoProduto getById(Object T) throws SQLException, ClassNotFoundException {
        String sql = "SELECT id,descricao,data_cadastro FROM grupo_produto WHERE id=?";
        try {
            this.conn = ConnectionFactory.getConnection();

            GrupoProduto grupoProduto = (GrupoProduto) T;

            //Cria um PreparedStatment, classe usada para executar a query
            this.pstm = conn.prepareStatement(sql);
            //Adicionando valor aos parametros da sql
            this.pstm.setInt(1, Integer.parseInt(grupoProduto.getId().toString()));

            //Executa a sql para inserção dos dados
            this.rset = pstm.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                grupoProduto = new GrupoProduto();
                grupoProduto.setId(Long.valueOf(rset.getInt("id")));
                grupoProduto.setDescricao(rset.getString("descricao"));
                grupoProduto.setDataCadastro(rset.getDate("data_cadastro"));
            }
            return grupoProduto;
        } finally {
            DaoUtil.closeConn(this.pstm, this.conn);
        }
    }

    @Override
    public List<GrupoProduto> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT id,descricao,data_cadastro FROM grupo_produto";
        List<GrupoProduto> gruposProduto = new ArrayList<>();
        try {
            this.conn = ConnectionFactory.getConnection();
            this.pstm = conn.prepareStatement(sql);

            this.rset = pstm.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {
                GrupoProduto grupoProduto = new GrupoProduto();
                grupoProduto.setId(Long.valueOf(rset.getInt("id")));
                grupoProduto.setDescricao(rset.getString("descricao"));
                grupoProduto.setDataCadastro(rset.getDate("data_cadastro"));

                //Adiciono o contato recuperado, a lista de contatos
                gruposProduto.add(grupoProduto);
            }
        } finally {
            DaoUtil.closeConn(this.pstm, this.conn);
        }
        return gruposProduto;
    }

}
