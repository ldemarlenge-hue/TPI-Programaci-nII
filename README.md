# TPI Programación 2 - Sistema de Gestión de Pedidos y Facturación

Este proyecto consiste en un sistema de gestión comercial desarrollado íntegramente en **Java SE** ejecutado por consola. 
Permite la administración integral de Categorías, Productos, Usuarios y la confección de Pedidos con lógica de persistencia simulada 
en memoria y control estricto de reglas de negocio.

---

## Video Demostrativo y Defensa Oral
De acuerdo con las pautas requeridas para la evaluación, se puede acceder a la defensa técnica y demostración en vivo del sistema 
haciendo clic en el siguiente enlace:

👉 **[ENLACE AL VIDEO DEMOSTRATIVO Y DEFENSA](https://youtu.be/JFAcyu7P3Ag)**

---

## Requisitos Previos

Para clonar, compilar y ejecutar este sistema de forma local, se necesita contar con el siguiente entorno:
* **Java Development Kit (JDK):** Versión 17 o superior instalada.
* **Entorno de Desarrollo (IDE):** Visual Studio Code (con la extensión *Extension Pack for Java*) o Eclipse / IntelliJ.
* **Git:** Instalado en el sistema operativo para la clonación del repositorio.

---

## Instrucciones de Ejecución

### Opción 1: Ejecución rápida desde Visual Studio Code (Recomendado)
1. Abrir la carpeta raíz del proyecto en Visual Studio Code.
2. Asegurarse de que el entorno detecte el proyecto Java (aparecerá la estructura de paquetes en la barra lateral).
3. Navegar hasta la carpeta `src/` y abrir el archivo `Main.java`.
4. Hacer clic en el botón **Run** (Ejecutar) que aparece sobre el método `public static void main` o presionar `F5`.

### Opción 2: Compilación y Ejecución desde la Terminal de Comandos (Consola)
Si se prefiere ejecutar de manera nativa mediante comandos del sistema:

1. Abrir la terminal o el símbolo del sistema (CMD) en la ruta raíz del proyecto.
2. Compilar los archivos fuente `.java` redirigiendo la salida a una carpeta temporal:
   ```bash
   javac -d bin src/**/*.java
