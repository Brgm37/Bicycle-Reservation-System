package isel.sisinf.model.EntityClass;

import jakarta.persistence.*;
import java.time.LocalDateTime;

interface IReserva {
    Integer getNoreserva();
    void setNoreserva(Integer noreserva);
    Loja getLoja();
    void setLoja(Loja loja);
    LocalDateTime getDtinicio();
    void setDtinicio(LocalDateTime dtinicio);
    LocalDateTime getDtfim();
    void setDtfim(LocalDateTime dtfim);
    Float getValor();
    void setValor(Float valor);
    Bicicleta getBicicleta();
    void setBicicleta(Bicicleta bicicleta);
}

@Entity
@Table(name = "RESERVA")
public class Reserva implements IReserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noreserva;

    @ManyToOne
    @JoinColumn(name = "loja", nullable = false)
    private Loja loja;

    @Column(nullable = false)
    private LocalDateTime dtinicio;

    @Column(nullable = false)
    private LocalDateTime dtfim;

    @Column(nullable = false, precision = 5, scale = 2)
    private Float valor;

    @ManyToOne
    @JoinColumn(name = "bicicleta", nullable = false, referencedColumnName = "id")
    private Bicicleta bicicletaUsed;

    @Override
    public Integer getNoreserva() {
        return null;
    }

    @Override
    public void setNoreserva(Integer noreserva) {

    }

    @Override
    public Loja getLoja() {
        return null;
    }

    @Override
    public void setLoja(Loja loja) {

    }

    @Override
    public LocalDateTime getDtinicio() {
        return null;
    }

    @Override
    public void setDtinicio(LocalDateTime dtinicio) {

    }

    @Override
    public LocalDateTime getDtfim() {
        return null;
    }

    @Override
    public void setDtfim(LocalDateTime dtfim) {

    }

    @Override
    public Float getValor() {
        return null;
    }

    @Override
    public void setValor(Float valor) {

    }

    @Override
    public Bicicleta getBicicleta() {
        return null;
    }

    @Override
    public void setBicicleta(Bicicleta bicicleta) {
    }

    // Getters and Setters
}
