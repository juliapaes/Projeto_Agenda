package com.turma2.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ContatoFamiliar extends Contato {

    @Column(name="grauparentesco", nullable=true)
    private String grauParentesco;

    public ContatoFamiliar() {}

    public ContatoFamiliar(Integer id, String nome,
                           String telefone, String grauParentesco) {
        this.setId(id);
        this.setNome(nome);
        this.setTelefone(telefone);
        this.grauParentesco = grauParentesco;
    }

    public String getGrauParentesco() {
        return grauParentesco;
    }

    public void setGrauParentesco(String grauParentesco) {
        this.grauParentesco = grauParentesco;
    }

    
    
}
