# ProcesamientoMasivo
Carga masiva de transacciones


1.- Favor de crear manualmete la base de datos en postgres con el nombre de Transacciones
2.- Ejecutar el script llamado schema para que se pueda genarar las tablas y sus indices
3.- clonar el repositorio https://github.com/riosdominguez47/ProcesamientoMasivo.git 
4.- importar el proyecto en el ID spring tolls
5.- correr el proyecto 
6.- Consumir el servicio del postman coleccion_api.postman_collection   


Explicación breve de la estrategia usada para evitar que el proceso falle ante errores de datos:


		Se realizo manejo de exepciones para poder para obtener los errores en tiempo de ejecucion y con  esto evitar tronar el API
		