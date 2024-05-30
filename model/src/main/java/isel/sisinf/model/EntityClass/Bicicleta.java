package isel.sisinf.model.EntityClass;

import jakarta.persistence.*;
import java.util.*;

interface IBicicleta {
   Integer getId();
   void setId(Integer id);
   double getPeso();
   void setPeso(double peso);
   int getRaio();
   void setRaio(int raio);
   String getModelo();
   void setModelo(String modelo);
   String getMarca();
   void setMarca(String marca);
   String getEstado();
   void setEstado(String estado);
   char getAtrdisc();
   void setAtrdisc(char atrdisc);
   Dispositivo getDispositivo();
   void setDispositivo(Dispositivo dispositivo);
}

@Entity
@Table(name = "BICICLETA")
@NamedQuery(name="Bicicleta.findByKey",
        query="SELECT b FROM Bicicleta b WHERE b.id =:key"
)
@NamedStoredProcedureQuery(
        name = "bike_availability_procedure",
        procedureName = "check_bike_availability",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = Boolean.class)
        }
)
public class Bicicleta implements IBicicleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(precision = 4, scale = 2)
    private double peso;
    private int raio;
    @Column(length = 20)
    private String modelo;
    @Column(length = 30)
    private String estado;
    @Column(length = 1)
    private char atrdisc;
    @OneToOne(optional = false)
    @JoinColumn(name = "dispositivo", referencedColumnName = "noserie")
    private Dispositivo dispositivo;
    @Column(length = 30)
    private String marca;

    public Bicicleta() {}

    @Override
    public Integer getId(){
        return this.id;
    }
    @Override
    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public double getPeso() {
        return this.peso;
    }
    @Override
    public void setPeso(double peso) {
        this.peso = peso;
    }
    @Override
    public int getRaio() {
        return this.raio;
    }
    @Override

    public void setRaio(int raio) {
        this.raio = raio;
    }

    @Override
    public String getModelo(){
        return this.modelo;
    }
    @Override
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String getMarca() {
        return this.marca;
    }

    @Override
    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String getEstado() {
        return this.estado;
    }
    @Override
    public void setEstado(String estado) {
        this.estado = estado;
    }
    @Override
    public char getAtrdisc() {
        return this.atrdisc;
    }
    @Override
    public void setAtrdisc(char atrdisc) {
        this.atrdisc = atrdisc;
    }
    @Override
    public Dispositivo getDispositivo() {
        return this.dispositivo;
    }
    @Override
    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
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
        Bicicleta other = (Bicicleta) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Bicicleta[id=" + id + ", peso=" + peso + ", raio=" + raio + ", modelo=" + modelo + ", marca=" + marca +
                ", estado=" + estado + ", atrdisc=" + atrdisc + ", dispositivo=" + dispositivo + "]";
    }

}

