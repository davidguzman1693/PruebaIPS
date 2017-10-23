/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tutorialprimespring.bean;

import com.mycompany.tutorialprimespring.DAO.ArtClieDao;
import com.mycompany.tutorialprimespring.DAO.ArticuloDao;
import com.mycompany.tutorialprimespring.DAO.ClienteDao;
import com.mycompany.tutorialprimespring.DTO.ArticuloDTO;
import com.mycompany.tutorialprimespring.DTO.ClienteDTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ADMIN
 */
@ManagedBean
@ViewScoped
public class ArtClieBean implements Serializable {
    private static final long serialVersionUID = -2152389656664659476L;
    private List<ArticuloDTO> articulosClie;
    private List<ArticuloDTO> articulosLibres;
    private ArticuloDao articuloDao;
    private ArtClieDao artclieDao;
    private ClienteDTO clienteDto = new ClienteDTO();
    private ArticuloDTO articuloDto = new ArticuloDTO();
    
        
    @PostConstruct
    public void setup()  {
        ArrayList<ArticuloDTO> articulosdto = new ArrayList<>();
        ArrayList<ArticuloDTO> articuloslibre = new ArrayList<>();
        articulosClie = new ArrayList<>();
        articulosLibres = new ArrayList<>();
        artclieDao = new ArtClieDao();
        articuloDao = new ArticuloDao();
        System.out.println("Cliente "+clienteDto.getNombre());
        try {
            articulosdto = artclieDao.listarArticulosClienteLibres(clienteDto);
            articuloslibre = artclieDao.listarArticulosCliente(clienteDto);
            
            //articuloslibre = articuloDao.listarArticulos();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        articulosLibres.addAll(articulosdto);
        articulosClie.addAll(articuloslibre);
        
     }

    public ArticuloDTO getArticuloDto() {
        return articuloDto;
    }

    public void setArticuloDto(ArticuloDTO articuloDto) {
        this.articuloDto = articuloDto;
    }
    
    
    
    public ClienteDTO getClienteDto() {
        return clienteDto;
    }

    public void setClienteDto(ClienteDTO clienteDto) {
        this.clienteDto = clienteDto;
    }
    
    public List<ArticuloDTO> getArticulosClie() {
        return articulosClie;
    }

    public void setArticulosClie(List<ArticuloDTO> articulosClie) {
        this.articulosClie = articulosClie;
    }

    public List<ArticuloDTO> getArticulosLibres() {
        return articulosLibres;
    }

    public void setArticulosLibres(List<ArticuloDTO> articulosLibres) {
        this.articulosLibres = articulosLibres;
    }

    public ArticuloDao getArticuloDao() {
        return articuloDao;
    }

    public void setArticuloDao(ArticuloDao articuloDao) {
        this.articuloDao = articuloDao;
    }
    
    public String navCliente(){
        return "clientes.xhtml";
    }
    
    public void agregarArticuloACliente(){
        artclieDao = new ArtClieDao();
        artclieDao.ingresarArtClie(articuloDto, clienteDto);
        articuloDto = new ArticuloDTO();
    }
    
    
}
