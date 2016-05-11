package sif.ali.questionnaire1.Model;

/**
 * Created by ali on 06/02/2016.
 */
public class Reseau {
    private String appAddr;
    private String email;
    private String passeWord;

    public Reseau(String appAddr, String email, String passeWord) {
        this.appAddr = appAddr;
        this.email = email;
        this.passeWord = passeWord;
    }

    /*public Reseau() {
        this.appAddr = "";
        this.email = "";
        this.passeWord = "";
    }*/


    public void sendProb(Probleme p){}
    public void connection(){}
    public void deconection(){}



}
