package Model;

public class REGIME {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType_Regime() {
        return Type_Regime;
    }

    public void setType_Regime(String type_Regime) {
        Type_Regime = type_Regime;
    }

    public String getDate_Creation() {
        return Date_Creation;
    }

    public void setDate_Creation(String date_Creation) {
        Date_Creation = date_Creation;
    }

    public int getPeriode_Regime() {
        return Periode_Regime;
    }

    public void setPeriode_Regime(int periode_Regime) {
        Periode_Regime = periode_Regime;
    }

    public Double getPoids_Final() {
        return Poids_Final;
    }

    public void setPoids_Final(Double poids_Final) {
        Poids_Final = poids_Final;
    }

    public Double getNb_Kilo_Regime() {
        return Nb_Kilo_Regime;
    }

    public void setNb_Kilo_Regime(Double nb_Kilo_Regime) {
        Nb_Kilo_Regime = nb_Kilo_Regime;
    }

    private String Type_Regime;
    private String Date_Creation;
    private int Periode_Regime;
    private Double Poids_Final;
    private Double Nb_Kilo_Regime;
}
