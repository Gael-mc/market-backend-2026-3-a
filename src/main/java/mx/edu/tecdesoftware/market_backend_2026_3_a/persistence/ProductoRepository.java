package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence;

import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

public class ProductoRepository {

    // Nota: Si usas Spring, no olvides poner @Autowired aquí para inyectar el repositorio
    private ProductoCrudRepository productoCrudRepository;

    // SELECT * FROM productos
    // Se castea de Iterable a Lista
    public List<Producto> getAll() { // CORRECCIÓN: Se agregaron los paréntesis () que faltaban
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria) {
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    /*
    SELECT * FROM producto
    WHERE cantidad_stock < ? AND estado = true;
    */
    public Optional<List<Producto>> getEscasos(int cantidad) {
        // CORRECCIÓN: Se eliminó el "estado:" que rompía la sintaxis de Java
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    // Obtener un producto dado el id
    public Optional<Producto> getProductoById(int idProducto) { // CORRECCIÓN: Se arregló el error de dedo "getProductoßyld"
        return productoCrudRepository.findById(idProducto);
    }

    /*
    INSERT INTO producto (nombre, cantidad_stock, estado, id_categoria)
    VALUES (?, ?, ?, ?);
    */
    // Guardar un producto
    public Producto save(Producto producto) {
        return productoCrudRepository.save(producto);
    }

    // Eliminar por id
    public void delete(int idProducto) {
        productoCrudRepository.deleteById(idProducto);
    }

} // CORRECCIÓN: Esta única llave al final cierra TODA la clase de manera limpia