
package Main;

import entities.Categoria;
import entities.Pedido;
import entities.Producto;
import entities.Usuario;
import enums.Estado;
import enums.FormaPago;
import enums.Rol;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import service.CategoriaService;
import service.PedidoService;
import service.ProductoService;
import service.UsuarioService;

public class Main {
    //Primero instancio los servicios globales de la aplicación
    private static CategoriaService categoriaService = new CategoriaService();
    private static ProductoService productoService = new ProductoService();
    private static UsuarioService usuarioService = new UsuarioService();
    private static PedidoService pedidoService = new PedidoService();
    
    public static void main(String[] args) {
        //Cargo los datos
        cargarDatosIniciales();

        //Lanzo el Menu
        ejecutarMenuPrincipal();
       
        
    }
    
    private static void cargarDatosIniciales(){
        //Instancio 3 Categorias
        Categoria bebSinAlc = new Categoria("Bebidas sin alcohol", "aguas saborizadas de la marca Sepsi");
        Categoria pasta = new Categoria("Pastas rellenas", "platos de pastas artesanales, no incluye salsa");
        Categoria postre = new Categoria("Postres", "porciones de tartas dulces");
        
        //Las guardo en el servicio de categorías
        categoriaService.agregarCategoria(bebSinAlc);
        categoriaService.agregarCategoria(pasta);
        categoriaService.agregarCategoria(postre);
        
        //Instancio 6 Productos
        Producto bebManzana = new Producto("Sepsi Manzana", 7000.0, "agua saborizada de manzana", 30, bebSinAlc);
        Producto bebPomelo = new Producto("Sepsi Pomelo", 7000.0, "agua saborizada de pomelo", 15, bebSinAlc);
        
        Producto sorCalabaza = new Producto("Sorrentinos de Calabaza", 25000.0, "pasta casera rellena de calabaza, queso azul y nuez", 5, pasta);
        Producto ravVerd = new Producto("Ravioles de Verdura", 20000.0, "pasta casera rellena de acelga y queso", 4, pasta);
        
        Producto tartaLimon = new Producto("Lemon Pie", 5000.0, "Tarta dulce con mouse de limón y merengue", 8, postre);
        Producto tartaManzana = new Producto("Tarta de Manzana", 5000.0, "Tarta dulce de manzanas, viene con bocha de americana", 5, postre);
        
        //Los guardo en el servicio de Productos
        productoService.agregarProducto(bebManzana);
        productoService.agregarProducto(bebPomelo);
        productoService.agregarProducto(sorCalabaza);
        productoService.agregarProducto(ravVerd);
        productoService.agregarProducto(tartaLimon);
        productoService.agregarProducto(tartaManzana);
        
        //Agrego los productos a las categorías correspondientes
        bebSinAlc.agregarProducto(bebManzana);
        bebSinAlc.agregarProducto(bebPomelo);
        
        pasta.agregarProducto(sorCalabaza);
        pasta.agregarProducto(ravVerd);
        
        postre.agregarProducto(tartaLimon);
        postre.agregarProducto(tartaManzana);
        
        //Instancio 2 Usuarios
        Usuario admin1 = new Usuario("Julio", "Perez", "juliop@hotmail.com", "+54 9 3445258866", "julPe25", Rol.ADMIN);
        Usuario user1 = new Usuario("Maria", "Aguilera", "mari.aguilera@hotmail.com1", "+54 9 3445778845", "MarAg", Rol.USUARIO);
        
        //Los guardo en el servicio de Usuario
        usuarioService.agregarUsuario(user1);
        usuarioService.agregarUsuario(admin1);
        
        //Instancio 4 Pedidos
        Pedido pAdmin1 = new Pedido(Estado.CONFIRMADO, FormaPago.TARJETA, admin1);
        Pedido pAdmin2 = new Pedido(Estado.PENDIENTE, FormaPago.TRANSFERENCIA, admin1);
        
        Pedido pUser1 = new Pedido(Estado.TERMINADO, FormaPago.EFECTIVO, user1);
        Pedido pUser2 = new Pedido(Estado.CANCELADO, FormaPago.EFECTIVO, user1);
        
        //Los guardo en el servicio de Usuario
        pedidoService.agregarPedido(pAdmin1);
        pedidoService.agregarPedido(pAdmin2);
        pedidoService.agregarPedido(pUser1);
        pedidoService.agregarPedido(pUser2);
        
        //Agrego los pedidos a cada usuario
        admin1.agregarPedido(pAdmin1);
        admin1.agregarPedido(pAdmin2);
        
        user1.agregarPedido(pUser1);
        user1.agregarPedido(pUser2);
    }
    
    public static void ejecutarMenuPrincipal(){
        Scanner Sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n=================================");
            System.out.println("      FOOD STORE - MENU PRINCIPAL");
            System.out.println("=================================");
            System.out.println("1. Gestión de Categorías");
            System.out.println("2. Gestión de Productos");
            System.out.println("3. Gestión de Usuarios");
            System.out.println("4. Gestión de Pedidos");
            System.out.println("0. Salir del Sistema");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(Sc.nextLine());
                
                switch (opcion) {
                    case 1:
                        System.out.println("Seleccionó Gestión de Categorías");
                        menuCategorias(Sc);
                        break;
                        
                    case 2:
                        System.out.println("Seleccionó Gestión de Productos");
                        menuProductos(Sc);
                        break;
                    case 3:
                        System.out.println("Seleccionó Gestión de Usuarios");
                        menuUsuarios(Sc);
                        break;
                    case 4:
                        System.out.println("Seleccionó Gestión de Pedidos");
                        menuPedidos(Sc);
                        break;
                    case 0:
                        System.out.println("👋 Saliendo del sistema. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("❌ Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ ERROR: Debe ingresar un número entero.");
            }
        } while (opcion != 0);
    }
    
