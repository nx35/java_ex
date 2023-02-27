public class DhomaVIP extends Hapesira implements Monitorohet {
    private boolean kaGjakuzi;

    public DhomaVIP(boolean gjakuzi, int nr, String pershkrimi, double cmimi) {
        super(nr, pershkrimi, cmimi);
        kaGjakuzi = gjakuzi;
    }
    public boolean kaWifi() {
        return true;
    }

    public String getMonitorimi() {
        return "Sigurimi Fizik";
    }

    public boolean getKaGjakuzi() {
        return kaGjakuzi;
    }

    public void setKaGjakuzi(boolean gjakuzi) {
        kaGjakuzi = gjakuzi;
    }

    public String toString() {
        String nuk = "";
        if (kaGjakuzi)
            nuk = "";
        else
            nuk = "nuk ";
        return "DhomaVIP"+":"+super.Nr+":"+super.Pershkrimi+":"+super.Cmimi+":"+nuk+"ka Gjakuzi";
    }
}
