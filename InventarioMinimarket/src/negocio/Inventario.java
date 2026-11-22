package negocio;

import modelo.Producto;

import java.util.ArrayList;


public class Inventario {


    private ArrayList<Producto> productos;
    private double presupuesto;      // dinero disponible para comprar en el mes
    private int capacidadBodega;     // maximo de unidades que caben en bodega

    public Inventario(double presupuesto, int capacidadBodega) {
        this.productos = new ArrayList<>();
        this.presupuesto = presupuesto;
        this.capacidadBodega = capacidadBodega;
    }

    public void agregarProducto(Producto p) {
        if (buscarProducto(p.getCodigo()) != null) {
            System.out.println("Error: ya existe un producto con el codigo " + p.getCodigo());
            return;
        }
        if (contarUnidades() + p.getStock() > capacidadBodega) {
            System.out.println("Error: no hay espacio en la bodega");
            return;
        }
        productos.add(p);
        System.out.println("Producto agregado: " + p.getNombre());
    }

    public Producto buscarProducto(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        for (Producto p : productos) {
            p.mostrarInfo(); // cada objeto usa SU version de mostrarInfo()
        }
    }

    // Entrada de mercaderia (compra al proveedor)
    public void comprarProducto(String codigo, int cantidad) {
        Producto p = buscarProducto(codigo);
        if (p == null) {
            System.out.println("Error: el producto no existe");
            return;
        }
        double costo = cantidad * p.getPrecioCompra();
        if (costo > presupuesto) {
            System.out.printf("Error: no alcanza el presupuesto. Compra: $%.2f | Disponible: $%.2f%n", costo, presupuesto);
            return;
        }
        if (contarUnidades() + cantidad > capacidadBodega) {
            System.out.println("Error: no hay espacio en la bodega");
            return;
        }
        p.setStock(p.getStock() + cantidad);
        presupuesto = presupuesto - costo;
        System.out.printf("Compra registrada. Costo: $%.2f | Presupuesto restante: $%.2f%n", costo, presupuesto);
    }

    // Salida de mercaderia (venta al cliente)
    public void venderProducto(String codigo, int cantidad) {
        Producto p = buscarProducto(codigo);
        if (p == null) {
            System.out.println("Error: el producto no existe");
            return;
        }
        if (!p.puedeVenderse()) {
            System.out.println("Error: " + p.getNombre() + " esta vencido y no se puede vender");
            return;
        }
        if (cantidad > p.getStock()) {
            System.out.println("Error: stock insuficiente, solo hay " + p.getStock() + " unidades");
            return;
        }
        double total = cantidad * p.calcularPrecioVenta();
        p.setStock(p.getStock() - cantidad);
        System.out.printf("Venta registrada. Total a cobrar: $%.2f%n", total);
    }

    public void eliminarProducto(String codigo) {
        Producto p = buscarProducto(codigo);
        if (p == null) {
            System.out.println("Error: el producto no existe");
        } else {
            productos.remove(p);
            System.out.println("Producto eliminado: " + p.getNombre());
        }
    }

    public void mostrarAlertas() {
        int contador = 0;
        for (Producto p : productos) {
            String alerta = p.obtenerAlerta();
            if (!alerta.equals("")) {
                System.out.println("[" + p.getCodigo() + "] " + p.getNombre() + ": " + alerta);
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("No hay alertas, todo esta en orden.");
        }
    }

    public int contarUnidades() {
        int total = 0;
        for (Producto p : productos) {
            total = total + p.getStock();
        }
        return total;
    }

    public double calcularValorInventario() {
        double total = 0;
        for (Producto p : productos) {
            total = total + p.calcularValorTotal();
        }
        return total;
    }

    public void mostrarResumen() {
        System.out.println("Productos registrados: " + productos.size());
        System.out.printf("Valor del inventario: $%.2f%n", calcularValorInventario());
        System.out.println("Unidades en bodega: " + contarUnidades() + " de " + capacidadBodega);
        System.out.printf("Presupuesto disponible: $%.2f%n", presupuesto);
    }
}
