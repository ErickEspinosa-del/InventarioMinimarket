package modelo;


public class ProductoNoPerecedero extends Producto {

    private String categoria;

    public ProductoNoPerecedero(String codigo, String nombre, double precioCompra, double precioVenta,
                                int stock, int stockMinimo, String categoria) {
        super(codigo, nombre, precioCompra, precioVenta, stock, stockMinimo);
        this.categoria = categoria;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("     -> No perecedero, categoria: " + categoria);
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
