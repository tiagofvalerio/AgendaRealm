package br.edu.ifspsaocarlos.agenda.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Contato extends RealmObject implements Serializable{

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    private long id;

    private String nome;
    private String fone;
    private String email;
    private String foneAdicional;
    private String dataAniversario;

    public Contato()
    {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getFone() {
        return fone;
    }
    public void setFone(String fone) {
        this.fone = fone;
    }
    public String getFoneAdicional() {
        return foneAdicional;
    }
    public void setFoneAdicional(String foneAdicional) {
        this.foneAdicional = foneAdicional;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDataAniversario() {
        return dataAniversario;
    }
    public void setDataAniversario(String dataAniversario) {
        this.dataAniversario = dataAniversario;
    }
}

