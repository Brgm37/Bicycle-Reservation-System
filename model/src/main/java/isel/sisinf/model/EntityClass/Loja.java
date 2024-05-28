package isel.sisinf.model.EntityClass;

import jakarta.persistence.*;
import java.util.Objects;

interface ILoja {
    Integer getCodigo();
    void setCodigo(Integer codigo);

    String getEmail();
    void setEmail(String email);

    String getEndereco();
    void setEndereco(String endereco);

    String getLocalidade();
    void setLocalidade(String localidade);

    Pessoa getGestor();
    void setGestor(Pessoa gestor);
}

@Entity
@Table(name = "LOJA")
public class Loja implements ILoja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false, unique = true, length = 40)
    private String email;

    @Column(nullable = false, length = 100)
    private String endereco;

    @Column(nullable = false, length = 30)
    private String localidade;

    @ManyToOne
    @JoinColumn(name = "gestor", nullable = false)
    private Pessoa gestor;

    public Loja() {}

    public Loja(Integer codigo, String email, String endereco, String localidade, Pessoa gestor) {
        this.codigo = codigo;
        this.email = email;
        this.endereco = endereco;
        this.localidade = localidade;
        this.gestor = gestor;
    }

    @Override
    public Integer getCodigo() {
        return codigo;
    }

    @Override
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getEndereco() {
        return endereco;
    }

    @Override
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String getLocalidade() {
        return localidade;
    }

    @Override
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    @Override
    public Pessoa getGestor() {
        return gestor;
    }

    @Override
    public void setGestor(Pessoa gestor) {
        this.gestor = gestor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
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

        Loja other = (Loja) obj;
        return Objects.equals(codigo, other.codigo);
    }

    @Override
    public String toString() {
        return "Loja[codigo=" + codigo + ", email=" + email + ", endereco=" + endereco + ", localidade=" + localidade +
                ", gestor=" + gestor.getId() + "]";
    }
}
