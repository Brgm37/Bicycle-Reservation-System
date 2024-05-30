package isel.sisinf.model.EntityClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

interface IClassica {
    Bicicleta getBicicleta();
    void setBicicleta(Bicicleta bicicleta);
    int getNrMudacas();
    void setNrMudacas(int nrMudacas);
}

@Entity
public class Classica implements IClassica {

    @Id
    @OneToOne
    private Bicicleta bicicleta;
    @Override
    public Bicicleta getBicicleta() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBicicleta(Bicicleta bicicleta) {
        // TODO Auto-generated method stub
    }

    @Override
    public int getNrMudacas() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setNrMudacas(int nrMudacas) {
        // TODO Auto-generated method stub
    }

    public Classica() {
        // TODO Auto-generated constructor stub
    }
}
