/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import java.io.Serializable;
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
import javax.validation.constraints.Size;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;


/**
 *
 * @author Mihael Zamin
 */
@Entity
@Indexed
@Table(name = "alternativa")
@NamedQueries({
    @NamedQuery(name = "Alternativa.findAll", query = "SELECT a FROM Alternativa a"),
    @NamedQuery(name = "Alternativa.findByIdAlternativa", query = "SELECT a FROM Alternativa a WHERE a.idAlternativa = :idAlternativa"),
    @NamedQuery(name = "Alternativa.findByDescricao", query = "SELECT a FROM Alternativa a WHERE a.descricao = :descricao"),
@NamedQuery(name = "Alternativa.findByPergunta", query = "SELECT a FROM Alternativa a WHERE a.pergunta = :pergunta")})
@JsonIdentityInfo(generator=JSOGGenerator.class)
@Analyzer(impl = BrazilianAnalyzer.class)
public class Alternativa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAlternativa")
    private Integer idAlternativa;
    @Size(max = 450)
    @Column(name = "descricao")
    @Field
    private String descricao;
    @Column(name = "correta")
    private Boolean correta;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alternativa", fetch = FetchType.EAGER)
    private List<Resposta> respostaList;
    
    
    @JoinColumn(name = "Pergunta_idPergunta", referencedColumnName = "idPergunta")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @ContainedIn
    private Pergunta pergunta;

    public Alternativa() {
    }

    public Alternativa(Integer idAlternativa) {
        this.idAlternativa = idAlternativa;
    }

    public Integer getIdAlternativa() {
        return idAlternativa;
    }

    public void setIdAlternativa(Integer idAlternativa) {
        this.idAlternativa = idAlternativa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public List<Resposta> getRespostaList() {
        return respostaList;
    }

    public void setRespostaList(List<Resposta> respostaList) {
        this.respostaList = respostaList;
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
        hash += (idAlternativa != null ? idAlternativa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alternativa)) {
            return false;
        }
        Alternativa other = (Alternativa) object;
        if ((this.idAlternativa == null && other.idAlternativa != null) || (this.idAlternativa != null && !this.idAlternativa.equals(other.idAlternativa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Alternativa[ idAlternativa=" + idAlternativa + " ]";
    }

    public Boolean getCorreta() {
        return correta;
    }

    public void setCorreta(Boolean correta) {
        this.correta = correta;
    }
    
}
