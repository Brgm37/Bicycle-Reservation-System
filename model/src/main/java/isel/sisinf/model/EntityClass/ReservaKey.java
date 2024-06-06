package isel.sisinf.model.EntityClass;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Objects;

@Embeddable
public class ReservaKey {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noReserva;

    private int loja;

    public ReservaKey() {
    }

    public ReservaKey(int noReserva, int loja) {
        this.noReserva = noReserva;
        this.loja = loja;
    }

    int getNoReserva() {
        return noReserva;
    }

    void setNoReserva(int numReserva) {
        this.noReserva = numReserva;
    }

    int getLoja() {
        return loja;
    }
    void setLoja(int loja) {
        this.loja = loja;
    }

    @Override
    public String toString() {
        return "ReservaKey[loja=" + loja + ", numReserva=" + noReserva + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ReservaKey reservaKey = (ReservaKey) obj;
        return noReserva == reservaKey.noReserva && loja == reservaKey.loja;
    }
    @Override
    public int hashCode() {
        return Objects.hash(noReserva, loja);
    }
}