    public static void menuCategorias(Scanner Sc){
        int opcionCat = 0;
        
        do {
             System.out.println("1. Listar Categorías");
             System.out.println("2. Crear Categoría");
             System.out.println("3. Editar Categoría");
             System.out.println("4. Eliminar Categoría");
             System.out.println("0. Volver al Menú Principal");
             System.out.print("Seleccione una opción: ");

            try {
                opcionCat = Integer.parseInt(Sc.nextLine());
                switch (opcionCat) {
                    case 1:
                        System.out.println("\n--- LISTADO DE CATEGORÍAS ---");
                        List<Categoria> categoriasAMostrar = categoriaService.listarCategorias();

                        //Si no hay registros, se informa
                        if (categoriasAMostrar.isEmpty()) {
                            System.out.println("No hay categorías cargadas.");
                        } else {
                            //Muestro filas con id, nombre y descripción
                            for (Categoria cat : categoriasAMostrar) {
                                System.out.format("ID: %-6d | Nombre: %-20s | Descripción: %s\n", 
                                cat.getId(), cat.getNombre(), cat.getDescripcion());
                            }
                        }
                        System.out.println("---------------------------------------");
                        break;
                        
                    case 2:
                        System.out.println("\n--- ALTA DE NUEVA CATEGORÍA ---");
        
                        String nombre = "";
                        String descripcion = "";
                        boolean altaExitosa = false;

                        while (!altaExitosa) {
                            nombre = ""; 
                            descripcion = "";
                            
                            //Solicito nombre con validación de no vacío
                            while (nombre.trim().isEmpty()) {
                                System.out.print("Ingrese el nombre de la categoría: ");
                                nombre = Sc.nextLine();
                                if (nombre.trim().isEmpty()) {
                                    System.out.println("❌ El nombre no puede estar vacío. Intente nuevamente.");
                                } 
                            }

                            //Solicito descripción con validación de no vacío
                            while (descripcion.trim().isEmpty()) {
                                System.out.print("Ingrese la descripción de la categoría: ");
                                descripcion = Sc.nextLine();
                                if (descripcion.trim().isEmpty()) {
                                    System.out.println("❌ La descripción no puede estar vacía. Intente nuevamente.");
                                }
                            }
                            
                            try {
                                // Envio los datos al servicio
                                Categoria catCreada = categoriaService.crearCategoria(nombre, descripcion);

                                // Si llega a esta línea es porque no saltó la excepción de duplicado
                                System.out.println("\n✅ Categoría creada con éxito.");
                                System.out.println("ID asignado automáticamente por el sistema: " + catCreada.getId());
        
                                altaExitosa = true; // Corta el bucle principal de reintentos

                                } catch (IllegalArgumentException e) {
                                    // Informa que el nombre está repetido y vuelve a empezar el bucle pidiendo otro nombre
                                    System.out.println("\n❌ " + e.getMessage() + " Por favor, elija otro.");
                            }
                        }
                        System.out.println("---------------------------------------");
                        break;
                        
                    case 3:
                        System.out.println("\n--- EDITAR CATEGORÍA EXISTENTE ---");
                        System.out.print("Ingrese el ID de la categoría que desea editar: ");
                        Long idIngresado = null;
        
                        try {
                            idIngresado = Long.parseLong(Sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("❌ ERROR: El ID debe ser un número válido.");
                            return;
                        }

                        Categoria categoriaAEditar = categoriaService.buscarCategoriaPorId(idIngresado);

                        //Si el ID no existe o está eliminado de forma lógica, se informa y frena
                        if (categoriaAEditar == null || categoriaAEditar.isEliminado()) {
                            System.out.println("❌ ERROR: No se encontró ninguna categoría activa con el ID " + idIngresado);
                        }
                        
                        //Si llegó acá, la categoría existe y está activa. Muestro los datos actuales:
                        System.out.println("\nCategoría seleccionada: " + categoriaAEditar.getNombre());
                        System.out.println("Descripción actual: " + categoriaAEditar.getDescripcion());
                        
                

                        //Solicito los nuevos datos con validación de no vacío (igual que en el Alta)
                        String nuevoNombre = "";
                        while (nuevoNombre.trim().isEmpty()) {
                            System.out.print("Ingrese el NUEVO nombre: ");
                            nuevoNombre = Sc.nextLine();
                            if (nuevoNombre.trim().isEmpty()) {
                                System.out.println("❌ El nombre no puede estar vacío.");
                            }
                        }

                        String nuevaDescripcion = "";
                        while (nuevaDescripcion.trim().isEmpty()) {
                            System.out.print("Ingrese la NUEVA descripción: ");
                            nuevaDescripcion = Sc.nextLine();
                            if (nuevaDescripcion.trim().isEmpty()) {
                                System.out.println("❌ La descripción no puede estar vacía.");
                            }
                        }

                        //Envio la actualización a la capa de servicio
                        try {
                            categoriaService.editarCategoria(categoriaAEditar, nuevoNombre, nuevaDescripcion);

                            System.out.println("\n✅ Categoría modificada con éxito.");
                            System.out.println("Datos actualizados: " + categoriaAEditar.toString());
            
                        } catch (IllegalArgumentException e) {
                            //Captura si las validaciones internas de los setters de Categoria rechazan el dato
                            System.out.println("\n❌ Error al guardar los cambios: " + e.getMessage());
                        }
                        System.out.println("---------------------------------------");
                        break;
                        
                    case 4:
                        System.out.println("\n--- ELIMINAR CATEGORÍA EXISTENTE ---");
                        System.out.print("Ingrese el ID de la categoría a eliminar: ");

                        try {
                            idIngresado = Long.parseLong(Sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("❌ ERROR: El ID debe ser un número válido.");
                            return;
                        }
                        
                        //Busco la categoría por ID
                        Categoria categoriaAEliminar = categoriaService.buscarCategoriaPorId(idIngresado);

                        //Verifico si existe y si ya no estaba eliminada de antes
                        if (categoriaAEliminar == null || categoriaAEliminar.isEliminado()) {
                            System.out.println("❌ ERROR: No se encontró ninguna categoría activa con el ID " + idIngresado);
                            return;
                        }
                        
                        System.out.println("\nCategoría encontrada: " + categoriaAEliminar.getNombre());
                        System.out.println("Descripción: " + categoriaAEliminar.getDescripcion());
        
                        //Pido confirmación (S/N)
                        System.out.print("\n⚠️ ¿Está seguro de que desea eliminar esta categoría? (S/N): ");
                        String confirmacion = Sc.nextLine().trim().toUpperCase();

                        if (confirmacion.equals("S")) {
                            try {
                                //Ejecuto la baja lógica en el Service
                                categoriaService.eliminarCategoria(categoriaAEliminar);
                                System.out.println("\n✅ Categoría '" + categoriaAEliminar.getNombre() + "' eliminada con éxito.");
                
                            } catch (IllegalStateException e) {
                            //Si tiene productos, frena e informa el error
                            System.out.println("\n❌ " + e.getMessage());
                            }
                        } else {
                            System.out.println("\n❌ Operación cancelada. La categoría no sufrió modificaciones.");
                        }
                        
                        System.out.println("---------------------------------------");
                        break;
                        
                    case 0:
                         break;
                         
                    default:
                        System.out.println("❌ Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ ERROR: Ingrese un número.");
            }
        } while (opcionCat != 0);
    }
    
    public static void menuProductos(Scanner Sc){
        int opcionProd = 0;
        
        do {
             System.out.println("1. Listar Productos");
             System.out.println("2. Crear Producto");
             System.out.println("3. Editar Producto");
             System.out.println("4. Eliminar Producto");
             System.out.println("0. Volver al Menú Principal");
             System.out.print("Seleccione una opción: ");

            try {
                opcionProd = Integer.parseInt(Sc.nextLine());
                switch (opcionProd) {
                    case 1:
                        System.out.println("\n--- LISTADO DE PRODUCTOS ---");
                        List<Producto> productosAMostrar = productoService.listarProductos();

                        //Si no hay registros, se informa
                        if (productosAMostrar.isEmpty()) {
                            System.out.println("No hay productos cargados.");
                        } else {
                            System.out.format("%-6s | %-25s | %-10s | %-6s | %s\n", "ID", "Nombre", "Precio", "Stock", "Categoría");
                            System.out.println("-------------------------------------------------------------------------------");
        
                            //Muestro filas iterando sobre la lista de productos
                            for (Producto prod : productosAMostrar) {
                                //Obtengo el nombre de la categoría asociada de forma segura
                                String nombreCat = (prod.getCategoria() != null) ? prod.getCategoria().getNombre() : "Sin Categoría";
            
                                System.out.format("ID: %-4d | %-25s | $%-9.2f | %-6d | %s\n", 
                                prod.getId(), prod.getNombre(), prod.getPrecio(), prod.getStock(), nombreCat);
                            }
                        }
                        System.out.println("-------------------------------------------------------------------------------");
                        break;
                        
                    case 2:
                        System.out.println("\n--- ALTA DE NUEVO PRODUCTO ---");
                        //SELECCIONO Y VALIDO DE CATEGORÍA
                        Categoria catSeleccionada = null;
                        while (catSeleccionada == null) {
                            System.out.print("Ingrese el ID de la categoría para el producto (0 para cancelar): ");
                            try {
                                long idCat = Long.parseLong(Sc.nextLine());
                                if (idCat == 0) {
                                    break;
                                }
                                
                                catSeleccionada = categoriaService.buscarCategoriaPorId(idCat);
                                
                                if (catSeleccionada == null || catSeleccionada.isEliminado()) {
                                    System.out.println("❌ ERROR: No existe una categoría activa con el ID " + idCat + ". Intente nuevamente.");
                                    catSeleccionada = null;
                                } else {
                                    System.out.println("✅ Categoría seleccionada: " + catSeleccionada.getNombre());
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("❌ ERROR: Debe ingresar un número de ID válido.");
                            }
                        }
                        
                        //Si se canceló la operación salimos del case
                        if (catSeleccionada == null) {
                            System.out.println("❌ Operación cancelada.");
                            System.out.println("---------------------------------------");
                            break;
                        }

                        //INGRESO DE DATOS 
                        System.out.print("Ingrese el nombre del producto: ");
                        String nomProd = Sc.nextLine();

                        System.out.print("Ingrese la descripción del producto: ");
                        String descProd = Sc.nextLine();

                        //INGRESO Y VALIDACIÓN DE PRECIO
                        double precioProd = -1;
                        while (precioProd < 0) {
                            System.out.print("Ingrese el precio del producto: ");
                            try {
                                precioProd = Double.parseDouble(Sc.nextLine());
                                if (precioProd < 0) {
                                    System.out.println("❌ El precio no puede ser negativo.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("❌ ERROR: Ingrese un precio numérico válido (ej: 4500.00).");
                            }
                        }

                        //INGRESO Y VALIDACIÓN DE STOCK
                        int stockProd = -1;
                        while (stockProd < 0) {
                            System.out.print("Ingrese el stock inicial del producto: ");
                            try {
                                stockProd = Integer.parseInt(Sc.nextLine());
                                if (stockProd < 0) {
                                    System.out.println("❌ El stock no puede ser negativo.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("❌ ERROR: Ingrese un número entero válido para el stock.");
                            }
                        }
                        
                        //LLAMADO AL SERVICIO Y CAPTURA DE EXCEPCIONES INTERNAS
                        try {
                            Producto nuevoProducto = productoService.crearProducto(
                                nomProd, precioProd, descProd, stockProd, catSeleccionada);
                            
                            System.out.println("\n✅ Producto creado con éxito.");
                            System.out.println("Datos del registro: " + nuevoProducto.toString());
                            
                        } catch (IllegalArgumentException e) {
                            System.out.println("\n❌ Error de validación: " + e.getMessage());
                        }
                        System.out.println("---------------------------------------");
                        break;
                        
                    case 3:
                        System.out.println("\n--- EDITAR PRODUCTO EXISTENTE ---");
                        System.out.print("Ingrese el ID del producto que desea editar: ");
                        Long idProdIngresado = null;
                        
                        try {
                            idProdIngresado = Long.parseLong(Sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("❌ ERROR: El ID debe ser un número válido.");
                            System.out.println("---------------------------------------");
                            break;
                        }

                        //Busco el producto en el servicio
                        Producto prodAEditar = productoService.buscarProductoPorId(idProdIngresado);

                        //Si el id no existe o está eliminado, se informa y se cancela
                        if (prodAEditar == null || prodAEditar.isEliminado()) {
                            System.out.println("❌ ERROR: No se encontró ningún producto activo con el ID " + idProdIngresado);
                            System.out.println("---------------------------------------");
                            break;
                        }

                        //Si llegó acá, el producto existe. Muestro su estado actual:
                        System.out.println("\nProducto seleccionado: " + prodAEditar.getNombre());
                        System.out.println("(Presione [ENTER] para mantener el valor actual)");

                        //EDITAR NOMBRE
                        System.out.print("Nombre actual [" + prodAEditar.getNombre() + "]: ");
                        String modNombre = Sc.nextLine();

                        //EDITAR DESCRIPCIÓN
                        System.out.print("Descripción actual [" + prodAEditar.getDescripcion() + "]: ");
                        String modDescripcion = Sc.nextLine();

                        //EDITAR PRECIO
                        double modPrecio = -1; 
                        System.out.print("Precio actual [$" + prodAEditar.getPrecio() + "]: ");
                        String precioInput = Sc.nextLine();

                        if (!precioInput.trim().isEmpty()) {
                            try {
                                modPrecio = Double.parseDouble(precioInput);
                            } catch (NumberFormatException e) {
                                System.out.println("❌ ERROR: Formato de precio inválido. Operación cancelada.");
                                System.out.println("---------------------------------------");
                                break; // Cancela si mete letras
                            }
                        }

                        //EDITAR STOCK
                        int modStock = -1; // -1 significa que el usuario decidió NO modificarlo
                        System.out.print("Stock actual [" + prodAEditar.getStock() + "]: ");
                        String stockInput = Sc.nextLine();

                        if (!stockInput.trim().isEmpty()) {
                            try {
                                modStock = Integer.parseInt(stockInput);
                            } catch (NumberFormatException e) {
                                System.out.println("❌ ERROR: Formato de stock inválido. Operación cancelada.");
                                System.out.println("---------------------------------------");
                                break; // Cancela si mete letras
                            }
                        }
                        
                        //EDITAR CATEGORÍA
                        Categoria modCategoria = null;
                        String nombreCatActual = (prodAEditar.getCategoria() != null) ? prodAEditar.getCategoria().getNombre() : "Sin Categoría";
                        System.out.print("Categoría actual [" + nombreCatActual + "] - Ingrese nuevo ID de Categoría (o Enter para mantener): ");
                        String catInput = Sc.nextLine();
                        
                        if (!catInput.trim().isEmpty()) {
                            try {
                                long idNuevaCat = Long.parseLong(catInput);
                                modCategoria = categoriaService.buscarCategoriaPorId(idNuevaCat);
                                
                                if (modCategoria == null || modCategoria.isEliminado()) {
                                    System.out.println("❌ ERROR: La categoría especificada no existe o está dada de baja. Se cancela la edición.");
                                    System.out.println("---------------------------------------");
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("❌ ERROR: ID de categoría inválido. Operación cancelada.");
                                System.out.println("---------------------------------------");
                                break;
                            }
                        }

                        //ENVÍO DE ACTUALIZACIONES AL SERVICIO Y CAPTURA DE EXCEPCIONES
                        try {
                            productoService.editarProducto(
                                prodAEditar, modNombre, modPrecio, modDescripcion, modStock, modCategoria);
                            
                            System.out.println("\n✅ Producto modificado con éxito.");
                            System.out.println("Datos actualizados: " + prodAEditar.toString());
                            
                        } catch (IllegalArgumentException e) {
                            // Aquí capturamos de manera segura tu mensaje: "ERROR. El precio no puede ser negativo", etc.
                            System.out.println("\n❌ Error al guardar los cambios: " + e.getMessage());
                            System.out.println("⚠️ La operación se canceló y el producto conservó sus valores anteriores.");
                        }
                        System.out.println("---------------------------------------");
                        break;
                        
                    case 4:
                        System.out.println("\n--- ELIMINAR PRODUCTO EXISTENTE ---");
                        System.out.print("Ingrese el ID del producto que desea eliminar: ");
                        Long idProdEliminar = null;
                        
                        try {
                            idProdEliminar = Long.parseLong(Sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("❌ ERROR: El ID debe ser un número válido.");
                            System.out.println("---------------------------------------");
                            break;
                        }

                        // Buscamos el producto en el servicio
                        Producto prodAEliminar = productoService.buscarProductoPorId(idProdEliminar);

                        // Criterio de aceptación: Si no existe o ya está eliminado, se informa y se cancela
                        if (prodAEliminar == null || prodAEliminar.isEliminado()) {
                            System.out.println("❌ ERROR: No se encontró ningún producto activo con el ID " + idProdEliminar);
                            System.out.println("---------------------------------------");
                            break;
                        }

                        // Mostramos el producto a eliminar para seguridad del operador
                        System.out.println("\nProducto encontrado: " + prodAEliminar.getNombre());
                        System.out.println("⚠️ ATENCIÓN: El producto se dará de baja del catálogo pero se conservará su historial.");
                        
                        // Criterio de aceptación: Pedir confirmación
                        System.out.print("¿Está seguro de que desea eliminar este producto? (S/N): ");
                        String confirmacion = Sc.nextLine().trim().toUpperCase();

                        if (confirmacion.equals("S")) {
                            productoService.eliminarProducto(prodAEliminar);
                            System.out.println("\n✅ El producto '" + prodAEliminar.getNombre() + "' fue eliminado con éxito del catálogo.");
                        } else {
                            System.out.println("\n❌ Operación cancelada. El producto permanece activo.");
                        }
                        
                        System.out.println("---------------------------------------");
                        break;
                        
                    case 0:
                         break;
                         
                    default:
                        System.out.println("❌ Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ ERROR: Ingrese un número.");
            }
        } while (opcionProd != 0);
    }
    
    public static void menuUsuarios(Scanner Sc){
        int opcionUsuario = 0;
        
        do {
             System.out.println("1. Listar Usuario");
             System.out.println("2. Crear Usuario");
             System.out.println("3. Editar Usuario");
             System.out.println("4. Eliminar Usuario");
             System.out.println("0. Volver al Menú Principal");
             System.out.print("Seleccione una opción: ");

            try {
                opcionUsuario = Integer.parseInt(Sc.nextLine());
                switch (opcionUsuario) {
                    case 1:
                        System.out.println("\n--- LISTADO DE USUARIOS ---");
                        //Obtengo la lista de usuarios activos desde el servicio
                        ArrayList<Usuario> usuariosActivos = usuarioService.listarUsuario();
                        
                        //Si no hay usuarios, se informa adecuadamente
                        if (usuariosActivos.isEmpty()) {
                            System.out.println("ℹ️ No hay usuarios registrados o activos en el sistema actualmente.");
                        } else {
                            //Se listan mostrando sus datos formatados
                            for (Usuario user : usuariosActivos) 
                                System.out.println(user.toString());
                            }
                        
                        System.out.println("-------------------------------------------------------------------------------");
                        break;
                        
                    case 2:
                        System.out.println("\n--- ALTA DE NUEVO USUARIO ---");
                        //CAPTURA Y VALIDACIÓN DEL NOMBRE
                        String nuevoNombre = "";
                        while (nuevoNombre.trim().isEmpty()) {
                            System.out.print("Ingrese el nombre: ");
                            nuevoNombre = Sc.nextLine();
                            if (nuevoNombre.trim().isEmpty()) {
                                System.out.println("❌ ERROR: El nombre no puede estar vacío.");
                            }
                        }

                        //CAPTURA Y VALIDACIÓN DEL APELLIDO
                        String nuevoApellido = "";
                        while (nuevoApellido.trim().isEmpty()) {
                            System.out.print("Ingrese el apellido: ");
                            nuevoApellido = Sc.nextLine();
                            if (nuevoApellido.trim().isEmpty()) {
                                System.out.println("❌ ERROR: El apellido no puede estar vacío.");
                            }
                        }

                        //CAPTURA Y VALIDACIÓN DE MAIL (Formato básico y no vacío)
                        String nuevoMail = "";
                        String regexEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
                        
                        while (true) {
                            System.out.print("Ingrese el correo electrónico: ");
                            nuevoMail = Sc.nextLine().trim();
                            
                            if (nuevoMail.isEmpty()) {
                                System.out.println("❌ ERROR: El correo electrónico no puede estar vacío.");
                            } else if (!nuevoMail.matches(regexEmail)) {
                                System.out.println("❌ ERROR: El formato del correo electrónico es inválido (Ej: usuario@dominio.com).");
                            } else {
                                break; 
                            }
                        }

                        //CAPTURA DE CELULAR
                        System.out.print("Ingrese el número de celular: ");
                        String nuevoCelular = Sc.nextLine();

                        //CAPTURA DE CONTRASEÑA
                        String nuevaContraseña = "";
                        while (nuevaContraseña.isEmpty()) {
                            System.out.print("Ingrese una contraseña para el usuario: ");
                            nuevaContraseña = Sc.nextLine();
                            if (nuevaContraseña.isEmpty()) {
                                System.out.println("❌ ERROR: La contraseña no puede estar vacía.");
                            }
                        }

                        //ASIGNACIÓN DE ROL
                        Rol nuevoRol = null;
                        while (nuevoRol == null) {
                            System.out.println("\nSeleccione el rol del usuario:");
                            System.out.println("1. USUARIO");
                            System.out.println("2. ADMIN");
                            System.out.print("Seleccione una opción: ");
                            
                            try {
                                int opcionRol = Integer.parseInt(Sc.nextLine());
                                switch (opcionRol) {
                                    case 1:
                                        // Ajustalo al nombre exacto de tu constante Enum (ej: Rol.USUARIO o Rol.CLIENTE)
                                        nuevoRol = Rol.USUARIO; 
                                        break;
                                    case 2:
                                        nuevoRol = Rol.ADMIN;
                                        break;
                                    default:
                                        System.out.println("❌ ERROR: Opción inválida. Elija 1 o 2.");
                                        break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("❌ ERROR: Debe ingresar un número entero válido.");
                            }
                        } 

                        //ENVÍO AL SERVICIO Y PERSISTENCIA
                        try {
                            Usuario usuarioCreado = usuarioService.crearUsuario(
                                nuevoNombre, nuevoApellido, nuevoMail, nuevoCelular, nuevaContraseña, nuevoRol
                            );
                            
                            // Criterio de aceptación: Se persiste el usuario y se informa el ID generado
                            System.out.println("\n✅ Usuario registrado con éxito.");
                            System.out.println("🆔 ID Generado: " + usuarioCreado.getId());
                            System.out.println("Datos: " + usuarioCreado.toString());
                            
                        } catch (IllegalArgumentException e) {
                            // Captura el error si el Mail ya existe en el sistema
                            System.out.println("\n❌ No se pudo crear el usuario: " + e.getMessage());
                            System.out.println("⚠️ Intente nuevamente con un correo electrónico diferente.");
                        }
                        System.out.println("---------------------------------------");
                        break;
                        
                    case 3:
                        System.out.println("\n--- EDITAR USUARIO EXISTENTE ---");
                        System.out.print("Ingrese el ID del usuario que desea editar: ");
                        Long idUserIngresado = null;
                        
                        try {
                            idUserIngresado = Long.parseLong(Sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("❌ ERROR: El ID debe ser un número válido.");
                            System.out.println("---------------------------------------");
                            break;
                        }

                        //Selección por id y validación de existencia/baja
                        Usuario userAEditar = usuarioService.buscarUsuarioPorId(idUserIngresado);

                        if (userAEditar == null || userAEditar.isEliminado()) {
                            System.out.println("❌ ERROR: No se encontró ningún usuario activo con el ID " + idUserIngresado);
                            System.out.println("---------------------------------------");
                            break;
                        }

                        //Si el usuario existe, muestro su estado actual:
                        System.out.println("\nUsuario seleccionado: " + userAEditar.getApellido() + ", " + userAEditar.getNombre());
                        System.out.println("(Presione [ENTER] para mantener el valor actual)");

                        //EDITAR NOMBRE
                        System.out.print("Nombre actual [" + userAEditar.getNombre() + "]: ");
                        String modNombre = Sc.nextLine();

                        //EDITAR APELLIDO
                        System.out.print("Apellido actual [" + userAEditar.getApellido() + "]: ");
                        String modApellido = Sc.nextLine();

                        // 3. EDITAR MAIL (Con validación de formato básico si no está vacío)
                        String modMail = "";
                        
                        while (true) {
                            System.out.print("Email actual [" + userAEditar.getMail() + "]: ");
                            modMail = Sc.nextLine().trim();
                            
                            if (modMail.isEmpty()) {
                                break; // Presionó ENTER, mantiene el actual
                            } 
                        }

                        //EDITAR CELULAR
                        System.out.print("Celular actual [" + userAEditar.getCelular() + "]: ");
                        String modCelular = Sc.nextLine();

                        //EDITAR CONTRASEÑA
                        System.out.print("Contraseña actual [********]: ");
                        String modContraseña = Sc.nextLine();

                        //EDITAR ROL
                        Rol modRol = null;
                        System.out.println("Rol actual [" + userAEditar.getRol() + "]");
                        System.out.println("¿Desea cambiar el rol? (1: USUARIO / 2: ADMIN / Enter para mantener): ");
                        String rolInput = Sc.nextLine().trim();
                        
                        if (!rolInput.isEmpty()) {
                            if (rolInput.equals("1")) {
                                modRol = Rol.USUARIO;
                            } else if (rolInput.equals("2")) {
                                modRol = Rol.ADMIN;
                            } else {
                                System.out.println("⚠️ Opción de rol inválida. Se mantendrá el rol actual.");
                            }
                        }

                        //ENVÍO AL SERVICIO Y CAPTURA DE EXCEPCIONES
                        try {
                            usuarioService.editarUsuario(
                                userAEditar, modNombre, modApellido, modMail, modCelular, modContraseña, modRol
                            );
                            
                            // Criterio de aceptación: Se confirma actualización al finalizar
                            System.out.println("\n✅ Usuario actualizado con éxito.");
                            System.out.println("Datos actuales: " + userAEditar.toString());
                            
                        } catch (IllegalArgumentException e) {
                            // Captura el error si el nuevo Mail ya pertenece a otro usuario activo
                            System.out.println("\n❌ Error al guardar los cambios: " + e.getMessage());
                            System.out.println("⚠️ La operación se canceló y el usuario conservó sus valores anteriores.");
                        }
                        System.out.println("---------------------------------------");
                        break;
                        
                    case 4:
                        System.out.println("\n--- ELIMINAR USUARIO EXISTENTE ---");
                        System.out.print("Ingrese el ID del usuario que desea eliminar: ");
                        Long idUserEliminar = null;
                        
                        try {
                            idUserEliminar = Long.parseLong(Sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("❌ ERROR: El ID debe ser un número válido.");
                            System.out.println("---------------------------------------");
                            break;
                        }

                        // Buscamos el usuario en el servicio
                        Usuario userAEliminar = usuarioService.buscarUsuarioPorId(idUserEliminar);

                        // Criterio de aceptación: Validamos que exista y no esté ya eliminado
                        if (userAEliminar == null || userAEliminar.isEliminado()) {
                            System.out.println("❌ ERROR: No se encontró ningún usuario activo con el ID " + idUserEliminar);
                            System.out.println("---------------------------------------");
                            break;
                        }

                        // Mostramos los datos para seguridad del operador
                        System.out.println("\nUsuario encontrado: " + userAEliminar.getApellido() + ", " + userAEliminar.getNombre());
                        System.out.println("⚠️ ATENCIÓN: El usuario ya no podrá usarse para nuevos pedidos, pero se mantendrá su historial.");
                        
                        // Criterio de aceptación: Se pide confirmación
                        System.out.print("¿Está seguro de que desea eliminar este usuario? (S/N): ");
                        String confEliminar = Sc.nextLine().trim().toUpperCase();

                        if (confEliminar.equals("S")) {
                            usuarioService.eliminarUsuario(userAEliminar);
                            System.out.println("\n✅ El usuario '" + userAEliminar.getNombre() + " " + userAEliminar.getApellido() + "' fue dado de baja con éxito.");
                        } else {
                            System.out.println("\n❌ Operación cancelada. El usuario permanece activo.");
                        }
                        
                        System.out.println("---------------------------------------");
                        break;
                        
                    case 0:
                         break;
                         
                    default:
                        System.out.println("❌ Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ ERROR: Ingrese un número.");
            }
        } while (opcionUsuario != 0);
    }
    
    public static void menuPedidos(Scanner Sc){
        int opcionPed = 0;
        
        do {
             System.out.println("1. Listar Pedidos");
             System.out.println("2. Crear Pedido");
             System.out.println("3. Actualizar estado/forma de pago del pedido");
             System.out.println("4. Eliminar Pedido");
             System.out.println("0. Volver al Menú Principal");
             System.out.print("Seleccione una opción: ");

            try {
                opcionPed = Integer.parseInt(Sc.nextLine());
                switch (opcionPed) {
                    case 1:
                        System.out.println("\n--- LISTADO DE PEDIDOS ---");
                        System.out.println("1. Ver todos los pedidos activos");
                        System.out.println("2. Filtrar pedidos por Usuario");
                        System.out.print("Seleccione una opción: ");
                        
                        String opcListado = Sc.nextLine().trim();
                        ArrayList<Pedido> pedidosAMostrar = new ArrayList<>();
                        
                        if (opcListado.equals("1")) {
                            // Listar todos
                            pedidosAMostrar = pedidoService.listarPedido();
                            
                        } else if (opcListado.equals("2")) {
                            // Criterio de Aceptación: Filtrar por usuario
                            System.out.print("Ingrese el ID del usuario para filtrar: ");
                            try {
                                Long idUserFiltro = Long.parseLong(Sc.nextLine());
                                
                                // Opcional: Validamos si el usuario existe para dar mejor UX
                                Usuario userFiltro = usuarioService.buscarUsuarioPorId(idUserFiltro);
                                if (userFiltro == null) {
                                    System.out.println("❌ ERROR: No existe ningún usuario con el ID " + idUserFiltro);
                                    System.out.println("---------------------------------------");
                                    break;
                                }
                                
                                System.out.println("🔍 Filtrando pedidos del cliente: " + userFiltro.getApellido() + ", " + userFiltro.getNombre());
                                pedidosAMostrar = pedidoService.listarPedidosPorUsuario(idUserFiltro);
                                
                            } catch (NumberFormatException e) {
                                System.out.println("❌ ERROR: El ID debe ser un número válido.");
                                System.out.println("---------------------------------------");
                                break;
                            }
                        } else {
                            System.out.println("❌ Opción inválida. Regresando al menú.");
                            System.out.println("---------------------------------------");
                            break;
                        }

                        // Criterio de aceptación: Si no hay registros, se informa adecuadamente
                        if (pedidosAMostrar.isEmpty()) {
                            System.out.println("ℹ️ No se encontraron pedidos registrados que coincidan con la búsqueda.");
                        } else {
                            // Imprimimos el resultado usando el toString de Pedido
                            for (Pedido ped : pedidosAMostrar) {
                                System.out.println(ped.toString());
                            }
                        }
                        
                        System.out.println("-------------------------------------------------------------------------------");
                        break;
                        
                    case 2:
                        System.out.println("\n--- ALTA DE NUEVO PEDIDO ---");
                        //SELECCIÓN DE USUARIO (Debe existir y estar activo)
                        System.out.print("Ingrese el ID del usuario/cliente: ");
                        Long idUserPedido = null;
                        try {
                            idUserPedido = Long.parseLong(Sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("❌ ERROR: El ID debe ser un número válido.");
                            break;
                        }

                        Usuario usuarioPedido = usuarioService.buscarUsuarioPorId(idUserPedido);
                        if (usuarioPedido == null || usuarioPedido.isEliminado()) {
                            System.out.println("❌ ERROR: El usuario no existe o está dado de baja. No se puede crear el pedido.");
                            break;
                        }

                        System.out.println("✅ Cliente seleccionado: " + usuarioPedido.getApellido() + ", " + usuarioPedido.getNombre());
                        
                        //SELECCIÓN DE FORMA DE PAGO 
                        FormaPago pagoSeleccionado = null;
                        while (pagoSeleccionado == null) {
                            System.out.println("\nSeleccione la forma de pago:");
                            System.out.println("1. TARJETA");
                            System.out.println("2. TRANSFERENCIA");
                            System.out.println("3. EFECTIVO");
                            System.out.print("Seleccione una opción: ");
                            String opcPago = Sc.nextLine().trim();
                            switch (opcPago) {
                                case "1":
                                    pagoSeleccionado = FormaPago.TARJETA;
                                    break;
                                case "2":
                                    pagoSeleccionado = FormaPago.TRANSFERENCIA;
                                    break;
                                case "3":
                                    pagoSeleccionado = FormaPago.EFECTIVO;
                                    break;
                                default:
                                    System.out.println("❌ ERROR: Opción inválida. Elija 1 o 2.");
                                    break;
                            }
                        }

                        //CREACIÓN INSTANTÁNEA DEL PEDIDO (Nace como PENDIENTE)
                        Pedido nuevoPedido = null;
                        try {
                            nuevoPedido = new Pedido(Estado.PENDIENTE, pagoSeleccionado, usuarioPedido);
                        } catch (IllegalArgumentException e) {
                            System.out.println("❌ ERROR al inicializar el pedido: " + e.getMessage());
                            break;
                        }
                        
                        //BUCLE PARA CARGAR DETALLES (1..N)
                        boolean cargandoDetalles = true;
                        
                        try {
                            while (cargandoDetalles) {
                                System.out.println("\n--- Agregar Producto al Pedido ---");
                                System.out.print("Ingrese el ID del producto: ");
                                Long idProdDetalle = Long.parseLong(Sc.nextLine());

                                Producto prodDetalle = productoService.buscarProductoPorId(idProdDetalle);
                                if (prodDetalle == null || prodDetalle.isEliminado()) {
                                    throw new IllegalArgumentException("El producto con ID " + idProdDetalle + " no existe o está inactivo.");
                                }

                                System.out.println("-> Producto: " + prodDetalle.getNombre() + " | Precio: $" + prodDetalle.getPrecio() + " | Stock disponible: " + prodDetalle.getStock());
                                System.out.print("Ingrese la cantidad que desea llevar: ");
                                int cantidadDetalle = Integer.parseInt(Sc.nextLine());

                                if (cantidadDetalle <= 0) {
                                    throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
                                }
                                
                                nuevoPedido.addDetallePedido(cantidadDetalle, prodDetalle);
                                System.out.println("🛒 ¡Producto añadido con éxito!");

                                //Pregunto si desea continuar
                                System.out.print("\n¿Desea agregar otro producto? (S/N): ");
                                String continuar = Sc.nextLine().trim().toUpperCase();
                                if (!continuar.equals("S")) {
                                    cargandoDetalles = false;
                                }
                            }

                            //Valido que al menos tenga 1 detalle
                            if (nuevoPedido.getDetalles().isEmpty()) {
                                throw new IllegalArgumentException("No se puede registrar un pedido sin detalles.");
                            }

                            //SI TODO SALIÓ BIEN, SE PERSISTE EN EL SERVICIO GENERAL
                            pedidoService.agregarPedido(nuevoPedido);
                            
                            System.out.println("\n✅ ¡Pedido registrado con éxito en el sistema!");
                            System.out.println(nuevoPedido.toString()); // Tu flamante StringBuilder se luce acá

                        } catch (NumberFormatException e) {
                            System.out.println("\n❌ ERROR: Se introdujo un carácter inválido en un campo numérico.");
                            System.out.println("⚠️ Cancelando operación. Revirtiendo cambios de stock...");
                            // Rollback automático: devolvemos stock usando tu propio método
                            if (nuevoPedido != null) {
                                while (!nuevoPedido.getDetalles().isEmpty()) {
                                    Producto p = nuevoPedido.getDetalles().get(0).getProducto();
                                    nuevoPedido.deleteDetallePedidoByProducto(p);
                                }
                            }
                        } catch (IllegalArgumentException e) {
                            // Captura falta de stock o producto inválido
                            System.out.println("\n❌ ERROR EN OPERACIÓN: " + e.getMessage());
                            System.out.println("⚠️ Cancelando creación del pedido de forma segura por inconsistencia.");
                            // Rollback automático
                            if (nuevoPedido != null) {
                                while (!nuevoPedido.getDetalles().isEmpty()) {
                                    Producto p = nuevoPedido.getDetalles().get(0).getProducto();
                                    nuevoPedido.deleteDetallePedidoByProducto(p);
                                }
                            }
                        }
                        
                        System.out.println("---------------------------------------");
                        break;
                        
                    case 3:
                        System.out.println("\n--- ACTUALIZAR ESTADO/FORMA DE PAGO ---");
                        System.out.print("Ingrese el ID del pedido que desea modificar: ");
                        Long idPedActualizar = null;
                        
                        try {
                            idPedActualizar = Long.parseLong(Sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("❌ ERROR: El ID debe ser un número válido.");
                            System.out.println("---------------------------------------");
                            break;
                        }

                        // Criterio de aceptación: Selección por ID y validación de existencia/baja
                        Pedido pedAModificar = pedidoService.buscarPedidoPorId(idPedActualizar);

                        if (pedAModificar == null || pedAModificar.isEliminado()) {
                            System.out.println("❌ ERROR: No se encontró ningún pedido activo con el ID " + idPedActualizar);
                            System.out.println("---------------------------------------");
                            break;
                        }

                        // Mostramos el estado actual del pedido seleccionado
                        System.out.println("\nPedido seleccionado: ID " + pedAModificar.getId());
                        System.out.println("Estado actual: [" + pedAModificar.getEstado() + "] | Pago actual: [" + pedAModificar.getFormaPago() + "]");
                        System.out.println("(Presione [ENTER] en cualquier opción para mantener el valor actual)");

                        // 1. MODIFICAR ESTADO (Criterio: Opciones numéricas validadas)
                        Estado elNuevoEstado = null;
                        System.out.println("\nSeleccione el NUEVO ESTADO:");
                        System.out.println("1. PENDIENTE");
                        System.out.println("2. CANCELADO");
                        System.out.println("3. CONFIRMADO");
                        System.out.println("4. TERMINADO");
                        System.out.print("Seleccione una opción (o Enter): ");
                        String inputEstado = Sc.nextLine().trim();

                        if (!inputEstado.isEmpty()) {
                            switch (inputEstado) {
                                case "1":
                                    elNuevoEstado = Estado.PENDIENTE;
                                    break;
                                case "2":
                                    elNuevoEstado = Estado.CANCELADO;
                                    break;
                                case "3":
                                    elNuevoEstado = Estado.CONFIRMADO;
                                    break;
                                case "4":
                                    elNuevoEstado = Estado.TERMINADO;
                                    break;
                                default:
                                    System.out.println("⚠️ Opción inválida. Se mantendrá el estado actual.");
                                    break;
                            }
                        }

                        // 2. MODIFICAR FORMA DE PAGO (Criterio: Opciones numéricas validadas)
                        FormaPago laNuevaFormaPago = null;
                        System.out.println("\nSeleccione la NUEVA FORMA DE PAGO:");
                        System.out.println("1. EFECTIVO");
                        System.out.println("2. TARJETA");
                        System.out.print("Seleccione una opción (o Enter): ");
                        String inputPago = Sc.nextLine().trim();

                        if (!inputPago.isEmpty()) {
                            switch (inputPago) {
                                case "1":
                                    laNuevaFormaPago = FormaPago.EFECTIVO;
                                    break;
                                case "2":
                                    laNuevaFormaPago = FormaPago.TARJETA;
                                    break;
                                default:
                                    System.out.println("⚠️ Opción inválida. Se mantendrá la forma de pago actual.");
                                    break;
                            }
                        }

                        //PERSISTENCIA EN MEMORIA E INFORME DE ÉXITO
                        pedidoService.actualizarPedido(pedAModificar, elNuevoEstado, laNuevaFormaPago);
                        
                        System.out.println("\n✅ Datos del pedido actualizados con éxito.");
                        System.out.println("---------------------------------------");
                        // Mostramos el ticket completo actualizado con tu StringBuilder
                        System.out.println(pedAModificar.toString());
                        
                        System.out.println("---------------------------------------");
                        break;
                        
                    case 4:
                        System.out.println("\n--- ELIMINAR PEDIDO EXISTENTE ---");
                        System.out.print("Ingrese el ID del pedido que desea eliminar: ");
                        Long idPedEliminar = null;
                        
                        try {
                            idPedEliminar = Long.parseLong(Sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("❌ ERROR: El ID debe ser un número válido.");
                            System.out.println("---------------------------------------");
                            break;
                        }

                        //Busco el pedido en el servicio
                        Pedido pedAEliminar = pedidoService.buscarPedidoPorId(idPedEliminar);

                        //Valido existencia y que no esté ya borrado
                        if (pedAEliminar == null || pedAEliminar.isEliminado()) {
                            System.out.println("❌ ERROR: No se encontró ningún pedido activo con el ID " + idPedEliminar);
                            System.out.println("---------------------------------------");
                            break;
                        }

                        //Muestro un resumen del pedido para que el operador esté seguro
                        System.out.println("\nPedido encontrado: ID " + pedAEliminar.getId());
                        System.out.println("Cliente: " + pedAEliminar.getUsuario().getApellido() + ", " + pedAEliminar.getUsuario().getNombre());
                        System.out.println("Monto total: $" + pedAEliminar.getTotal());
                        System.out.println("⚠️ ATENCIÓN: Esta acción repondrá el stock de los productos en el inventario.");
                        
                        //Se pide id y confirmación
                        System.out.print("¿Está seguro de que desea eliminar este pedido? (S/N): ");
                        String confEliminarPed = Sc.nextLine().trim().toUpperCase();

                        if (confEliminarPed.equals("S")) {
                            //Ejecuto la baja y el reabastecimiento de stock de forma segura
                            pedidoService.eliminarPedido(pedAEliminar);
                            System.out.println("\n✅ El pedido ID " + pedAEliminar.getId() + " fue eliminado lógicamente con éxito.");
                            System.out.println("📦 El stock de los productos ha sido devuelto al catálogo.");
                        } else {
                            System.out.println("\n❌ Operación cancelada. El pedido permanece intacto.");
                        }
                        
                        System.out.println("---------------------------------------");
                        break;
                        
                    case 0:
                         break;
                         
                    default:
                        System.out.println("❌ Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ ERROR: Ingrese un número.");
            }
        } while (opcionPed != 0);
    }
}
