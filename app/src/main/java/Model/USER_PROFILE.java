package Model;

public class USER_PROFILE {
    private long id;
    private long id_user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getDate_Naissence() {
        return Date_Naissence;
    }

    public void setDate_Naissence(String date_Naissence) {
        Date_Naissence = date_Naissence;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String sexe) {
        Sexe = sexe;
    }

    public Float getTaille() {
        return Taille;
    }

    public void setTaille(Float taille) {
        Taille = taille;
    }

    public Float getPoids() {
        return Poids;
    }

    public void setPoids(Float poids) {
        Poids = poids;
    }

    private String Nom;
    private String Prenom;
    private String Date_Naissence;
    private String Sexe;
    private Float Taille;
    private Float Poids;
}
