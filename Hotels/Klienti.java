import java.util.ArrayList;

public class Klienti {
    private String Emri, Mbiemri, Gjinia;
    private int Mosha;
    private ArrayList<Hapesira> HapesiratERezervuara;

    public Klienti(String emri, String mbiemri, String gjinia, int mosha) {
        Emri = emri;
        Mbiemri = mbiemri;
        gjinia = gjinia.toLowerCase();
        if (gjinia == "m" || gjinia == "mashkull") {
            Gjinia = "mashkull";
        }
        else if (gjinia == "f" || gjinia == "femer") {
            Gjinia = "femer";
        }
        else {
            Gjinia = "tjeter";
        }
        Mosha = mosha;
    }

    public String getEmri() {
        return Emri;
    }
    public String getMbiemri() {
        return Mbiemri;
    }
    public String getGjinia() {
        return Gjinia;
    }
    public int getMosha() {
        return Mosha;
    }
    public ArrayList<Hapesira> getHapesiratERezervuara() {
        return HapesiratERezervuara;
    }
    private boolean merreRadhen(Hoteli h) {
        return h.Radha.tryLock();
    }

    public void rezervo(Hoteli h) {
        if (merreRadhen(h)) {
            h.Radha.lock();
            Hapesira hapesira = h.rezervoHapesiren(this);
            HapesiratERezervuara.add(hapesira);
            h.Radha.unlock();
            System.out.println(Emri+" "+Mbiemri+"rezervoi me sukses hapesiren "+hapesira.toString()+" ne hotelin "+h.getEmri());
        }
        else {
            System.out.println(Emri+" "+Mbiemri+" nuk e mori radhen ne hotelin "+h.getEmri());
        }
    }
    public String toString() {
        return Emri+" "+Mbiemri+"-"+Gjinia+" "+Mosha+" vjec";
    }
    public boolean equals(Klienti k) {
        boolean equals = true;
        if (Emri!=k.getEmri() || Mosha!=k.getMosha() || Gjinia!=k.getGjinia())
            equals = false;
        for (int i=0; i<HapesiratERezervuara.size(); i++) {
            if (!HapesiratERezervuara.get(i).equals(k.getHapesiratERezervuara().get(i))) {
                equals = false;
                break;
            }
        }
        return equals;
    }
}
