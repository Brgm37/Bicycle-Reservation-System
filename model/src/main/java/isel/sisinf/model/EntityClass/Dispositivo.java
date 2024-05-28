package isel.sisinf.model.EntityClass;

import jakarta.persistence.*;

import java.util.Objects;

interface IDispositivo {
    int getNoSerie();
    void setNoSerie(int noSerie);
    float getLatitude();
    void setLatitude(Float latitude);
    float getLongitude();
    void setLongitude(Float longitude);
}

@Entity
@Table(name = "DISPOSITIVO")
public class Dispositivo implements IDispositivo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noSerie;

    @Column(nullable = false, precision = 6, scale = 4)
    private Float latitude;

    @Column(nullable = false, precision = 6, scale = 4)
    private Float longitude;

    @Column(nullable = false)
    private Integer bateria;

    @Override
    public int getNoSerie() {
        return noSerie;
    }

    @Override
    public void setNoSerie(int noSerie) {
        this.noSerie = noSerie;
    }

    @Override
    public float getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(Float latitude) {
        this.latitude = latitude;

    }

    @Override
    public float getLongitude() {
        return longitude;
    }

    @Override
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        return Objects.hash(noSerie);
    }

    public Dispositivo() {}

    public Dispositivo(int noSerie, float latitude, float longitude, int bateria) {
        this.noSerie = noSerie;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bateria = bateria;
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

        Dispositivo other = (Dispositivo) obj;
        return Objects.equals(noSerie, other.noSerie);
    }

    @Override
    public String toString() {
        return "Dispositivo[noSerie =" + noSerie + ", "
                + "latitude =" + latitude + ", "
                + "longitude =" + longitude + ", "
                + "bateria =" + bateria + "]";
    }
}
