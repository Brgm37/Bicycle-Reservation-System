package isel.sisinf.model.EntityClass;

import jakarta.persistence.*;

interface IClassica {
    Bicicleta getBicicleta();
    void setBicicleta(Bicicleta bicicleta);
    Integer getNomudanca();
    void setNomudanca(Integer nomudanca);
}

@Entity
@Table(name = "CLASSICA")
public class Classica implements IClassica {
    @Id
    @OneToOne
    private Bicicleta bicicleta;

    @Column(nullable = false)
    private Integer nomudanca;

    @Override
    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    @Override
    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
    }

    @Override
    public Integer getNomudanca() {
        return nomudanca;
    }

    @Override
    public void setNomudanca(Integer nomudanca) {
        this.nomudanca = nomudanca;
    }
}
