

public class CheckBond {
    private double saldo;
    private int tempoWaktu;
    private double kenaikanBunga = 0.005;

    public CheckBond() {
        this.saldo = 0;
        this.tempoWaktu = 0;
    }
    
    public double kenaikanBunga(){
        double uangTotal = saldo;
        if (tempoWaktu >= 48) { 
            kenaikanBunga = 0.025;
        }else if(tempoWaktu >= 36) {
        	kenaikanBunga = 0.02;
        }else if(tempoWaktu >= 24) {
        	kenaikanBunga = 0.015;
        }else if(tempoWaktu >= 12) {
        	kenaikanBunga = 0.01;
        }else {
        	kenaikanBunga = 0.005;
        }
        
        for (int i = 1; i <= tempoWaktu; i++) {
            double bungaBulanIni = uangTotal * kenaikanBunga;
            uangTotal += bungaBulanIni; 
        }
        return uangTotal;
    }
    
    public void setSaldo(double uang){
        this.saldo = uang;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setTempoWaktu(int bulan) {
        if(bulan < 1 || bulan > 60){
        throw new IllegalArgumentException("Anda hanya bisa membeli dari 1 hingga 60 bulan");
        }
         this.tempoWaktu = bulan;
    }
    public int getTempoWaktu() {
        return tempoWaktu;
    }
}
