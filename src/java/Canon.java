public class Canon extends Defense{
    public Canon(){//DONE
        typeBatiment="Canon";
        
        //Héritage Defense
        typeDefense=TypeAttaque.LOCALISE;
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
    
    public Canon clone(){//DONE
        return new Canon();
    }
}
