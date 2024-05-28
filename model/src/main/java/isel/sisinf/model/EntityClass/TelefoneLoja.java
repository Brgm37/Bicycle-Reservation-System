package isel.sisinf.model.EntityClass;

import jakarta.persistence.*;
import java.util.Objects;

interface ITelefoneLoja {
    String getNumero();
    void setNumero(String numero);

    Loja getLoja();
    void setLoja(Loja loja);
}

@Entity
@Table(name = "TELEFONELOJA")
public class TelefoneLoja implements ITelefoneLoja {

    @Id
    @Column(length = 10)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "loja", nullable = false)
    private Loja loja;

    public TelefoneLoja() {}

    public TelefoneLoja(String numero, Loja loja) {
        this.numero = numero;
        this.loja = loja;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
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

        TelefoneLoja other = (TelefoneLoja) obj;
        return Objects.equals(numero, other.numero);
    }

    @Override
    public String toString() {
        return "TelefoneLoja[numero=" + numero + ", loja=" + loja.getCodigo() + "]";
    }
}
