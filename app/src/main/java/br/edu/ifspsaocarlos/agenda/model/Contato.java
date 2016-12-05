package br.edu.ifspsaocarlos.agenda.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Contato extends RealmObject implements Parcelable {

//    private static final long serialVersionUID = 1L;

    @PrimaryKey
    private long id;

    private String nome;
    private String fone;
    private String email;
    private String foneAdicional;
    private String dataAniversario;

    public Contato() {
    }

    public Contato(Parcel parcel) {
        this.id = parcel.readLong();
        this.nome = parcel.readString();
        this.fone = parcel.readString();
        this.email = parcel.readString();
        this.foneAdicional = parcel.readString();
        this.dataAniversario = parcel.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(nome);
        parcel.writeString(fone);
        parcel.writeString(email);
        parcel.writeString(foneAdicional);
        parcel.writeString(dataAniversario);
    }

    public static final Parcelable.Creator<Contato> CREATOR = new Parcelable.Creator<Contato>(){
        public Contato createFromParcel(Parcel in){
            return new Contato(in);
        }

        public Contato[] newArray(int size){
            return new Contato[size];
        }
    };
}

