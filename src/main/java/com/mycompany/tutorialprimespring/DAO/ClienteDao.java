/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tutorialprimespring.DAO;

import com.mycompany.tutorialprimespring.DTO.ClienteDTO;
import com.mycompany.tutorialprimespring.sql.PostgresConexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import javax.sql.DataSource;

/**
 *
 * @author ADMIN
 */
public class ClienteDao {
    private Connection cn;
    private CallableStatement cs;
    private ResultSet rs;
    private PostgresConexion oc;
    private Statement statement = null;
        
    public ArrayList<ClienteDTO> listarClientes() throws SQLException{
        ArrayList<ClienteDTO> clientesDto = new ArrayList<>();
        oc = new PostgresConexion();
        cn = PostgresConexion.conexionBd();
        rs = null;
        cs = null;
        ClienteDTO clienteDto;
        String sql = "SELECT * FROM cliente";
        System.out.println("Listar clientes");
        cs = cn.prepareCall(sql);
        statement = cn.createStatement();
        rs = statement.executeQuery(sql);
        while(rs.next()){
            clienteDto = new ClienteDTO();
            clienteDto.setIdcliente(rs.getInt("idcliente"));
            clienteDto.setNombre(rs.getString("nombre"));
            clienteDto.setApellido(rs.getString("apellido"));
            clienteDto.setEdad(rs.getInt("edad"));
            clienteDto.setIdentificacion(rs.getString("identificacion"));	
            clienteDto.setFecnac(rs.getDate("fecnac"));	
            clientesDto.add(clienteDto);//add(clientesDto);
        }
        cerrarConexiones(cn);
        return clientesDto;
    }
   
    public void ingresarCliente(ClienteDTO clienteDto) throws SQLException{
        try{
            oc = new PostgresConexion();
            cn = PostgresConexion.conexionBd();
            rs = null;
            cs = null;
            cn.setAutoCommit(false);

            String sql = "insert into cliente (nombre,apellido,edad,identificacion) values ('"
                    +clienteDto.getNombre()+"','"
                    +clienteDto.getApellido()+"','"
                    +clienteDto.getEdad()+"','"
                    +clienteDto.getIdentificacion()+"');";
            statement = cn.createStatement();
            statement.executeUpdate(sql);
            cn.commit();
            
            cerrarConexiones(cn);
        }
        
        catch(Exception e){
                
            }
    }
    
    public boolean editarCliente(ClienteDTO clienteDto){
        try{
            oc = new PostgresConexion();
            cn = PostgresConexion.conexionBd();
            rs = null;
            cs = null;
            cn.setAutoCommit(false);

            java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            String sql = "update cliente set nombre='"+clienteDto.getNombre()+"', "
                    +"apellido='"+clienteDto.getApellido()+"'"+", "
                    +"edad='"+clienteDto.getEdad()+"', "+
                    "identificacion='"+clienteDto.getIdentificacion()+"' " +
                    "  where idcliente="+clienteDto.getIdcliente()+";";
                    //                +sqlDate+;
            statement = cn.createStatement();
            statement.executeUpdate(sql);
            cn.commit();
            cerrarConexiones(cn);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean eliminarCliente(ClienteDTO clienteDto){
        try{
            System.out.println("Identificacion en eliminar: "+clienteDto.getIdcliente());
            oc = new PostgresConexion();
            cn = PostgresConexion.conexionBd();
            rs = null;
            cs = null;
            cn.setAutoCommit(false);
            String sql = "delete from cliente" +
                    "  where idcliente="+clienteDto.getIdcliente()+";";
                    //                +sqlDate+;
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
