package Model;

public class MainVillage {
	public static void affichageVillage(Village v){
		System.out.println("Nom village : " +v.getNom() );
		
	}
	
	public static void main(String[] args){
		Village v=new Village("blaine");
		affichageVillage(v);
	}
}
