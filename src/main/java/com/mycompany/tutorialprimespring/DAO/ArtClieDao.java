/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tutorialprimespring.DAO;

import com.mycompany.tutorialprimespring.DTO.ArticuloDTO;
import com.mycompany.tutorialprimespring.DTO.ClienteDTO;
import com.mycompany.tutorialprimespring.sql.PostgresConexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class ArtClieDao {
    private Connection cn;
    private CallableStatement cs;
    private ResultSet rs;
    private PostgresConexion oc;
    private Statement statement = null;
    
    public ArrayList<ArticuloDTO> listarArticulosCliente(ClienteDTO clienteDto) throws SQLException{
        ArrayList<ArticuloDTO> articulosDto = new ArrayList<>();
        oc = new PostgresConexion();
        cn = PostgresConexion.conexionBd();
        rs = null;
        cs = null;
        ArticuloDTO articuloDto;
        
        String sql = "SELECT * " +
        "FROM articulo a " +
        "INNER JOIN clie_art ca " +
        "ON a.idarticulo = ca.idarticulo " +
        "INNER JOIN cliente c " +
        "ON ca.idcliente = c.idcliente " +
        "WHERE c.idcliente = "+17+";";
        System.out.println(sql);
        cs = cn.prepareCall(sql);
        statement = cn.createStatement();
        rs = statement.executeQuery(sql);
        while(rs.next()){
            articuloDto = new ArticuloDTO();
            articuloDto.setIdarticulo(rs.getInt("idarticulo"));
            articuloDto.setNombre(rs.getString("nombre"));
            articuloDto.setValor(rs.getInt("valor"));
            
        }
        cerrarConexiones(cn);
        return articulosDto;
    }
    
     public ArrayList<ArticuloDTO> listarArticulosClienteLibres(ClienteDTO clienteDto) throws SQLException{
        ArrayList<ArticuloDTO> articulosDto = new ArrayList<>();
        oc = new PostgresConexion();
        cn = PostgresConexion.conexionBd();
        rs = null;
        cs = null;
        ArticuloDTO articuloDto;
        
        String sql = "SELECT * " +
        "FROM cliente c " +
        "INNER JOIN clie_art ca " +
        "ON c.idcliente != ca.idcliente " +
        "INNER JOIN articulo a " +
        "ON ca.idarticulo = a.idarticulo " +
        "WHERE c.idcliente = "+17+";";
        System.out.println(sql);
        cs = cn.prepareCall(sql);
        statement = cn.createStatement();
        rs = statement.executeQuery(sql);
        while(rs.next()){
            articuloDto = new ArticuloDTO();
            articuloDto.setIdarticulo(rs.getInt("idarticulo"));
            articuloDto.setNombre(rs.getString("nombre"));
            articuloDto.setValor(rs.getInt("valor"));
            
        }
        cerrarConexiones(cn);
        return articulosDto;
    }
    
    public boolean ingresarArtClie(ArticuloDTO articuloDto, ClienteDTO clienteDto){
        try{
            oc = new PostgresConexion();
            cn = PostgresConexion.conexionBd();
            rs = null;
            cs = null;
            cn.setAutoCommit(false);

            String sql = "insert into clie_art (idcliente,idarticulo) values ('"
                    +articuloDto.getIdarticulo()+"','"
                    +clienteDto.getIdcliente()+"');";
            statement = cn.createStatement();
            statement.executeUpdate(sql);
            cn.commit();
            cerrarConexiones(cn);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    private void cerrarConexiones(Connection c) throws SQLException{
        if(c!=null){
            rs.close();
            cs.close();
            c.close();
        }
    }
    
}
