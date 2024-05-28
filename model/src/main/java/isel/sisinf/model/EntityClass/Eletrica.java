package isel.sisinf.model.EntityClass;

import jakarta.persistence.*;
import java.util.Objects;

interface IEletrica {
    Bicicleta getBicicleta();
    void setBicicleta(Bicicleta bicicleta);

    int getAutonomia();
    void setAutonomia(int autonomia);

    int getVelocidade();
    void setVelocidade(int velocidade);
}

@Entity
@Table(name = "ELETRICA")
public class Eletrica implements IEletrica {
    @Id
    @OneToOne
    private Bicicleta bicicleta;

    @Column(nullable = false)
    private int autonomia;

    @Column(nullable = false)
    private int velocidade;

    @Override
    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    @Override
    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
    }

    @Override
    public int getAutonomia() {
        return autonomia;
    }

    @Override
    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }

    @Override
    public int getVelocidade() {
        return velocidade;
    }

    @Override
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bicicleta, autonomia, velocidade);
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

        Eletrica other = (Eletrica) obj;
        return Objects.equals(bicicleta, other.bicicleta)
                && autonomia == other.autonomia
                && velocidade == other.velocidade;
    }

    @Override
    public String toString() {
        return "Eletrica[bicicleta=" + bicicleta.getId() + ", autonomia=" + autonomia + ", velocidade=" + velocidade + "]";
    }
}
