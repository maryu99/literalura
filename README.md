## Literalura
Es una aplicación que permite registrar tanto los libros como los autores de los mismos en una base de datos, además de buscar también se pueden realizar las siguientes consultas:
<br> 1.- Hacer una busqueda de los libros registrados
<br> 2.- Hacer una busqueda de los autores registrados
<br> 3.- Consultar autores vivos en un determinado año 
<br> 4.- Consultar libros por idioma.

Para la realiazción de esta apliación, se hizo uso de las siguientes herramientas:
- API: Se uso la API Gutendex, la cual es un catálogo de información de más de 70.000 libros presentes en Project Gutenberg (biblioteca en línea y gratuita).
- HttpClient: se emplea la clase HttpClient para realizar solicitudes a la API de libros y obtener datos esenciales. El uso de HttpClient en Java facilita la conexión y la obtención de respuestas de manera eficiente. Además, proporciona una base sólida para realizar operaciones HTTP de manera más estructurada y versátil. 
- HttpRequest: se emplea la clase HttpRequest para configurar y personalizar nuestras solicitudes a la API de libros. La clase HttpRequest en Java nos brinda un control detallado sobre los parámetros de nuestras solicitudes, lo que resulta esencial para adaptar la consulta a nuestras necesidades específicas. 
- HttpResponse: l uso de la interfaz HttpResponse para gestionar las respuestas recibidas de la API. La interfaz HttpResponse en Java ofrece una estructura que permite analizar y acceder a los diferentes elementos de una respuesta HTTP.

<h3>Desarrollo de la aplicación</h3>
- Cración de paquetes:
  * modelo: es este paquete se crearon las clases Autor y Libro y a su vez los Records DatosAutor, DatosGutendex para interpretar las respuestas Json
  * principal: en este paquete se creo la clase principal en donde se realizan los metodos y menú para permitir la ejecucución de los datos solicitados
  * repository: en este se crearon las interfaces IAutorRepository y ILibroRepository, estos se encargan de generar los datos de respuesta para cada petición de información por parte del usuario.
  * service: se crearon las clases CosumoApi, ConvierteDatos y la interfaz IConvierteDatos e encargan de realizar las peticiones http para obtener los datos desde la API Gutendex y posterior transformar esra respuesta a datos del tipo objetos java, con el fin de que se pueda manipular esa información.

![literalura](https://github.com/user-attachments/assets/bc9a4d1b-602f-4214-ae46-5f7b6cacd7f8)




  
