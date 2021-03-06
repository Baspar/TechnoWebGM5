package Model;

public class Mortier extends Defense{
    public Mortier(){//DONE
        typeBatiment=TypeBatiment.MORTIER;

        //Héritage Defense
        typeDefense=TypeAttaque.ZONE;
        puissanceDefense=10;
        attaqueParSeconde=0.7;

        //HéritageBatiment
        niveau=0;
        ressourceNecessaire=TypeRessource.OR;
        pointsDeVie=400;
        tempsConstruction=5;
        coutUpdate=2000;
    }

    public Mortier clone(){//DONE
        return new Mortier();
    }
}
