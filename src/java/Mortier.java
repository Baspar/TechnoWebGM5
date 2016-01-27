public class Mortier extends Defense{
    public Mortier(){//WIP
        typeBatiment="Mortier";
    }
    public void upgrade(){//TODO
        super.upgrade();
    }
    public Mortier clone(){//DONE
        return new Mortier();
    }
}
