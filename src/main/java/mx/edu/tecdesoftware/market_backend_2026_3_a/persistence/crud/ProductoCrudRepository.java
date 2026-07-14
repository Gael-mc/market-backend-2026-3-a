package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.crud;

import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    // SELECT * FROM producto WHERE id_categoria = ? ORDER BY nombre ASC;
    List<Producto> findByIdcategoriaOrderByNombreAsc(int idCategoria);

    // SELECT * FROM producto WHERE cantidad_stock < ? AND estado = ?;
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}