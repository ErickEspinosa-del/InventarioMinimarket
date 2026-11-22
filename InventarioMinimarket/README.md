# Sistema de Inventario – Minimarket "La Esquina"

Programa de consola en Java para controlar el inventario de un minimarket.
Usa **encapsulamiento** (atributos privados con getters y setters), **herencia**
(`ProductoPerecedero` y `ProductoNoPerecedero` heredan de `Producto`) y
**polimorfismo** (cada tipo de producto tiene su propia versión de `mostrarInfo()`,
`calcularPrecioVenta()`, `puedeVenderse()` y `obtenerAlerta()`).

## Paquetes
- `modelo`: Producto, ProductoPerecedero, ProductoNoPerecedero
- `negocio`: Inventario
- `interfaz`: Main (menú)

## Qué hace
- Ver, agregar y eliminar productos.
- Comprar mercadería, controlando el presupuesto ($3000) y el espacio en bodega (1500 unidades).
- Vender productos: los que vencen en 7 días o menos tienen 30 % de descuento y los vencidos no se pueden vender.
- Mostrar alertas de stock bajo, productos por vencer y vencidos.
- Mostrar un resumen del inventario.

## Cómo ejecutar
Abrir la carpeta en IntelliJ IDEA (File → Open), elegir un JDK y ejecutar `src/interfaz/Main.java`.
