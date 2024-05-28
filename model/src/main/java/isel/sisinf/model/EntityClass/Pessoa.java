package isel.sisinf.model.EntityClass;

import jakarta.persistence.*;

import java.util.Objects;

interface IPessoa {
    Integer getId();
    String getNome();
    String getMorada();
    String getEmail();
    String getTelefone();
    String getNoident();
    String getNacionalidade();
    String getAtrdisc();

    void setId(Integer id);
    void setNome(String nome);
    void setMorada(String morada);
    void setEmail(String email);
    void setTelefone(String telefone);
    void setNoident(String noident);
    void setNacionalidade(String nacionalidade);
    void setAtrdisc(String atrdisc);
}

@Entity
@Table(name = "PESSOA")
public class Pessoa implements IPessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 40, nullable = false)
    private String nome;

    @Column(length = 150, nullable = false)
    private String morada;

    @Column(length = 40, nullable = false, unique = true)
    private String email;

    @Column(length = 30, unique = true)
    private String telefone;

    @Column(length = 12, unique = true, nullable = false)
    private String noident;

    @Column(length = 20, nullable = false)
    private String nacionalidade;

    @Column(nullable = false)
    private String atrdisc;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getMorada() {
        return morada;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getTelefone() {
        return telefone;
    }

    @Override
    public String getNoident() {
        return noident;
    }

    @Override
    public String getNacionalidade() {
        return nacionalidade;
    }

    @Override
    public String getAtrdisc() {
        return atrdisc;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void setMorada(String morada) {
        this.morada = morada;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public void setNoident(String noident) {
        this.noident = noident;
    }

    @Override
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    @Override
    public void setAtrdisc(String atrdisc) {
        this.atrdisc = atrdisc;
    }

    public Pessoa() {}

    public Pessoa(
            Integer id,
            String nome,
            String morada,
            String email,
            String telefone,
            String noident,
            String nacionalidade,
            String atrdisc
    ) {
        this.id = id;
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telefone = telefone;
        this.noident = noident;
        this.nacionalidade = nacionalidade;
        this.atrdisc = atrdisc;
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

        Pessoa other = (Pessoa) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Pessoa[id=" + id + ", nome=" + nome + ", morada=" + morada + ", email=" + email +
                ", telefone=" + telefone + ", noident=" + noident + ", nacionalidade="
                 + nacionalidade + ", atrdisc=" + atrdisc + "]";
    }
}
