package Model;

public class Caserne extends Batiment{
    private Armee armee;
    public Caserne(){//WIP
        typeBatiment=TypeBatiment.Caserne;
    }
    public void upgrade(){//TODO
        super.upgrade();
    }
    public Caserne clone(){//DONE
        return new Caserne();
    }
}
