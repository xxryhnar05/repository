
public class Main {
	public static void main(String[] args) {
		CheckBond bond = new CheckBond(); // Example: Creating a bond with a term of 24 months
		bond.setTempoWaktu(10);
		bond.setSaldo(15000);
		System.out.println("Uang Pendapatan: " + bond.kenaikanBunga());
			  
	}
}
