package isel.sisinf.model.EntityClass;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

interface IReserva {
    Integer getNoreserva();
    void setNoreserva(Integer noreserva);
    Loja getLoja();
    void setLoja(Loja loja);
    LocalDateTime getDtinicio();
    void setDtinicio(LocalDateTime dtinicio);
    LocalDateTime getDtfim();
    void setDtfim(LocalDateTime dtfim);
    float getValor();
    void setValor(float valor);
    Bicicleta getBicicleta();
    void setBicicleta(Bicicleta bicicleta);
    int setVersion(int version);
    int getVersion();
}

@Entity
@Table(name = "RESERVA")
@NamedQuery(name="Reserva.findByKey",
        query="SELECT r FROM Reserva r WHERE r.key =:key"
)
@NamedStoredProcedureQuery(
        name = "make_reserva_procedure",
        procedureName = "make_reservation",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Double.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class),
        }
)
public class Reserva implements IReserva {

    @EmbeddedId
    private ReservaKey key;

    @Column(nullable = false)
    private LocalDateTime dtinicio;

    @MapsId("loja")
    @ManyToOne
    @JoinColumn(name = "loja", referencedColumnName = "codigo")
    private Loja loja;

    @Column
    private LocalDateTime dtfim;

    @Column(nullable = false, precision = 5, scale = 2)
    private Float valor;

    @ManyToOne
    @JoinColumn(name = "bicicleta", nullable = false, referencedColumnName = "id")
    private Bicicleta bicicletaUsed;

    @Version
    private int version;

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public int setVersion(int version) {
        return this.version = version;
    }

    @Override
    public Integer getNoreserva() {
        return key.getNoReserva();
    }

    @Override
    public void setNoreserva(Integer noreserva) {
        this.key.setNoReserva(noreserva);
    }

    @Override
    public Loja getLoja() {
        return loja;
    }

    @Override
    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    @Override
    public LocalDateTime getDtinicio() {
        return dtinicio;
    }

    @Override
    public void setDtinicio(LocalDateTime dtinicio) {
        this.dtinicio = dtinicio;
    }

    @Override
    public LocalDateTime getDtfim() {
        return dtfim;
    }

    @Override
    public void setDtfim(LocalDateTime dtfim) {
        this.dtfim = dtfim;
    }

    @Override
    public float getValor() {
        return valor;
    }

    @Override
    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public Bicicleta getBicicleta() {
        return bicicletaUsed;
    }

    @Override
    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicletaUsed = bicicleta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
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

        Reserva other = (Reserva) obj;
        return Objects.equals(key, other.key);
    }

    @Override
    public String toString() {
        return "Reserva[noreserva=" + key + ", loja=" + loja +", dtinicio=" + dtinicio + ", dtfim=" + dtfim +
                ", valor=" + valor + ", bicicleta=" + bicicletaUsed + "]";
    }

    public Reserva() {
        this.key = new ReservaKey();
    }

    public Reserva(Loja lojaId, String dtinicio, String dtfim, double valor, Bicicleta bicicleta) {
        this.key = new ReservaKey();
        this.loja = lojaId;
        this.dtinicio = LocalDateTime.parse(dtinicio+"T00:00:00");
        this.dtfim = LocalDateTime.parse(dtfim+"T00:00:00");
        this.valor = (float) valor;
        this.bicicletaUsed = bicicleta;
    }
}