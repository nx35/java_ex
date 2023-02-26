import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Qyteti {
    private Hoteli Hotel;
    private ArrayList<Klienti> Klientet;

    public Qyteti(Hoteli h) {
        Hotel = h;
    }
    private class Rezervimi extends Thread {
        private Hoteli Hotel;
        private Klienti Klient;
        public Rezervimi(Hoteli h, Klienti k) {
            Hotel = h;
            Klient = k;
        }
        public void run() {
            while (Hotel.kaHapesira()) {
                Klient.rezervo(Hotel);
                Random random = new Random();
                long rand = random.nextLong(1500L - 250L) + 250L;
                try {
                    Thread.sleep(rand);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public Hoteli getHotel() {
        return Hotel;
    }
    public ArrayList<Klienti> getKlientet() {
        return Klientet;
    }
    
    public void lexoHapesirat() {
        File hapesiratFile = new File("hapesirat.txt");
        try {
            Scanner lineReader = new Scanner(hapesiratFile);
            while (lineReader.hasNextLine()) {
                String lineData = lineReader.nextLine();
                String[] HapesiraData = lineData.split(":", 5); // TODO: check if there are 5 parameters. if not skip this line
                String llojiHapesires = HapesiraData[0];
                int numri = Integer.parseInt(HapesiraData[1]); // TODO: if not int skip line
                String pershkrimi = HapesiraData[2];
                double cmimi = Double.parseDouble(HapesiraData[3]); // TODO: if not double skip line

                switch(llojiHapesires) {
                    case "DhomaStandarde":
                        boolean kaTV;
                        if (HapesiraData[4]=="false")
                            kaTV = false;
                        else if (HapesiraData[4]=="true")
                            kaTV = true;
                        else
                            continue;
                        DhomaStandarde dhomaStandarde = new DhomaStandarde(kaTV, numri, pershkrimi, cmimi);
                        Hotel.shtoHapesiren(dhomaStandarde);
                        break;
                    case "DhomaVIP":
                        boolean kaGjakuzi;
                        if (HapesiraData[4]=="false")
                            kaGjakuzi = false;
                        else if (HapesiraData[4]=="true")
                            kaGjakuzi = true;
                        else
                            continue;
                        DhomaVIP dhomaVIP = new DhomaVIP(kaGjakuzi, numri, pershkrimi, cmimi);
                        Hotel.shtoHapesiren(dhomaVIP);
                        break;
                    case "Restorant":
                        int kapacitetiR = Integer.parseInt(HapesiraData[4]); // TODO: skip if throws
                        Restoranti restorant = new Restoranti(kapacitetiR, numri, pershkrimi, cmimi);
                        Hotel.shtoHapesiren(restorant);
                        break;
                    case "SallaPerKonferenca":
                        int kapacitetiS = Integer.parseInt(HapesiraData[4]); // TODO: skip if throws
                        SallaPerKonferenca sallaPerKonferenca = new SallaPerKonferenca(kapacitetiS, numri, pershkrimi, cmimi);
                        Hotel.shtoHapesiren(sallaPerKonferenca);
                        break;
                    default:
                        continue;
                }
            }
            lineReader.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void shtoKlientin(Klienti klienti) {
        if (!Klientet.contains(klienti))
            Klientet.add(klienti);
    }
    public void lexoKlientet() {
        
    }
}