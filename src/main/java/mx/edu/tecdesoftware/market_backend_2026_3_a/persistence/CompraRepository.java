package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence;

import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.repository.PurchaseRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.crud.CompraCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Compra;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.mapper.PurchaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    private final CompraCrudRepository compraCrudRepository;
    private final PurchaseMapper mapper;

    public CompraRepository(CompraCrudRepository compraCrudRepository, PurchaseMapper mapper) {
        this.compraCrudRepository = compraCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Purchase> getAll() {
        List<Compra> compras = (List<Compra>) compraCrudRepository.findAll();
        return mapper.toPurchases(compras);
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(mapper::toPurchases);
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        // Paso crítico de integridad referencial: cada producto debe
        // referenciar a la compra principal antes de persistir en cascada.
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}