-- =====================================================================
-- Datos iniciales: Tienda de videojuegos
-- Base de datos: game_store_2026 (PostgreSQL)
--
-- Cómo usarlo:
--   1. Crear la base:  CREATE DATABASE game_store_2026;
--   2. Arrancar el backend una vez (Hibernate crea las tablas con
--      spring.jpa.hibernate.ddl-auto=update).
--   3. Ejecutar este script UNA sola vez:
--      psql -U postgres -d game_store_2026 -f seed_videojuegos.sql
--
-- Productos con stock menor a 50 se mostrarán como "Poco stock".
-- =====================================================================

BEGIN;

-- 4 categorías
INSERT INTO categorias (descripcion, activo) VALUES
    ('Consolas', true),
    ('Videojuegos', true),
    ('Accesorios', true),
    ('Coleccionables', true);

-- 16 productos (precios en pesos mexicanos)
INSERT INTO productos (nombre, id_categoria, codigo_barras, precio_venta, cantidad_stock, estado) VALUES
    -- Consolas
    ('PlayStation 5 Slim 1 TB',           (SELECT id_categoria FROM categorias WHERE descripcion = 'Consolas'),       '7500000000011',  9999.00,  25, true),
    ('Xbox Series X 1 TB',                (SELECT id_categoria FROM categorias WHERE descripcion = 'Consolas'),       '7500000000012', 10499.00,  18, true),
    ('Nintendo Switch OLED',              (SELECT id_categoria FROM categorias WHERE descripcion = 'Consolas'),       '7500000000013',  7299.00,  40, true),
    ('Nintendo Switch Lite',              (SELECT id_categoria FROM categorias WHERE descripcion = 'Consolas'),       '7500000000014',  4499.00,  60, true),
    -- Videojuegos
    ('Zelda: Tears of the Kingdom',       (SELECT id_categoria FROM categorias WHERE descripcion = 'Videojuegos'),    '7500000000021',  1299.00,  85, true),
    ('EA Sports FC 25',                   (SELECT id_categoria FROM categorias WHERE descripcion = 'Videojuegos'),    '7500000000022',  1199.00, 120, true),
    ('Minecraft',                         (SELECT id_categoria FROM categorias WHERE descripcion = 'Videojuegos'),    '7500000000023',   699.00, 150, true),
    ('Mario Kart 8 Deluxe',               (SELECT id_categoria FROM categorias WHERE descripcion = 'Videojuegos'),    '7500000000024',  1199.00,  95, true),
    -- Accesorios
    ('Control DualSense',                 (SELECT id_categoria FROM categorias WHERE descripcion = 'Accesorios'),     '7500000000031',  1599.00,  70, true),
    ('Control inalámbrico Xbox',          (SELECT id_categoria FROM categorias WHERE descripcion = 'Accesorios'),     '7500000000032',  1399.00,  65, true),
    ('Audífonos gamer con micrófono',     (SELECT id_categoria FROM categorias WHERE descripcion = 'Accesorios'),     '7500000000033',   899.00, 110, true),
    ('Tarjeta microSD 256 GB',            (SELECT id_categoria FROM categorias WHERE descripcion = 'Accesorios'),     '7500000000034',   549.00, 200, true),
    -- Coleccionables
    ('Figura amiibo Link',                (SELECT id_categoria FROM categorias WHERE descripcion = 'Coleccionables'), '7500000000041',   399.00,  35, true),
    ('Funko Pop! Master Chief',           (SELECT id_categoria FROM categorias WHERE descripcion = 'Coleccionables'), '7500000000042',   349.00,  48, true),
    ('Peluche Pikachu 30 cm',             (SELECT id_categoria FROM categorias WHERE descripcion = 'Coleccionables'), '7500000000043',   499.00,  55, true),
    ('Taza Pac-Man',                      (SELECT id_categoria FROM categorias WHERE descripcion = 'Coleccionables'), '7500000000044',   249.00,  80, true);

COMMIT;
