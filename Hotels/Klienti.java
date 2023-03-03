import java.util.ArrayList;

public class Klienti {
    private String Emri, Mbiemri, Gjinia;
    private int Mosha;
    private ArrayList<Hapesira> HapesiratERezervuara;

    public Klienti(String emri, String mbiemri, String gjinia, int mosha) {
        Emri = emri;
        Mbiemri = mbiemri;
        gjinia = gjinia.toLowerCase();
        if (gjinia.equals("m") || gjinia.equals("mashkull")) {
            Gjinia = "M";
        }
        else if (gjinia.equals("f") || gjinia.equals("femer")) {
            Gjinia = "F";
        }
        else {
            Gjinia = "O";
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

    public void rezervo(Hoteli h) throws NukKaHapesiraException {
        if (merreRadhen(h)) {
            h.Radha.lock();
            System.out.println("*******>"+this.getEmri()+" mori radhen");
            Hapesira hapesira = h.rezervoHapesiren(this);
            if (hapesira == null) {
                throw new NukKaHapesiraException("Nuk ka hapesire te lire per te rezervuar");
            }
            else {
                System.out.println("rezervo(): "+hapesira);
                //HapesiratERezervuara.add(hapesira);
                System.out.println(Emri+" "+Mbiemri+" rezervoi me sukses hapesiren "+hapesira.toString()+" ne hotelin "+h.getEmri());
            }
            h.Radha.unlock();
            System.out.println("------>"+this.getEmri()+" e leshoi radhen");
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
