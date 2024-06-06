package isel.sisinf.model.EntityClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.Objects;

interface IClassica {
    Bicicleta getBicicleta();
    void setBicicleta(Bicicleta bicicleta);
}

@Entity
public class Classica implements IClassica {

    @Id
    @OneToOne
    private Bicicleta bicicleta;

    @Override
    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    @Override
    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
    }

    public Classica() {}

    public Classica(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
    }

    @Override
    public String toString() {
        return "Classica[bicicleta=" + bicicleta.getId() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        }

        Classica other = (Classica)obj;
        return Objects.equals(bicicleta, other.bicicleta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bicicleta);
    }
}
