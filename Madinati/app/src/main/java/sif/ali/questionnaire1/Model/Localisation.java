package sif.ali.questionnaire1.Model;

/**
 * Created by ali on 06/02/2016.
 */
public class Localisation {
    private double loguitude;
    private double latitude;

    public Localisation(double loguitude, double latitude) {
        this.loguitude = loguitude;
        this.latitude = latitude;
    }

    public Localisation() {
        this.loguitude = 0.0;
        this.latitude = 0.0;
    }


    @Override
    public String toString() {
        return "Localisation{" +
                "loguitude=" + loguitude +
                ", latitude=" + latitude +
                '}';
    }

    public double getLoguitude() {
        return loguitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLoguitude(double loguitude) {
        this.loguitude = loguitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


}
