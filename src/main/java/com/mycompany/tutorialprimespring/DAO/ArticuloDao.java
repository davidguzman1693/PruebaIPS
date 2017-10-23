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
import java.util.Calendar;

/**
 *
 * @author ADMIN
 */
public class ArticuloDao {
    
    private Connection cn;
    private CallableStatement cs;
    private ResultSet rs;
    private PostgresConexion oc;
    private Statement statement = null;
        
    public ArrayList<ArticuloDTO> listarArticulos() throws SQLException{
        ArrayList<ArticuloDTO> articulosDto = new ArrayList<>();
        oc = new PostgresConexion();
        cn = PostgresConexion.conexionBd();
        rs = null;
        cs = null;
        ArticuloDTO articuloDto;
        String sql = "SELECT * FROM articulo";
        System.out.println("Listar articulos");
        cs = cn.prepareCall(sql);
        statement = cn.createStatement();
        rs = statement.executeQuery(sql);
        while(rs.next()){
            articuloDto = new ArticuloDTO();
            articuloDto.setIdarticulo(rs.getInt("idarticulo"));
            articuloDto.setNombre(rs.getString("nombre"));
            articuloDto.setValor(rs.getInt("valor"));
            articulosDto.add(articuloDto);//add(clientesDto);
        }
        cerrarConexiones(cn);
        return articulosDto;
    }
   
    public boolean ingresarArticulo(ArticuloDTO articuloDTO) throws SQLException{
        try{
            oc = new PostgresConexion();
            cn = PostgresConexion.conexionBd();
            rs = null;
            cs = null;
            cn.setAutoCommit(false);
            String sql = "insert into articulo (nombre,valor) values ('"
                    +articuloDTO.getNombre()+"','"
                    +articuloDTO.getValor()+"');";
            statement = cn.createStatement();
            statement.executeUpdate(sql);
            cn.commit();
            cerrarConexiones(cn);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean editarArticulo(ArticuloDTO articuloDto){
        try{
            oc = new PostgresConexion();
            cn = PostgresConexion.conexionBd();
            rs = null;
            cs = null;
            cn.setAutoCommit(false);

            String sql = "update articulo set nombre='"+articuloDto.getNombre()+"', "+
                    "valor='"+articuloDto.getValor()+"' " +
                    "  where idarticulo="+articuloDto.getIdarticulo()+";";
                    //                +sqlDate+;
            System.out.println(sql);
            statement = cn.createStatement();
            statement.executeUpdate(sql);
            cn.commit();
            cerrarConexiones(cn);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean eliminarArticulo(ArticuloDTO articuloDto){
        try{
            oc = new PostgresConexion();
            cn = PostgresConexion.conexionBd(); 
            rs = null;
            cs = null;
            cn.setAutoCommit(false);
            String sql = "delete from articulo" +
                    "  where idarticulo="+articuloDto.getIdarticulo()+";";
                    //                +sqlDate+;
            System.out.println(sql);
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
