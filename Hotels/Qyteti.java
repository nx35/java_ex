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
        Klientet = new ArrayList<Klienti>();
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
                Random random = new Random();
                int rand = random.nextInt(1250) + 250;
                try {
                    Thread.sleep(rand);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Klient.rezervo(Hotel);
                }
                catch (NukKaHapesiraException e) {
                    break;
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
                        if (HapesiraData[4].equals("false"))
                            kaTV = false;
                        else if (HapesiraData[4].equals("true"))
                            kaTV = true;
                        else
                            continue;   
                        DhomaStandarde dhomaStandarde = new DhomaStandarde(kaTV, numri, pershkrimi, cmimi);
                        Hotel.shtoHapesiren(dhomaStandarde);
                        break;
                    case "DhomaVIP":
                        boolean kaGjakuzi;
                        if (HapesiraData[4].equals("false"))
                            kaGjakuzi = false;
                        else if (HapesiraData[4].equals("true"))
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
        // lexo klientet.txt
        File klientetFile = new File("klientet.txt");
        try {
            Scanner klientetScanner = new Scanner(klientetFile);
            String emri;
            String mbiemri;
            String gjinia;
            int mosha;
            while(klientetScanner.hasNextLine()) {
                String klientLine = klientetScanner.nextLine();
                String[] klientData = klientLine.split(";", 4); // TODO: check if 3 params. if not ignore line
                emri = klientData[0];
                mbiemri = klientData[1];
                gjinia = klientData[2]; // TODO: if invalid ignore line
                mosha = Integer.parseInt(klientData[3]); // TODO: if invalid ignore line
                Klienti klienti = new Klienti(emri, mbiemri, gjinia, mosha);
                Klientet.add(klienti);
            }
            klientetScanner.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public boolean filloRezervimet() throws InterruptedException { //this implementation of join might be out of spec, since the requirement statet to continue with Faturo() after waiting for the threads to finish... inside the main function. and that filloRezervimet() should be void? 
        // alt implementation
        ArrayList<Rezervimi> rezervimet = new ArrayList<Rezervimi>();
        for(Klienti klienti : Klientet) {
            Rezervimi rezervimThread = new Rezervimi(Hotel, klienti);
            rezervimet.add(rezervimThread);
            rezervimThread.start();
        }
        for(Rezervimi rezervimi : rezervimet) {
            rezervimi.join();
        }
        return true;
    }
    public static void main(String args[]) {
        Hoteli hoteli = new Hoteli("Maradona");
        Qyteti qyteti = new Qyteti(hoteli);
        qyteti.lexoHapesirat(); // TODO: create files with data
        qyteti.lexoKlientet();
        try {
        boolean finished = qyteti.filloRezervimet();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("====== Nuk ka me hapesira te lira.\nFillo Faturimin");
        hoteli.faturo();
        System.out.println("Mbaroi faturimi");
    }
}

// default checking to throw RezervimiException:
// positive numbers
// nonempty strings
// nonnull objects
