# Tareas API
Es una API para llevar registro de tareas, y agruparlas por categorias personalizables.


# Ejecutalo Localmente
Clona el proyecto
```bash
$ git clone https://github.com/Frankester/todo-app.git
```
Accede al directorio del proyecto
```bash
$ cd todo-app
```
Ve al archivo de configuracion en src/main/resources/application.properties
Agrega el client-secret y el client-id dadas por google (ver "Credenciales de google" para obtenerlas)
Inicializalo con Docker Compose
```bash
$ docker-compose up
```

__*Para ver la documentacion de la API ve a:*__
``http://localhost:8080/swagger-ui.html``

## Credenciales de google
Para obtener el client-secret y el client-id debes ir a [Google Console](https://console.developers.google.com), despues vas a la seccion "Credenciales" y ahi creas las credenciales.
Te va a pedir la URI de redireccionamiento autorizados, y ahí tenes que ingresar: "http://localhost:8080/login/oauth2/code/google".


# Tech Stack

**Database:** MongoDB

**Spring Boot Starters:**  Web,  Data REST, Security, Data JPA

**Documentation**: [Springdoc openapi](https://springdoc.org/v2/)

**Authentication**: Spring Oauth2 Client
