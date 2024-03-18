# Customer_Relationship_ManagerCRM

# INSTALAR MAVEN

1. Descargar Apache Maven https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz
2. Descomprimir con CMD desde el directorio del archivo de descarga: tar xzvf apache-maven-3.9.6-bin.tar.gz
3. Configurar PATH con CMD: setx PATH "%PATH%;ruta_al_directorio_bin_de_Maven"
4. Confirmar la configuración de Maven:
	En el nuevo símbolo del sistema, escribe mvn -v y presiona Enter.
	Deberías ver la versión de Maven y otra información relacionada, lo que confirmará que Maven se ha configurado correctamente y está en tu PATH.

# EJECUTAR EL PROGRAMA

1. Clonar el repositorio en tu pc
2. Asegurarte de que tienes Maven instalado, si no, mira el siguiente apartado
3. Abre la terminal y dirígite a la ubicación donde tengas el proyecto, por ejemplo:

	```C:\Users\admin\Desktop\Customer_Relationship_ManagerCRM\CRM```

4. Accede a la carpeta "target"
5. Pon el siguiente comando en la terminal del pc:

	```java -jar CRM-1.0-SNAPSHOT.jar <opcion> <args1> <args2> ... <argsN>```


# COMPILAR UN PROYECTO MAVEN

1. En la terminal del PC dirígete a la carpeta del proyecto donde se encuentre el archivo pom.xml
2. Comando: mvn compile
3. Comando: mvn package

# OPCIONES DEL PROYECTO

- altacliente &lt;phone&gt; nombre apellido email<br>
- altaEmpleado dni nombre apellido<br>
*bajaEmpleado dni<br>
*cliente phone<br>
*empleado dni<br>
*listClientes<br>
*listEmpleados<br>




