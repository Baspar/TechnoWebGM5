package Model;

public class Canon extends Defense{
    public Canon(){//DONE
        typeBatiment=TypeBatiment.CANON;
        
        //Héritage Defense
        typeDefense=TypeAttaque.LOCALISE;
        puissanceDefense=0;
        attaqueParSeconde=0.7;
        
        //HéritageBatiment
        niveau=0;
        ressourceNecessaire=TypeRessource.OR;
        pointsDeVie=400;
        tempsConstruction=5;
        coutUpdate=2000;
    }
    
    public Canon clone(){//DONE
        return new Canon();
    }
}
