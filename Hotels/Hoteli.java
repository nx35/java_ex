import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

public class Hoteli {
    private String Emri;
    public ReentrantLock Radha;
    private Vector<Hapesira> Hapesirat;
    private Hashtable<Klienti, ArrayList<Hapesira>> Rezervimet;

    public Hoteli(String emri) {
        Emri = emri;
        Rezervimet = new Hashtable<Klienti, ArrayList<Hapesira>>();
        Radha = new ReentrantLock();
    }
    public String getEmri() {
        return Emri;
    }
    public Vector<Hapesira> getHapesirat() {
        return Hapesirat;
    }
    public Hashtable<Klienti, ArrayList<Hapesira>> getRezervimet() {
        return Rezervimet;
    }
    public void shtoHapesiren(Hapesira h) {
        if (!Hapesirat.contains(h))
            Hapesirat.add(h);
    }
    public boolean kaHapesira() {
        return !Hapesirat.isEmpty();
    }
    public Hapesira rezervoHapesiren(Klienti k) {
        if (kaHapesira()) {
            Hapesira hapesira = Hapesirat.remove(0);
            regjistroHapesiren(k, hapesira);
            return hapesira;
        }
        else {
            return null;
        }
    }
    private void regjistroHapesiren(Klienti k, Hapesira h) {
        if (Rezervimet.containsKey(k)) {
            Rezervimet.get(k).add(h);
        }
        else {
            ArrayList<Hapesira> hapesirat = new ArrayList<Hapesira>();
            hapesirat.add(h);
            Rezervimet.put(k, hapesirat);
        } 
    }
    public void faturo() {
        Enumeration<Klienti> klientet = Rezervimet.keys();
        Enumeration<ArrayList<Hapesira>> hapesirat = Rezervimet.elements();
        while(klientet.hasMoreElements()) {
            Klienti klienti = klientet.nextElement();
            ArrayList<Hapesira> hapesiratR = hapesirat.nextElement();
            String filename = klienti.getEmri()+"_"+klienti.getMbiemri()+".txt";

            // create file
            try {
                File klientFile = new File(filename);
                if (klientFile.createNewFile()) {
                    
                }
                else {
                    System.out.println("File already exists.");
                }
            }
            catch(IOException e) {
                System.out.println("Could not create the file "+filename);
                e.printStackTrace();
            }

            //wirte to file
            try {
                FileWriter klientFileWriter = new FileWriter(filename);

                klientFileWriter.write("Klienti: "+klienti.getEmri()+" "+klienti.getMbiemri()+" - "+klienti.getGjinia()+" "+klienti.getMosha()+" vjec");
                String breakLine = "------------------------------------------------------------------------";
                klientFileWriter.write(breakLine);
                int numberHapesira = hapesiratR.size();
                klientFileWriter.write("Numri i hapesirave te rezervuara: "+numberHapesira);
                klientFileWriter.write(breakLine);
                double total = 0;
                for(Hapesira hapesira: hapesiratR) {
                    total+=hapesira.Cmimi;
                    klientFileWriter.write(hapesira.toString());
                }
                klientFileWriter.write(breakLine);
                klientFileWriter.write("Totali: "+total);
                klientFileWriter.write(breakLine);
                klientFileWriter.close();
            }
            catch(IOException e) {
                System.out.println("Could not write to file "+filename);
                e.printStackTrace();
            }
        }
    }
}
