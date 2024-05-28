package isel.sisinf.model.EntityClass;

import jakarta.persistence.*;

import java.util.*;

interface IBicicleta {
   int getId();
   void setId(int id);
   double getPeso();
   void setPeso(double peso);
   int getRaio();
   void setRaio(int raio);
   String getModelo();
   void setModelo(String modelo);
   String getEstado();
   void setEstado(String estado);
   char getAtrdisc();
   void setAtrdisc(char atrdisc);
   Dispositivo getDispositivo();
   void setDispositivo(Dispositivo dispositivo);
}
@Entity
public class Bicicleta implements IBicicleta {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double peso;
    private int raio;
    private String modelo;
    private String estado;
    private char atrdisc;
    @OneToOne(optional = false)
    private Dispositivo dispositivo;

    public Bicicleta() {
        // TODO
    }

    @Override
    public int getId(){
        return this.id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public double getPeso() {
        return this.peso;
    }
    @Override
    public void setPeso(double peso) {
//        if(peso < 0) {
//            throw new IllegalArgumentException("'Peso' must be higher than 0.");
//        }
        this.peso = peso;
    }
    @Override
    public int getRaio() {
        return this.raio;
    }
    @Override
    public void setRaio(int raio) {
//        if (raio < 13 || raio > 23) {
//            throw  new IllegalArgumentException("'Raio' must be between 13 and 23.");
//        }
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

//    @OneToMany(mappedBy = "bicicletaUsed", fetch = FetchType.LAZY)
//    private Set<Reserva> reservas;

//    @Override
//    public boolean equals(Objects obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        Bicicleta other = (Bicicleta) obj;
//        return id == other.id;
//    }

//    private static final int[] mudancas = {1, 6, 18, 24};

}

//id serial primary key,
//peso numeric(4,2) check (peso >= 0),
//raio integer check (raio between 13 and 23),
//modelo varchar(20),
//marca varchar(30),
//mudanca integer check (mudanca in (1,6,18,24)),
//estado varchar(30) check (estado in ('livre', 'ocupado', 'em manutenção')),
//atrdisc char(1) check (atrdisc in ('C', 'E')),
//dispositivo integer,
//foreign key (dispositivo) references dispositivo(noserie)