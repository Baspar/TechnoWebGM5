import java.util.Set;

public class MainBatiments{
    public static void affichage(Batiments batiments){
        System.out.println();
        System.out.println("Résumé:");
        Set<String> nBatiments=batiments.getSetNomBatiment();
        for(String type: nBatiments){
            System.out.println("  "+type+"["+batiments.getBatiments(type).size()+"]:");
            for(Batiment batiment:batiments.getBatiments(type)){
                System.out.println("    * lvl"+batiment.getNiveau());
            }
        }

    }
    public static void main(String[] args){
        Batiments batiments=new Batiments();

        Set<String> nBatiments=batiments.getSetNomBatiment();
        System.out.println("Vous pouvez instancier des: "+nBatiments);

        affichage(batiments);
        batiments.addNewBuilding("HDV");
        batiments.addNewBuilding("MineOr");
        batiments.addNewBuilding("MineOr");
        affichage(batiments);
        batiments.getBatiments("MineOr").get(1).upgrade();
        affichage(batiments);
    }
}
