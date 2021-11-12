package com.turma2.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ContatoEscolar extends Contato {

    @Column(name="ehcolega", nullable=true)
    private boolean ehColega;
    @Column(name="ehProfessor", nullable=true)
    private boolean ehProfessor;
    @Column(name="ehAmigo", nullable = true)
    private boolean ehAmigo;

    public ContatoEscolar() {}

    public ContatoEscolar(Integer id, String nome, String telefone,
                    boolean ehColega, boolean ehProfessor, boolean ehAmigo) {
        this.setId(id);
        this.setNome(nome);
        this.setTelefone(telefone);
        this.ehColega = ehColega;
        this.ehProfessor = ehProfessor;
        this.ehAmigo = ehAmigo;
    }

    public boolean getEhColega() {
        return ehColega;
    }
    public void setEhColega(boolean ehColega) {
        this.ehColega = ehColega;
    }
    public boolean getEhProfessor() {
        return ehProfessor;
    }
    public void setEhProfessor(boolean ehProfessor) {
        this.ehProfessor = ehProfessor;
    }
    public boolean getEhAmigo() {
        return ehAmigo;
    }
    public void setEhAmigo(boolean ehAmigo) {
        this.ehAmigo = ehAmigo;
    }

    
    
}
