# **README - Proyecto Hospital**

## **Descripción**
Este proyecto es un sistema de gestión hospitalaria desarrollado con **Spring Boot, Oracle y Angular**. Permite realizar operaciones CRUD sobre hospitales, provincias, distritos y más mediante procedimientos almacenados en Oracle.

---

## **Requisitos Previos**
Antes de iniciar, asegúrate de cumplir con los siguientes requisitos:
- JDK 17 instalado y configurado
- Maven instalado
- Oracle Database 21c o superior
- Node.js y Angular CLI versión 19
- Angular Material instalado
- Git instalado
- Acceso a GitHub

---

## **1. Clonar el Proyecto desde GitHub**
1. Abre una terminal o consola y navega a la carpeta donde deseas clonar el proyecto.
2. Ejecuta el siguiente comando:
```bash
 git clone https://github.com/alarconerus/proyecto-hospital.git
```

---

## **2. Configuración de la Base de Datos (Oracle)**
1. Inicia sesión en Oracle Database usando SQL Developer o una herramienta similar.
2. Crea un nuevo esquema o base de datos.
3. Ejecuta los scripts de la carpeta "BD-Procedimientos-Almacenados":
   - Ejecuta el script de creación de tablas desde **Create and Insert of Data**.
   - Ejecuta el script de inserción de datos.
   - Ejecuta cada uno de los procedimientos almacenados en el orden indicado.

---

## **3. Configuración del Backend (Spring Boot)**
1. Abre el proyecto en tu IDE favorito (IntelliJ, Eclipse o Spring Tool Suite).
2. Ejecuta el comando Maven para descargar dependencias:
```bash
 mvn clean install
```
3. Configura la conexión en el archivo `application.properties`:
```properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XEPDB1
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.hibernate.ddl-auto=none
```
4. Ejecuta el backend desde tu IDE o con:
```bash
mvn spring-boot:run
```

---

## **4. Configuración del Frontend (Angular)**
1. Extrae el archivo **hospital-frontend.zip** en tu máquina.
2. Abre una terminal dentro de la carpeta del proyecto Angular.
3. Instala las dependencias:
```bash
 npm install
```
4. Configura el archivo `src/environments/environment.ts`:
```javascript
export const environment = {
 production: false,
 apiUrl: 'http://localhost:8080'
};
```
5. Instala Angular Material si no está instalado:
```bash
 ng add @angular/material
```
6. Ejecuta el frontend:
```bash
ng serve --open
```
7. Accede a la aplicación en: `http://localhost:4200`

---

## **5. Ejecución Completa del Proyecto**
1. Asegúrate de que el backend esté ejecutándose en el puerto 8080 y el frontend en el puerto 4200.
2. Ingresa a la aplicación desde el navegador web y realiza operaciones CRUD en la interfaz de usuario.

¡Listo! El sistema debe estar funcionando correctamente.

