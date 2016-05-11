package sif.ali.questionnaire1.Model;

/**
 * Created by ali on 06/02/2016.
 */
public class Probleme {

    //private static final long serialVersionUID = 1L;
    private Type type;
    private Localisation loc;
    private String description;
    private String image;

    public Probleme(Type type, Localisation loc, String description, String image) {
        this.type = type;
        this.loc = loc;
        this.description = description;
        this.image = image;
    }

   /* public Probleme() {
        this.type = new Type();
        this.loc = new Localisation();
        this.description = "";
        this.image ="";
    }*/

    @Override
    public String toString() {
        return "Probleme{" +
                "type=" + type.toString() +
                ", loc=" + loc.toString() +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public Localisation getLoc() {
        return loc;
    }

    public Type getType() {
        return type;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setLoc(Localisation loc) {
        this.loc = loc;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
