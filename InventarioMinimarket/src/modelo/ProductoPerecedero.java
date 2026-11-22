package modelo;


public class ProductoPerecedero extends Producto {

    private int diasParaVencer;
    private boolean refrigerado;

    public ProductoPerecedero(String codigo, String nombre, double precioCompra, double precioVenta,
                              int stock, int stockMinimo, int diasParaVencer, boolean refrigerado) {
        super(codigo, nombre, precioCompra, precioVenta, stock, stockMinimo); // llama al constructor del padre
        this.diasParaVencer = diasParaVencer;
        this.refrigerado = refrigerado;
    }

    public boolean estaVencido() {
        return diasParaVencer < 0;
    }


    public boolean estaPorVencer() {
        return diasParaVencer >= 0 && diasParaVencer <= 7;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        String texto;
        if (estaVencido()) {
            texto = "     -> Perecedero, VENCIDO";
        } else {
            texto = "     -> Perecedero, vence en " + diasParaVencer + " dias";
        }
        if (refrigerado) {
            texto = texto + ", refrigerado";
        }
        System.out.println(texto);
    }

    // Si esta por vencer se vende con 30% de descuento
    @Override
    public double calcularPrecioVenta() {
        if (estaPorVencer()) {
            return getPrecioVenta() * 0.70;
        }
        return getPrecioVenta();
    }

    // Un producto vencido no se puede vender
    @Override
    public boolean puedeVenderse() {
        return !estaVencido();
    }

    @Override
    public String obtenerAlerta() {
        if (estaVencido()) {
            return "VENCIDO, retirar de la percha";
        }
        String alerta = super.obtenerAlerta(); // revisa el stock bajo (codigo del padre)
        if (estaPorVencer()) {
            if (!alerta.equals("")) {
                alerta = alerta + " / ";
            }
            alerta = alerta + "Vence en " + diasParaVencer + " dias, vender con 30% de descuento";
        }
        return alerta;
    }

    public int getDiasParaVencer() {
        return diasParaVencer;
    }

    public void setDiasParaVencer(int diasParaVencer) {
        this.diasParaVencer = diasParaVencer;
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

    public void setRefrigerado(boolean refrigerado) {
        this.refrigerado = refrigerado;
    }
}
