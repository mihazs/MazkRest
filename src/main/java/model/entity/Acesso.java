/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


/**
 *
 * @author Mihael Zamin
 */
@Entity
@Table(name = "acesso")
@NamedQueries({
    @NamedQuery(name = "Acesso.findAll", query = "SELECT a FROM Acesso a"),
    @NamedQuery(name = "Acesso.findByIdAcesso", query = "SELECT a FROM Acesso a WHERE a.idAcesso = :idAcesso"),
    @NamedQuery(name = "Acesso.findByDataDeEntrada", query = "SELECT a FROM Acesso a WHERE a.dataDeEntrada = :dataDeEntrada")})
@JsonIdentityInfo(generator=JSOGGenerator.class)
//@JsonIgnoreProperties({"@ref"})
public class Acesso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAcesso")
    private Integer idAcesso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataDeEntrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDeEntrada;
    
    
    @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuario;

    public Acesso() {
    }

    public Acesso(Integer idAcesso) {
        this.idAcesso = idAcesso;
    }

    public Acesso(Integer idAcesso, Date dataDeEntrada) {
        this.idAcesso = idAcesso;
        this.dataDeEntrada = dataDeEntrada;
    }

    public Integer getIdAcesso() {
        return idAcesso;
    }

    public void setIdAcesso(Integer idAcesso) {
        this.idAcesso = idAcesso;
    }

    public Date getDataDeEntrada() {
        return dataDeEntrada;
    }

    public void setDataDeEntrada(Date dataDeEntrada) {
        this.dataDeEntrada = dataDeEntrada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcesso != null ? idAcesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acesso)) {
            return false;
        }
        Acesso other = (Acesso) object;
        if ((this.idAcesso == null && other.idAcesso != null) || (this.idAcesso != null && !this.idAcesso.equals(other.idAcesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Acesso[ idAcesso=" + idAcesso + " ]";
    }
    
}
