package com.example.tlenguajes2023.configuracion;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteConnection extends SQLiteOpenHelper
{
    public SQLiteConnection(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        /* Creacion de objectos de base de datos */
        sqLiteDatabase.execSQL(ConfigDB.CreateTBPersonas);  // Creando la tabla de personas en sqlite..
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL(ConfigDB.DropTBPersonas);
        onCreate(sqLiteDatabase);
    }

    public boolean actualizarPersona(personas persona) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ConfigDB.nombres, persona.getNombres());
        values.put(ConfigDB.apellidos, persona.getApellidos());
        values.put(ConfigDB.edad, persona.getEdad());
        values.put(ConfigDB.correo, persona.getCorreo());
        values.put(ConfigDB.direccion, persona.getDireccion());

        // Agrega otros campos para actualizar

        int rowsAffected = db.update(ConfigDB.tblpersonas, values, ConfigDB.id + " = ?",
                new String[]{String.valueOf(persona.getId())});

        db.close();
        return rowsAffected > 0;
    }

    // Método para eliminar una persona de la base de datos
    public boolean eliminarPersona(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(ConfigDB.tblpersonas, ConfigDB.id + " = ?",
                new String[]{String.valueOf(id)});

        db.close();
        return rowsAffected > 0;
    }
}
