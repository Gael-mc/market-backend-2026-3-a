package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence;

import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.Product;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.repository.ProductRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Producto;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ProductoRepository implements ProductRepository {

    // Nota: Si usas Spring, no olvides poner @Autowired aquí para inyectar el repositorio
    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper productMapper;

    // SELECT * FROM productos
    // Se castea de Iterable a Lista
    public List<Product> getAll() { // CORRECCIÓN: Se agregaron los paréntesis () que faltaban
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getCategory(int categoryId) {
        return Optional.empty();
    }

    public Optional <List<Product>> getByCategory(int categoryId) {
        List<Producto> productos= productoCrudRepository.findByIdcategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
    }

    /*
    SELECT * FROM producto
    WHERE cantidad_stock < ? AND estado = true;
    */
    public Optional<List<Product>> getScarceProducts(int quantity) {
        Optional<List<Producto>> productos= productoCrudRepository.findCantidadStockLessThanAndEstado(
                quantity, true);
        return Optional.of(productMapper.toProducts(productos.get()));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return Optional.empty();
    }

    // Obtener un producto dado el id
    public Optional<Product> getProducto(int productId) { // CORRECCIÓN: Se arregló el error de dedo "getProductoßyld"
        return productoCrudRepository.findById(productId)
                .map (producto -> productMapper.toProduct(producto));
    }

    /*
    INSERT INTO producto (nombre, cantidad_stock, estado, id_categoria)
    VALUES (?, ?, ?, ?);
    */
    // Guardar un producto
    public Product save(Product product) {
            Producto producto = productMapper.toProducto(product);
        return productMapper.toProduct(productoCrudRepository.save(producto));
    }

    // Eliminar por id
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);

    }

} // CORRECCIÓN: Esta única llave al final cierra TODA la clase de manera limpia