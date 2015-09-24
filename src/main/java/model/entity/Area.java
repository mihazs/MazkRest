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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "area")

@NamedQueries({
    @NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a"),
    @NamedQuery(name = "Area.findByIdArea", query = "SELECT a FROM Area a WHERE a.idArea = :idArea"),
    @NamedQuery(name = "Area.findByNome", query = "SELECT a FROM Area a WHERE a.nome = :nome")})
@JsonIdentityInfo(generator=JSOGGenerator.class)
@Analyzer(impl = BrazilianAnalyzer.class)
public class Area implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idArea")
    private Integer idArea;
    @Size(max = 100)
    @Column(name = "nome")
    @Field
    private String nome;
    
    
    @JoinTable(name = "pergunta_has_area", joinColumns = {
        @JoinColumn(name = "Area_idArea", referencedColumnName = "idArea")}, inverseJoinColumns = {
        @JoinColumn(name = "Pergunta_idPergunta", referencedColumnName = "idPergunta")})
    @ManyToMany(fetch = FetchType.EAGER)
    @IndexedEmbedded
    private List<Pergunta> perguntaList;
    
    //Áreas filhos
    @OneToMany(mappedBy = "area", fetch = FetchType.EAGER)
    @ContainedIn
    private List<Area> areaList;
    
    //Área pai
    @JoinColumn(name = "Area_idArea", referencedColumnName = "idArea")
    @ManyToOne(fetch = FetchType.EAGER)
    @IndexedEmbedded(depth = 1)
    private Area area;

    public Area() {
    }

    public Area(Integer idArea) {
        this.idArea = idArea;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public List<Pergunta> getPerguntaList() {
        return perguntaList;
    }

    public void setPerguntaList(List<Pergunta> perguntaList) {
        this.perguntaList = perguntaList;
    }

    
    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArea != null ? idArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.idArea == null && other.idArea != null) || (this.idArea != null && !this.idArea.equals(other.idArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Area[ idArea=" + idArea + " ]";
    }
    
}
