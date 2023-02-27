public abstract class Hapesira {

  protected int Nr;
  protected String Pershkrimi;
  protected double Cmimi;

  public Hapesira(int nr, String pershkrimi, double cmimi) {
    Nr = nr;
    Pershkrimi = pershkrimi;
    Cmimi = cmimi;
  }

  public int getNr() {
    return Nr;
  }
  public String getPershkrimi() {
    return Pershkrimi;
  }
  public double getCmimi() {
    return Cmimi;
  }
  public void setCmimi(int cmimi) {
    Cmimi = cmimi;
  }
  public abstract boolean kaWifi();

  public String toString() {
    return Nr+":"+Pershkrimi+":"+Cmimi;
  }
  public boolean equals(Hapesira h) {
    return h.getNr() == Nr;
  }
}
  
