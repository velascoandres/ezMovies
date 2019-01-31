package com.example.andres.ezmoviesadmin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.andres.ezmoviesadmin.dummy.PeliculaContent
import com.example.andres.ezmoviesadmin.dummy.PeliculaContent.Pelicula


class SqliteHelper(context: Context?) :
        SQLiteOpenHelper(context,
                "moviles5", // Nombre de la base de datos
                null,
                1) {

    override fun onCreate(baseDeDatos: SQLiteDatabase?) {

        val crearTablaPelicula = "CREATE TABLE " +
                "pelicula " +
                "(" +
                "id_pelicula INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre VARCHAR(50)," +
                "id_categoria INTEGER,"+
                "FOREIGN KEY(id_categoria) REFERENCES categoria_id(id_categoria)" +
                ")"

        val crearTablaCategoria = "CREATE TABLE " +
                "categoria" +
                "(" +
                "id_categoria INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre VARCHAR(50)" +
                ")"
        val crearTablaActor = "CREATE TABLE " +
                "Actor " +
                "(" +
                "id_actor INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre VARCHAR(50)" +
                ")"
        val insertarCategoria = "Insert into categoria (nombre) values ('accion');"
        val insertarCategoria2 = "Insert into categoria (nombre) values ('fantasia');"
        val insertarPelicula = "Insert into pelicula (nombre,id_categoria) values ('venom',1);"
        val insertarPelicula2 = "Insert into pelicula (nombre,id_categoria) values ('spiderman',1);"
        val insertarActor = "Insert into actor (nombre) values ('Harrison Ford');"
        val insertarActor2 = "Insert into actor (nombre) values ('Mia Khalifa');"

        baseDeDatos?.execSQL(crearTablaCategoria)
        baseDeDatos?.execSQL(crearTablaPelicula)
        baseDeDatos?.execSQL(crearTablaActor)
        baseDeDatos?.execSQL(insertarCategoria)
        baseDeDatos?.execSQL(insertarCategoria2)
        baseDeDatos?.execSQL(insertarPelicula)
        baseDeDatos?.execSQL(insertarPelicula2)
        baseDeDatos?.execSQL(insertarActor)
        baseDeDatos?.execSQL(insertarActor2)



    }

    override fun onUpgrade(baseDeDatos: SQLiteDatabase?,
                           antiguaVersion: Int,
                           nuevaVersion: Int) {

    }

    fun existePelicula(): RespuestaGuardadoPelicula {

        val statement = "select * from pelicula where id_pelicula=1;"
        val dbReadable = readableDatabase
        val resultado = dbReadable.rawQuery(statement, null)

        val respuestaUsuario = RespuestaGuardadoPelicula(null, null,null)

        if (resultado.moveToFirst()) {
            do {
                Log.i("BDD","${respuestaUsuario.nombre}")
                respuestaUsuario.id = resultado.getString(0)
                respuestaUsuario.nombre = resultado.getString(1)
                respuestaUsuario.id_categoria = resultado.getString(2)
            } while (resultado.moveToNext())
        }

        resultado.close()

        dbReadable.close()

        return respuestaUsuario
    }

    fun getCategorias(categorias: ArrayList<Categoria>){
        val statement = "select id_categoria, nombre from categoria;"
        val dbReadable = readableDatabase
        val resultado = dbReadable.rawQuery(statement, null)
        categorias.clear()

        if (resultado.moveToFirst()) {
            do {
                categorias.add(Categoria(resultado.getString(0),resultado.getString(1)))
            } while (resultado.moveToNext())
        }

        resultado.close()

        dbReadable.close()

    }

    fun getActores(actores:ArrayList<Actor>){
        val statement = "select id_actor, nombre from actor;"
        val dbReadable = readableDatabase
        val resultado = dbReadable.rawQuery(statement, null)
        actores.clear()

        if (resultado.moveToFirst()) {
            do {
                actores.add(Actor(resultado.getString(0),resultado.getString(1)))
            } while (resultado.moveToNext())
        }

        resultado.close()

        dbReadable.close()
    }

    fun getPeliculas(peliculas: ArrayList<PeliculaContent.Pelicula>){
        val statement = "select id_pelicula, pelicula.nombre, pelicula.id_categoria, categoria.nombre from pelicula,categoria where pelicula.id_categoria=categoria.id_categoria;"

        val dbReadable = readableDatabase

        val resultado = dbReadable.rawQuery(statement, null)
        Log.i("BDD", resultado.getColumnName(0))

        peliculas.clear()

        if (resultado.moveToFirst()) {
            do {
                peliculas.add(Pelicula(resultado.getString(0),resultado.getString(1),resultado.getString(2),resultado.getString(3)))
            } while (resultado.moveToNext())
        }

        resultado.close()

        dbReadable.close()


    }

    fun crearPeliculaFormulario(nombre: String,
                                id_categoria: String): Boolean {
        // Base de datos de escritura
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        // Valores de los campos
        cv.put("nombre", nombre)
        cv.put("id_categoria", id_categoria)

        val resultado: Long = dbWriteable
                .insert(
                        "pelicula", // Nombre de la tabla
                        null,
                        cv)

        dbWriteable.close()

        return if (resultado.toInt() == -1) false else true

    }
    fun crearCategoriaForumlario(nombre: String): Boolean {
        // Base de datos de escritura
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        // Valores de los campos
        cv.put("nombre", nombre)

        val resultado: Long = dbWriteable
                .insert(
                        "categoria", // Nombre de la tabla
                        null,
                        cv)

        dbWriteable.close()

        return if (resultado.toInt() == -1) false else true

    }
    fun crearActorForumlario(nombre: String): Boolean {
        // Base de datos de escritura
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        // Valores de los campos
        cv.put("nombre", nombre)

        val resultado: Long = dbWriteable
                .insert(
                        "actor", // Nombre de la tabla
                        null,
                        cv)

        dbWriteable.close()

        return if (resultado.toInt() == -1) false else true

    }

    fun actualizarPelicula(id:String,nombre: String,id_categoria: String):Boolean{
        // Base de datos de escritura
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        // Valores de los campos

        cv.put("nombre", nombre)
        cv.put("id_categoria", id_categoria)

        val resultado = dbWriteable
                .update(
                        "pelicula", // Nombre de la tabla
                        cv, // Valores a actualizarse
                        "id_pelicula=?", // Where
                        arrayOf(id) // Parametros
                )

        dbWriteable.close()

        return if (resultado.toInt() == -1) false else true
    }

    fun actualizarCategoria(id:String,nombre: String):Boolean{
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        cv.put("nombre", nombre)

        val resultado = dbWriteable
                .update(
                        "categoria", // Nombre de la tabla
                        cv, // Valores a actualizarse
                        "id_categoria=?", // Where
                        arrayOf(id) // Parametros
                )

        dbWriteable.close()

        return if (resultado.toInt() == -1) false else true
    }

    fun actualizarActor(id:String,nombre: String):Boolean{
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        cv.put("nombre", nombre)

        val resultado = dbWriteable
                .update(
                        "actor", // Nombre de la tabla
                        cv, // Valores a actualizarse
                        "id_actor=?", // Where
                        arrayOf(id) // Parametros
                )

        dbWriteable.close()

        return if (resultado.toInt() == -1) false else true
    }

    fun eliminarPelicula(id:String):Boolean{
        val dbWriteable = this.writableDatabase
        val parametros = arrayOf(id)
        val nombreTabla = "pelicula"
        val clausulaWhere = "id_pelicula = ?"
        val respuesta = dbWriteable.delete(
                nombreTabla,
                clausulaWhere,
                parametros
        )
        return  if(respuesta == -1 )false else true
    }

    fun eliminarCategoria(id:String):Boolean{
        val dbWriteable = this.writableDatabase
        val parametros = arrayOf(id)
        val nombreTabla = "categoria"
        val clausulaWhere = "id_categoria = ?"
        val respuesta = dbWriteable.delete(
                nombreTabla,
                clausulaWhere,
                parametros
        )
        return  if(respuesta == -1 )false else true
    }

    fun eliminarActor(id:String):Boolean{
        val dbWriteable = this.writableDatabase
        val parametros = arrayOf(id)
        val nombreTabla = "actor"
        val clausulaWhere = "id_actor = ?"
        val respuesta = dbWriteable.delete(
                nombreTabla,
                clausulaWhere,
                parametros
        )
        return  if(respuesta == -1 )false else true
    }


}






