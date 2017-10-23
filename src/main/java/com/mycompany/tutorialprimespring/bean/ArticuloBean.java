/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tutorialprimespring.bean;

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
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author ADMIN
 */
@ManagedBean
@ViewScoped
public class ArticuloBean implements Serializable {
    private static final long serialVersionUID = -2152389656664659476L;
    private List<ArticuloDTO> articulos;
    private ArticuloDao articuloDao;
    private ArticuloDTO aritucloDto = new ArticuloDTO();

    public List<ArticuloDTO> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<ArticuloDTO> articulos) {
        this.articulos = articulos;
    }

    public ArticuloDao getArticuloDao() {
        return articuloDao;
    }

    public void setArticuloDao(ArticuloDao articuloDao) {
        this.articuloDao = articuloDao;
    }

    public ArticuloDTO getAritucloDto() {
        return aritucloDto;
    }

    public void setAritucloDto(ArticuloDTO aritucloDto) {
        this.aritucloDto = aritucloDto;
    }
    
   @PostConstruct
    public void setup()  {
        articulos = new ArrayList<>();
        articuloDao = new ArticuloDao();
        ArrayList<ArticuloDTO> articulosdto = new ArrayList<>();
        
        try {
            articulosdto = articuloDao.listarArticulos();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        articulos.addAll(articulosdto);
    }
    
    public void ingresarArticulo() throws SQLException{
        articuloDao = new ArticuloDao();
        articuloDao.ingresarArticulo(aritucloDto);
        aritucloDto = new ArticuloDTO();
    }
    
    public void editarArticulo() throws SQLException{
        articuloDao = new ArticuloDao();
        articuloDao.editarArticulo(aritucloDto);
        aritucloDto = new ArticuloDTO();
    }
    
    public void eliminarArticulo() throws SQLException{
        articuloDao = new ArticuloDao();
        boolean flag = false;
        articuloDao.eliminarArticulo(aritucloDto);
        articulos.remove(aritucloDto);
        aritucloDto = new ArticuloDTO();
    }
    
}
