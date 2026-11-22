package modelo;


public class Producto {


    private String codigo;
    private String nombre;
    private double precioCompra;
    private double precioVenta;
    private int stock;
    private int stockMinimo;


    public Producto(String codigo, String nombre, double precioCompra, double precioVenta, int stock, int stockMinimo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }


    public void mostrarInfo() {
        System.out.printf("%s | %-15s | Stock: %3d (minimo %d) | Precio: $%.2f%n",
                codigo, nombre, stock, stockMinimo, calcularPrecioVenta());
    }


    public double calcularPrecioVenta() {
        return precioVenta;
    }


    public double calcularValorTotal() {
        return stock * precioCompra;
    }

    public boolean tieneStockBajo() {
        return stock <= stockMinimo;
    }


    public boolean puedeVenderse() {
        return true;
    }


    public String obtenerAlerta() {
        if (tieneStockBajo()) {
            return "Stock bajo, hay que hacer pedido";
        }
        return "";
    }


    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        if (precioCompra > 0) {
            this.precioCompra = precioCompra;
        }
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        if (precioVenta > 0) {
            this.precioVenta = precioVenta;
        }
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        }
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
}
