package sif.ali.questionnaire1.Model;

import java.io.Serializable;

/**
 * Created by ali on 06/02/2016.
 */
public class Type implements Serializable{
    private String nom;

    public Type(String nom) {
        this.nom = nom;
    }

    /*public Type(){
        this.nom="Mon Problème";
    }*/

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "" + nom ;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}