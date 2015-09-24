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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;



/**
 *
 * @author Mihael Zamin
 */
@Entity
@Table(name = "exemplo")
@NamedQueries({
    @NamedQuery(name = "Exemplo.findAll", query = "SELECT e FROM Exemplo e"),
    @NamedQuery(name = "Exemplo.findByIdExemplo", query = "SELECT e FROM Exemplo e WHERE e.idExemplo = :idExemplo")})
@JsonIdentityInfo(generator=JSOGGenerator.class)
//@JsonIgnoreProperties({"@ref"})
public class Exemplo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idExemplo")
    private Integer idExemplo;
    @Lob
    @Column(name = "conteudo")
    private byte[] conteudo;
    
    
    @JoinColumn(name = "Pergunta_idPergunta", referencedColumnName = "idPergunta")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pergunta pergunta;

    public Exemplo() {
    }

    public Exemplo(Integer idExemplo) {
        this.idExemplo = idExemplo;
    }

    public Integer getIdExemplo() {
        return idExemplo;
    }

    public void setIdExemplo(Integer idExemplo) {
        this.idExemplo = idExemplo;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExemplo != null ? idExemplo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exemplo)) {
            return false;
        }
        Exemplo other = (Exemplo) object;
        if ((this.idExemplo == null && other.idExemplo != null) || (this.idExemplo != null && !this.idExemplo.equals(other.idExemplo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Exemplo[ idExemplo=" + idExemplo + " ]";
    }
    
}
