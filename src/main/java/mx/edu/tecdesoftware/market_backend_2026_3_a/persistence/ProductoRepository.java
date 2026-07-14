package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence;

import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.Product;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.repository.ProductRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Producto;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper productMapper;

    // SELECT * FROM producto;
    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getCategory(int categoryId) {
        return Optional.empty();
    }

    // SELECT * FROM producto WHERE id_categoria = ? ORDER BY nombre ASC;
    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdcategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
    }

    /*
    SELECT * FROM producto
    WHERE cantidad_stock < ? AND estado = true;
    */
    @Override
    public Optional<List<Product>> getScarceProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository
                .findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> productMapper.toProducts(prods));
    }

    // SELECT * FROM producto WHERE id_producto = ?;
    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId)
                .map(producto -> productMapper.toProduct(producto));
    }

    /*
    INSERT INTO producto (nombre, id_categoria, codigo_barras, precio_venta, cantidad_stock, estado)
    VALUES (?, ?, ?, ?, ?, ?);
    */
    @Override
    public Product save(Product product) {
        Producto producto = productMapper.toProducto(product);
        return productMapper.toProduct(productoCrudRepository.save(producto));
    }

    // DELETE FROM producto WHERE id_producto = ?;
    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }
}