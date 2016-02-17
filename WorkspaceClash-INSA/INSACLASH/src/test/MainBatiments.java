package test;

import java.util.Set;

import Model.Batiment;
import Model.Batiments;
import Model.TypeBatiment;

public class MainBatiments{
    public static void affichage(Batiments batiments){
        System.out.println();
        System.out.println("Résumé:");
        Set<TypeBatiment> nBatiments=batiments.getSetNomBatiment();
        for(TypeBatiment type: nBatiments){
            System.out.println("  "+type+"["+batiments.getBatiments(type).size()+"]:");
            for(Batiment batiment:batiments.getBatiments(type)){
                System.out.println("    * lvl"+batiment.getNiveau());
            }
        }

    }
    public static void main(String[] args){
        Batiments batiments=new Batiments();

        Set<TypeBatiment> nBatiments=batiments.getSetNomBatiment();
        System.out.println("Vous pouvez instancier des: "+nBatiments);

        affichage(batiments);
        for(int i=0; i<10; i++)
            batiments.addNewBuilding(TypeBatiment.MINEOR);
        affichage(batiments);
        batiments.addNewBuilding(TypeBatiment.MINEOR);
        affichage(batiments);
    }
}
