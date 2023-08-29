# Read_Return_Browser_T2
Por medio de este repositorio, podemos visualizar desde nuestro archivo local, diferentes archivos ya sean, GIF, JPG, HTML, entre otros.

## Elaborado por:
Jose Ricardo Olarte Pardo

### Directorios de la estructuración
![directorios](https://github.com/Ricardo-Olarte/Read_Return_Browser_T2/blob/main/images/directorios.png)

Se distribuye la estructuración de los directorios, por medio de la imagen visualizada.

### Requisitos previos
* Navegador (Chrome, Firefox, Safari)
* IDE de Java (Netbeans, Intellij, entre otros)

### Ejecución
Para consultar desde local

> localhost:37600

Abriendo desde el browser consultamos localhost:37600
1. Clonamos el repositorio indicado de la siguiente URL https://github.com/Ricardo-Olarte/AREP/tree/main/Read_Return_Browser_T2
2. Abrimos intellij y corremos la clase HttpServer
3. Ejecutamos HTTP server y abrimos nuestro browser

![http_server](https://github.com/Ricardo-Olarte/Read_Return_Browser_T2/blob/main/images/HttpServer.png)
![web](https://github.com/Ricardo-Olarte/Read_Return_Browser_T2/blob/main/images/index.png)


### Clases Utilizadas
#### HttpServer
Esta clase es la encarga del web server, a partir de un socket "37600", podemos hacer la consulta de archivos locales, desde el browser
![http_server](https://github.com/Ricardo-Olarte/Read_Return_Browser_T2/blob/main/images/HttpServer.png)
#### LocalFileReader
Clase encargada de generar un index.html, para la lectura de los distintos archivos, de los directorios
![Local_files](https://github.com/Ricardo-Olarte/Read_Return_Browser_T2/blob/main/images/LocalFileReader.png)

### Funcionalidad
* El web server puede leer distintos archivos locales, de diferentes tipos, JPG, HTML, GIF, entre otros.
* Todo por medio de un archivo  simple, generado por la clase Local File Reader.
* Este archivo contiene cada archivo con su lectura respectiva, guardado en un html.
* Se tiene en cuenta que se realizó un cambio en las imagénes a base64 para su lectura.
* Cada una de las clases de manera independiente, realiza tanto el index y su lectura para mostrarlo en el server
* El index.html que viene por default, está modificado, que sea de manera agradable para el usuario, en cambio, al ejecutar LocalFileReader y luego corremos el server, se visualizará la lectura de otro tipos de archivos, pero no mostrará la misma interfaz.

  ## CircleCI
  [![CircleCI](https://dl.circleci.com/status-badge/img/gh/Ricardo-Olarte/Read_Return_Browser_T2/tree/main.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/Ricardo-Olarte/Read_Return_Browser_T2/tree/main)
