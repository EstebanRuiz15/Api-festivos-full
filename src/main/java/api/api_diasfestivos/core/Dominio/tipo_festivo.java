package api.api_diasfestivos.core.Dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo")
public class tipo_festivo {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "tipo", length = 100, nullable = false)
    private String tipo;

    public tipo_festivo() {

    }

    public tipo_festivo(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
