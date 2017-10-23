/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tutorialprimespring.bean;

import com.mycompany.tutorialprimespring.DAO.ClienteDao;
import com.mycompany.tutorialprimespring.DTO.ClienteDTO;
import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;


/**
 *
 * @author ADMIN
 */
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {
    private static final long serialVersionUID = -2152389656664659476L;
    private List<ClienteDTO> customers;
    private ClienteDao clienteDao;
    private ClienteDTO clienteDto = new ClienteDTO();
    private DataTable dataTableCliente;
    public static final ClienteDTO clienteArticulo = new ClienteDTO();

    public DataTable getDataTableCliente() {
        return dataTableCliente;
    }

    public void setDataTableCliente(DataTable dataTableCliente) {
        this.dataTableCliente = dataTableCliente;
    }

     
    
    public List<ClienteDTO> getCustomers() {
        return customers;
    }

    public ClienteDTO getClienteDto() {
        return clienteDto;
    }

    public void setClienteDto(ClienteDTO clienteDto) {
        this.clienteDto = clienteDto;
    }
    
   
    @PostConstruct
    public void setup()  {
        customers = new ArrayList<>();
        clienteDao = new ClienteDao();
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        
        try {
            clientes = clienteDao.listarClientes();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        customers.addAll(clientes);
    }
    
    public void ingresarCliente() throws SQLException{
        clienteDao = new ClienteDao();
       clienteDao.ingresarCliente(clienteDto);
        
        this.customers.add(clienteDto);
        clienteDto = new ClienteDTO();
    }
    
    public void editarCliente() throws SQLException{
        clienteDao = new ClienteDao();
        clienteDao.editarCliente(clienteDto);
        clienteDto = new ClienteDTO();
    }
    
    public void eliminarCliente() throws SQLException{
        clienteDao = new ClienteDao();
        boolean flag = false;
        clienteDao.eliminarCliente(clienteDto);
        customers.remove(clienteDto);
        clienteDto = new ClienteDTO();
    }
    
}
