package Model;

public class Mortier extends Defense{
    public Mortier(){//DONE
        typeBatiment=TypeBatiment.Mortier;
        
        //Héritage Defense
        typeDefense=TypeAttaque.ZONE;
        puissanceDefense=0;
        attaqueParSeconde=0.7;
        
        //HéritageBatiment
        niveau=0;
        ressourceNecessaire=TypeRessource.OR;
        pointsDeVie=400;
        x=-1;
        y=-1;
        tempsConstruction=5;
        coutConstruction=2000;
    }
   
    public Mortier clone(){//DONE
        return new Mortier();
    }
}
