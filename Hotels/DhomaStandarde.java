public class DhomaStandarde extends Hapesira implements Monitorohet {
    
    private boolean kaTV;
    
    public DhomaStandarde(boolean tv, int nr, String pershkrimi, double cmimi) {
        super(nr, pershkrimi, cmimi);
        kaTV = tv;
    }

    public boolean getKaTV() {
        return kaTV;
    }

    public void setKaTV(boolean tv) {
        kaTV = tv;
    }

    public boolean kaWifi() {
        return false;
    }

    public String getMonitorimi() {
        return "Kamerat";
    }

    public String toString() {
        String nuk = "";
        if (kaTV)
            nuk = "";
        else
            nuk = "nuk ";
        return "DhomaStandarde"+":"+super.Nr+":"+super.Pershkrimi+":"+super.Cmimi+":"+nuk+"ka TV";
    }
}
