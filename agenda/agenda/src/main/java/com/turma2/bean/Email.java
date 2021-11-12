package com.turma2.bean;

public class Email {
    
    private String emailPrincipal;
    private String emailSecundario;

    public Email() {}

    public Email(String auxEmailPrincipal, String auxEmailSecundario) {
        this.emailPrincipal = auxEmailPrincipal;
        this.emailSecundario = auxEmailSecundario;
    }

    public void setEmailPrincipal(String auxEmailPrincipal) {
        this.emailPrincipal = auxEmailPrincipal;
    }

    public void setEmailSecundario(String auxEmailSecundario) {
        this.emailSecundario = auxEmailSecundario;
    }

    public String getEmailPrincipal() {
        return this.emailPrincipal;
    }

    public String getEmailSecundario() {
        return this.emailSecundario;
    }

    public String getEmails() {
        return this.emailPrincipal + " - " + this.emailSecundario;
    }

}
