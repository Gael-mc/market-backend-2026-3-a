package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    private String nombre;

    @Column(name = "id_categoria")
    private Integer idcategoria;

    @Column(name = "id_barras")
    private String codigoBarras;

    @Column(name = "id_venta")
    private double precioVenta;

    @Column(name = "id_stock")
    private Integer cantidadStock;

    private boolean estado;
}
