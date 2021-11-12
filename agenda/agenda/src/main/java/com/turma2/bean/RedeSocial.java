package com.turma2.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="tbl_redesocial")
public class RedeSocial {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name="nomerede", nullable=false)
    private String rede;
    @Column(name="nomeperfil", nullable=false)
    private String perfil;
    @ManyToOne
    @JoinColumn(name="contato", nullable = false)
    private Contato contato;

    public RedeSocial() {}

    public RedeSocial (Integer auxId, String auxRede, String auxPerfil, Contato auxContato) {
        this.id = auxId;
        this.rede = auxRede;
        this.perfil = auxPerfil;
        this.contato = auxContato;
    }

    public void setId(Integer auxId) {
        this.id = auxId;
    }

    public void setRede(String auxRede) {
        this.rede = auxRede;
    }

    public void setPerfil(String auxPerfil) {
        this.perfil = auxPerfil;
    }

    public void setContato(Contato auxContato) {
        this.contato = auxContato;
    }

    public Integer getId() {
        return this.id;
    }

    public String getRede() {
        return this.rede;
    }

    public String getPerfil() {
        return this.perfil;
    }

    public Contato getContato() {
        return this.contato;
    }

    public String getPerfilFormatado() {
        return this.rede + ": " + this.perfil;
    }
    
}
