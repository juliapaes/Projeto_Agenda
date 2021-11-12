package com.turma2.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbl_contato")
public abstract class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="nome", nullable=false)
    private String nome;
    private String apelido;
    private String endereco;
    @Column(name="telefone", nullable=false)
    private String telefone;
    @Embedded
    private Aniversario aniversario;
    @Embedded
    private Email email;
    @OneToMany(mappedBy = "contato")
    private List<RedeSocial> redesSociais = new ArrayList<RedeSocial>();

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setAniversario(Aniversario aniversario) {
        this.aniversario = aniversario;
    }
    public void setEmail(Email email) {
        this.email = email;
    }

    public String getApelido() {
        return this.apelido;
    }
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
    public String getEndereco() {
        return this.endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getTelefone() {
        return this.telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public Aniversario getAniversario() {
        return this.aniversario;
    }
    public Email getEmail() {
        return this.email;
    }

    public void setRedesSociais(List<RedeSocial> auxRedesSociais) {
        this.redesSociais = auxRedesSociais;
    }

    public List<RedeSocial> getRedesSociais() {
        return this.redesSociais;
    }

}
