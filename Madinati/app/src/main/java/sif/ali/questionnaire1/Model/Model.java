package sif.ali.questionnaire1.Model;

import java.io.File;
import java.io.Serializable;
import java.util.Observable;

/**
 * Created by ali on 06/02/2016.
 */
public class Model extends Observable implements Serializable {// a chercher pk

   // private static final long serialVersionUID = 1L;
    //Pour une sous claase  Probleme
    private Type typeProbleme;
    private double longitude, latitude;
    private String description;
    private String image;
    private String path_image;


    public Model(Type typeProbleme, double longitude, double latitude, String description,String image, String path_image) {
        this.typeProbleme = typeProbleme;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
        this.path_image=path_image;
        this.image=image;


    }
    public Model(String typeProbleme, double longitude, double latitude, String description) {
        this(new Type(typeProbleme), longitude, latitude, description,"","");
    }

    public Model() {
        this.typeProbleme = new Type("");
        this.longitude = 0.0;
        this.latitude = 0.0;
        this.description = "";
        this.image="";
    }

    public Type getTypeProbleme() {
        return typeProbleme;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getDescription() {
        return description;
    }

    public void setTypeProbleme(Type typeProbleme) {
        this.typeProbleme = typeProbleme;
    }

    public void setTypeProbleme(String typeProbleme){
        this.typeProbleme.setNom(typeProbleme);
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getPath_image() {
        return path_image;
    }

    public void setPath_image(String path_image) {
        this.path_image = path_image;
    }

    @Override
    public String toString() {
        return "Model{" +
                "\ntypeProbleme='" + typeProbleme + '\'' +
                "\nlongitude=" + longitude +
                "\nlatitude=" + latitude +
                "\ndescription='" + description + '\'' +
                '}';
    }

}

