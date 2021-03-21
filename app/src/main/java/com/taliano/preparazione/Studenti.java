package com.taliano.preparazione;

import java.io.Serializable;

public class Studenti implements Serializable {
    private Integer ID;
    private String nome;
    private String cognome;
    private String sesso;
    private String Citta;
    private Integer imgI;
    private Integer img;
    private Integer eta;

    public Studenti() {
        img = -1;
        ID = 0;
    }

    public Studenti(Integer id, String nome, String cognome, String sesso, String citta, Integer imgI, Integer eta) {
        ID = id;
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        Citta = citta;
        this.imgI = imgI;
        this.eta = eta;
        this.img = -1;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getCitta() {
        return Citta;
    }

    public void setCitta(String citta) {
        Citta = citta;
    }

    public Integer getImgI() {
        return imgI;
    }

    public void setImgI(Integer imgI) {
        this.imgI = imgI;
    }

    public Integer getEta() {
        return eta;
    }

    public void setEta(Integer eta) {
        this.eta = eta;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }
}
