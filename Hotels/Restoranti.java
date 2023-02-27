public class Restoranti extends Hapesira implements Monitorohet {
    private int kapaciteti;

    public Restoranti(int kap, int nr, String pershkrimi, double cmimi) {
        super(nr, pershkrimi, cmimi);
        kapaciteti = kap;
    }

    public boolean kaWifi() {
        return true;
    }

    public String getMonitorimi() {
        return "Sigurimi Fizik";
    }

    public int getKapaciteti() {
        return kapaciteti;
    }

    public void setKapaciteti(int kap) {
        kapaciteti = kap;
    }

    public String toString() {
        return "Restoranti"+":"+super.Nr+":"+super.Pershkrimi+":"+super.Cmimi+" me kapacitet "+kapaciteti;
    }
}
