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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author Mihael Zamin
 */
@Entity
@Table(name = "usuario")

@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE u.nome = :nome"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email LIKE :email"),
    @NamedQuery(name = "Usuario.countByEmail", query = "SELECT count(u) FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha"),
    @NamedQuery(name = "Usuario.findByExperiencia", query = "SELECT u FROM Usuario u WHERE u.experiencia = :experiencia"),
    @NamedQuery(name = "Usuario.findByDataDeCadastro", query = "SELECT u FROM Usuario u WHERE u.dataDeCadastro = :dataDeCadastro"),
    @NamedQuery(name = "Usuario.findByDataDeNascimento", query = "SELECT u FROM Usuario u WHERE u.dataDeNascimento = :dataDeNascimento")})
@JsonIdentityInfo(generator=JSOGGenerator.class)
//@JsonIgnoreProperties({"@ref"})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Size(max = 45)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "senha")
    private String senha;
    
    @Column(name = "experiencia", updatable = false, insertable = false)
    private Double experiencia;
    @Column(name = "dataDeCadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDeCadastro;
    @Lob
    @Column(name = "avatar")
    private byte[] avatar;
    @Column(name = "dataDeNascimento")
    @Temporal(TemporalType.DATE)
    private Date dataDeNascimento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Acesso> acessoList;
    
    @JoinColumn(name = "Tipo_idTipo", referencedColumnName = "idTipo")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tipo tipo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Tentativa> tentativaList;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String email, String senha) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.senha = senha;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Double getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Double experiencia) {
        this.experiencia = experiencia;
    }

    public Date getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(Date dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    
    public List<Acesso> getAcessoList() {
        return acessoList;
    }

    public void setAcessoList(List<Acesso> acessoList) {
        this.acessoList = acessoList;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    
    public List<Tentativa> getTentativaList() {
        return tentativaList;
    }

    public void setTentativaList(List<Tentativa> tentativaList) {
        this.tentativaList = tentativaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entity.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
