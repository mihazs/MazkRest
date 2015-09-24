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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
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
@Table(name = "pergunta")

@NamedQueries({
    @NamedQuery(name = "Pergunta.findAll", query = "SELECT p FROM Pergunta p"),
    @NamedQuery(name = "Pergunta.findByIdPergunta", query = "SELECT p FROM Pergunta p WHERE p.idPergunta = :idPergunta"),
    @NamedQuery(name = "Pergunta.findByAtivo", query = "SELECT p FROM Pergunta p WHERE p.ativo = :ativo"),
    @NamedQuery(name = "Pergunta.findByDificuldade", query = "SELECT p FROM Pergunta p WHERE p.dificuldade = :dificuldade"),
    @NamedQuery(name = "Pergunta.findByTempoMedio", query = "SELECT p FROM Pergunta p WHERE p.tempoMedio = :tempoMedio"),
    @NamedQuery(name = "Pergunta.findByDataInserida", query = "SELECT p FROM Pergunta p WHERE p.dataInserida = :dataInserida")})
@JsonIdentityInfo(generator=JSOGGenerator.class)
@Analyzer(impl = BrazilianAnalyzer.class)

public class Pergunta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPergunta")
    private Integer idPergunta;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "enunciado")
    @Field
    private String enunciado;
    @Column(name = "ativo")
    private Boolean ativo;
    @Lob
    @Column(name = "explicacao")
    private byte[] explicacao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dificuldade", updatable = false, insertable = false)
    private Double dificuldade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tempoMedio")
    private double tempoMedio;
    @Column(name = "dataInserida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInserida;
    
    
    @ManyToMany(mappedBy = "perguntaList", fetch = FetchType.EAGER)
    @ContainedIn
    private List<Area> areaList;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pergunta", fetch = FetchType.EAGER)
    private List<Exemplo> exemploList;
    
    
    @OneToMany(mappedBy = "pergunta", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Resposta> respostaList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pergunta", fetch = FetchType.EAGER)
    @IndexedEmbedded
    private List<Alternativa> alternativaList;

    public Pergunta() {
    }

    public Pergunta(Integer idPergunta) {
        this.idPergunta = idPergunta;
    }

    public Pergunta(Integer idPergunta, String enunciado, double tempoMedio) {
        this.idPergunta = idPergunta;
        this.enunciado = enunciado;
        this.tempoMedio = tempoMedio;
    }

    public Integer getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(Integer idPergunta) {
        this.idPergunta = idPergunta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public byte[] getExplicacao() {
        return explicacao;
    }

    public void setExplicacao(byte[] explicacao) {
        this.explicacao = explicacao;
    }

    public Double getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Double dificuldade) {
        this.dificuldade = dificuldade;
    }

    public double getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(double tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    public Date getDataInserida() {
        return dataInserida;
    }

    public void setDataInserida(Date dataInserida) {
        this.dataInserida = dataInserida;
    }

    
    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }


    
    public List<Exemplo> getExemploList() {
        return exemploList;
    }

    public void setExemploList(List<Exemplo> exemploList) {
        this.exemploList = exemploList;
    }

    
    public List<Resposta> getRespostaList() {
        return respostaList;
    }

    public void setRespostaList(List<Resposta> respostaList) {
        this.respostaList = respostaList;
    }

    
    public List<Alternativa> getAlternativaList() {
        return alternativaList;
    }

    public void setAlternativaList(List<Alternativa> alternativaList) {
        this.alternativaList = alternativaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPergunta != null ? idPergunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pergunta)) {
            return false;
        }
        Pergunta other = (Pergunta) object;
        if ((this.idPergunta == null && other.idPergunta != null) || (this.idPergunta != null && !this.idPergunta.equals(other.idPergunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Pergunta[ idPergunta=" + idPergunta + " ]";
    }
    
}
