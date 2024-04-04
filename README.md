
# AREP_Parcial2
## SERGIO DANIEL LOPEZ VARGAS
### Instrucciones de Ejecución
* Clone el repositorio desde GitHub:

```
git clone https://github.com/sergiolopezzl/AREP_Parcial2.git
```

* Navegue al directorio del proyecto: 

```
cd AREP_Parcial2
```

* Compile el proyecto y descargue las dependencias con Maven: 

```
mvn clean package
```

* Ejecute el `MathService` utilizando el siguiente comando: 

```
mvn exec:java '-Dexec.mainClass=org.example.MathService'
```
* Ejecute el `ServiceProxy` utilizando el siguiente comando:
```
mvn exec:java '-Dexec.mainClass=org.example.ServiceProxy'
```

* Para su funcionamiento `round-robin`, `MathService` tiene el puerto *4500* y el `ServiceProxy` tiene 
*4567*, este necesita el `MathService` para poder calcular lo solicitado. Debe tener los dos ejecutandose al tiempo

* Acceda a Local: http://localhost:4567/
  * Local: http://localhost:4567/
  * AWS: http://ec2-18-234-70-136.compute-1.amazonaws.com:4567/
    
### Pruebas
* Prueba Local

![localP.png](src/main/resources/img/localP.PNG)

* Prueba local con maquinas virtuales Docker 2 servicios 1 web

![dockerlocal.png](src/main/resources/img/dockerlocal.PNG)

![dockerps.png](src/main/resources/img/dockerps.PNG)

![docker.png](src/main/resources/img/docker.PNG)

![docker2.png](src/main/resources/img/docker2.PNG)

* Prueba AWS EC2
* 
![awsP.png](src/main/resources/img/awsP.PNG)

* Prueba AWS EC2 con maquinas virtuales Docker 2 servicios 1 web
* 
![dockeraws.png](src/main/resources/img/dockeraws.PNG)

![aws1.png](src/main/resources/img/aws1.PNG)

![awsdocker.png](src/main/resources/img/awsdocker.PNG)

