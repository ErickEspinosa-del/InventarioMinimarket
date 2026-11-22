package interfaz;

import modelo.Producto;
import modelo.ProductoNoPerecedero;
import modelo.ProductoPerecedero;
import negocio.Inventario;

import java.util.Locale;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US); // para escribir decimales con punto, ej: 1.50


        Inventario inventario = new Inventario(3000, 1500);
        cargarDatos(inventario);

        int opcion;
        do {
            System.out.println();
            System.out.println("===== MINIMARKET LA ESQUINA =====");
            System.out.println("1. Ver productos");
            System.out.println("2. Agregar producto");
            System.out.println("3. Comprar mercaderia (entrada)");
            System.out.println("4. Vender producto (salida)");
            System.out.println("5. Ver alertas");
            System.out.println("6. Ver resumen");
            System.out.println("7. Eliminar producto");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    inventario.mostrarProductos();
                    break;
                case 2:
                    agregarProducto(sc, inventario);
                    break;
                case 3:
                    System.out.print("Codigo: ");
                    String codCompra = sc.nextLine();
                    System.out.print("Cantidad: ");
                    int cantCompra = sc.nextInt();
                    inventario.comprarProducto(codCompra, cantCompra);
                    break;
                case 4:
                    System.out.print("Codigo: ");
                    String codVenta = sc.nextLine();
                    System.out.print("Cantidad: ");
                    int cantVenta = sc.nextInt();
                    inventario.venderProducto(codVenta, cantVenta);
                    break;
                case 5:
                    inventario.mostrarAlertas();
                    break;
                case 6:
                    inventario.mostrarResumen();
                    break;
                case 7:
                    System.out.print("Codigo: ");
                    String codEliminar = sc.nextLine();
                    inventario.eliminarProducto(codEliminar);
                    break;
                case 0:
                    System.out.println("Gracias por usar el sistema.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);

        sc.close();
    }


    public static void agregarProducto(Scanner sc, Inventario inventario) {
        System.out.print("Tipo (1 = Perecedero, 2 = No perecedero): ");
        int tipo = sc.nextInt();
        sc.nextLine();
        System.out.print("Codigo: ");
        String codigo = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Precio de compra: ");
        double compra = sc.nextDouble();
        System.out.print("Precio de venta: ");
        double venta = sc.nextDouble();
        System.out.print("Stock: ");
        int stock = sc.nextInt();
        System.out.print("Stock minimo: ");
        int minimo = sc.nextInt();
        sc.nextLine();

        Producto nuevo;
        if (tipo == 1) {
            System.out.print("Dias para vencer: ");
            int dias = sc.nextInt();
            sc.nextLine();
            System.out.print("Necesita refrigeracion? (s/n): ");
            boolean refrigerado = sc.nextLine().equalsIgnoreCase("s");
            nuevo = new ProductoPerecedero(codigo, nombre, compra, venta, stock, minimo, dias, refrigerado);
        } else {
            System.out.print("Categoria: ");
            String categoria = sc.nextLine();
            nuevo = new ProductoNoPerecedero(codigo, nombre, compra, venta, stock, minimo, categoria);
        }
        inventario.agregarProducto(nuevo);
    }


    public static void cargarDatos(Inventario inventario) {
        inventario.agregarProducto(new ProductoPerecedero("P001", "Leche entera 1L", 0.85, 1.10, 60, 20, 3, true));
        inventario.agregarProducto(new ProductoPerecedero("P002", "Yogurt 200g", 0.55, 0.80, 40, 15, 20, true));
        inventario.agregarProducto(new ProductoPerecedero("P003", "Pan de molde", 1.40, 1.95, 12, 15, 6, false));
        inventario.agregarProducto(new ProductoPerecedero("P004", "Queso fresco", 2.30, 3.20, 18, 10, -1, true));
        inventario.agregarProducto(new ProductoNoPerecedero("N001", "Arroz 2kg", 1.80, 2.35, 120, 40, "Abarrotes"));
        inventario.agregarProducto(new ProductoNoPerecedero("N002", "Aceite 1L", 2.10, 2.75, 25, 30, "Abarrotes"));
        inventario.agregarProducto(new ProductoNoPerecedero("N003", "Atun en lata", 1.05, 1.45, 150, 50, "Abarrotes"));
        inventario.agregarProducto(new ProductoNoPerecedero("N004", "Detergente 1kg", 2.60, 3.40, 45, 15, "Limpieza"));
        System.out.println("Datos de ejemplo cargados.");
    }
}
