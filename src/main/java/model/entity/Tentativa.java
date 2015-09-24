/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



/**
 *
 * @author Mihael Zamin
 */

@Entity
@Table(name = "tentativa")

@NamedQueries({
    @NamedQuery(name = "Tentativa.findAll", query = "SELECT t FROM Tentativa t"),
    @NamedQuery(name = "Tentativa.findByUsuario", query = "SELECT t FROM Tentativa t WHERE t.usuario = :usuario"),
    
    @NamedQuery(name = "Tentativa.findByIdTentativa", query = "SELECT t FROM Tentativa t WHERE t.idTentativa = :idTentativa"),
    @NamedQuery(name = "Tentativa.findByDesempenho", query = "SELECT t FROM Tentativa t WHERE t.desempenho = :desempenho"),
    @NamedQuery(name = "Tentativa.findByData", query = "SELECT t FROM Tentativa t WHERE t.data = :data"),
    @NamedQuery(name = "Tentativa.findByTempoTotal", query = "SELECT t FROM Tentativa t WHERE t.tempoTotal = :tempoTotal")})
@JsonIdentityInfo(generator=JSOGGenerator.class)
//@JsonIgnoreProperties({"@ref"})
public class Tentativa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTentativa")
    private Integer idTentativa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "desempenho", updatable = false, insertable = false)
    private Double desempenho;
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Column(name = "tempoTotal")
    private Integer tempoTotal;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tentativa", fetch = FetchType.EAGER)
    private List<Resposta> respostaList;
    
    @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuario;

    public Tentativa() {
    }
    public Tentativa(Usuario usuarioidUsuario)
    {
        this.usuario = usuarioidUsuario;
    }

    public Tentativa(Integer idTentativa) {
        this.idTentativa = idTentativa;
    }

    public Integer getIdTentativa() {
        return idTentativa;
    }

    public void setIdTentativa(Integer idTentativa) {
        this.idTentativa = idTentativa;
    }

    public Double getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(Double desempenho) {
        this.desempenho = desempenho;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getTempoTotal() {
        return tempoTotal;
    }

    public void setTempoTotal(Integer tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    
    public List<Resposta> getRespostaList() {
        return respostaList;
    }

    public void setRespostaList(List<Resposta> respostaList) {
        this.respostaList = respostaList;
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
        hash += (idTentativa != null ? idTentativa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tentativa)) {
            return false;
        }
        Tentativa other = (Tentativa) object;
        if ((this.idTentativa == null && other.idTentativa != null) || (this.idTentativa != null && !this.idTentativa.equals(other.idTentativa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Tentativa[ idTentativa=" + idTentativa + " ]";
    }
    
}
