public class Caserne extends Batiment{
    private Armee armee;
    public Caserne(){//WIP
        typeBatiment="Caserne";
    }
    public int calculerCoutUpgrade(){//TODO
        return 0;
    }
    public void upgrade(){//TODO
    }
    public Caserne clone(){//DONE
        return new Caserne();
    }
}
