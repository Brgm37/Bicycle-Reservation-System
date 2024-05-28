package isel.sisinf.model.EntityClass;

import jakarta.persistence.*;
import java.util.Objects;

interface IClienteReserva {
    Integer getId();
    void setId(Integer id);

    Pessoa getCliente();
    void setCliente(Pessoa cliente);

    Reserva getReserva();
    void setReserva(Reserva reserva);
}

@Entity
@Table(name = "CLIENTERESERVA")
public class ClienteReserva implements IClienteReserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente", nullable = false)
    private Pessoa cliente;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "reserva", referencedColumnName = "noreserva"),
            @JoinColumn(name = "loja", referencedColumnName = "loja")
    })
    private Reserva reserva;

    public ClienteReserva() {}

    public ClienteReserva(Pessoa cliente, Reserva reserva) {
        this.cliente = cliente;
        this.reserva = reserva;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Pessoa getCliente() {
        return cliente;
    }

    @Override
    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    @Override
    public Reserva getReserva() {
        return reserva;
    }

    @Override
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

        ClienteReserva other = (ClienteReserva) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "ClienteReserva[id=" + id + ", cliente=" + cliente.getId() + ", reserva=" + reserva.getNoreserva() + ", loja=" +
                reserva.getLoja().getCodigo() + "]";
    }
}