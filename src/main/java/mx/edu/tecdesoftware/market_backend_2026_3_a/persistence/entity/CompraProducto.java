package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity;
import jakarta.persistence.*;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.CompraProductoPK;

@Entity
@Table(name = "compras_productos")
public class CompraProducto {

    @EmbeddedId
    private CompraProductoPK id;   // ← reemplaza el id simple

    private Integer cantidad;
    private Double total;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_compra" ,
    insertable = false , updatable = false)
    private Compra compra;


    @ManyToOne
    @JoinColumn(name = "id_producto" ,insertable = false , updatable = false)
    private Producto producto;

    public CompraProductoPK getId() {
        return id;
    }

    public void setId(CompraProductoPK id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
}