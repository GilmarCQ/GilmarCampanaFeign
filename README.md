<!-- ABOUT THE PROJECT -->
## Consumo servicios externos

###  1.  Consulta de RUC 
Método `GET`, EndPoint `http://localhost:8080/v1/empresa/{RUC}`.
```sh
   http://localhost:8080/v1/empresa/20141637941
```

###  2.  Crear empresa con información de Sunat 
Método `POST`, EndPoint `http://localhost:8080/v1/empresa`.
```sh
   http://localhost:8080/v1/empresa
```
Parametros del Body
```sh
{
    "numDocu": "20141637941"
}
```
