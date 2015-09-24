/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;



/**
 *
 * @author Mihael Zamin
 */
@Entity
@Table(name = "resposta")
@NamedQueries({
    @NamedQuery(name = "Resposta.findAll", query = "SELECT r FROM Resposta r"),
    @NamedQuery(name = "Resposta.findByTentativa", query = "SELECT r FROM Resposta r WHERE r.tentativa = :tentativa"),
    @NamedQuery(name = "Resposta.findByIdResposta", query = "SELECT r FROM Resposta r WHERE r.idResposta = :idResposta"),
    @NamedQuery(name = "Resposta.findByData", query = "SELECT r FROM Resposta r WHERE r.data = :data"),
    @NamedQuery(name = "Resposta.findByTempoDecorrido", query = "SELECT r FROM Resposta r WHERE r.tempoDecorrido = :tempoDecorrido")})
@JsonIdentityInfo(generator=JSOGGenerator.class)
//@JsonIgnoreProperties({"@ref"})
public class Resposta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idResposta")
    private Integer idResposta;
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tempoDecorrido")
    private int tempoDecorrido;
    
    @JoinColumn(name = "Alternativa_idAlternativa", referencedColumnName = "idAlternativa")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Alternativa alternativa;
    
    
    @JoinColumn(name = "Pergunta_idPergunta", referencedColumnName = "idPergunta")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pergunta pergunta;
    
    
    @JoinColumn(name = "Tentativa_idTentativa", referencedColumnName = "idTentativa")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tentativa tentativa;

    public Resposta() {
    }

    public Resposta(Integer idResposta) {
        this.idResposta = idResposta;
    }

    public Resposta(Integer idResposta, int tempoDecorrido) {
        this.idResposta = idResposta;
        this.tempoDecorrido = tempoDecorrido;
    }

    public Integer getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(Integer idResposta) {
        this.idResposta = idResposta;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getTempoDecorrido() {
        return tempoDecorrido;
    }

    public void setTempoDecorrido(int tempoDecorrido) {
        this.tempoDecorrido = tempoDecorrido;
    }

    public Alternativa getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(Alternativa alternativa) {
        this.alternativa = alternativa;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public Tentativa getTentativa() {
        return tentativa;
    }

    public void setTentativa(Tentativa tentativa) {
        this.tentativa = tentativa;
        if(tentativa.getRespostaList() == null)
            tentativa.setRespostaList(new ArrayList<Resposta>());
        if(tentativa.getRespostaList().indexOf(this) == -1)
        tentativa.getRespostaList().add(this);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResposta != null ? idResposta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resposta)) {
            return false;
        }
        Resposta other = (Resposta) object;
        if ((this.idResposta == null && other.idResposta != null) || (this.idResposta != null && !this.idResposta.equals(other.idResposta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Resposta[ idResposta=" + idResposta + " ]";
    }
    
}
