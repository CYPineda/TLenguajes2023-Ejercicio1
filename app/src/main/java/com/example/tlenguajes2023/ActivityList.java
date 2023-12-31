package com.example.tlenguajes2023;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tlenguajes2023.configuracion.ConfigDB;
import com.example.tlenguajes2023.configuracion.SQLiteConnection;
import com.example.tlenguajes2023.configuracion.personas;

import java.util.ArrayList;

public class ActivityList extends AppCompatActivity
{
    private static final int REQUEST_EDIT_PERSON = 1;
    SQLiteConnection conexion;
    ListView list;
    ArrayList<personas> listpersonas;
    ArrayList<String> arreglopersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        conexion = new SQLiteConnection(this, ConfigDB.namebd, null, 1);
        list = (ListView) findViewById(R.id.Lista);

        ObtenerTabla();

        ArrayAdapter<String> apd = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arreglopersonas);
        list.setAdapter(apd);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personas selectedPerson = listpersonas.get(position);
                openDetalleActivity(selectedPerson);
            }
        });

    }

    private void ObtenerTabla()
    {
        SQLiteDatabase db = conexion.getReadableDatabase();
        personas person = null;
        listpersonas = new ArrayList<personas>();

        // Cursor de Base de Datos
        Cursor cursor = db.rawQuery(ConfigDB.SelectTBPersonas,null);

        // recorremos el cursor
        while(cursor.moveToNext())
        {
            person = new personas();
            person.setId(cursor.getInt(0));
            person.setNombres(cursor.getString(1));
            person.setApellidos(cursor.getString(2));
            person.setEdad(cursor.getString(3));
            person.setCorreo(cursor.getString(4));
            person.setDireccion(cursor.getString(5));
            listpersonas.add(person);
        }

        cursor.close();

        fillData();
    }

    private void fillData()
    {
        arreglopersonas = new ArrayList<String>();

        for(int i=0; i < listpersonas.size(); i++)
        {
            arreglopersonas.add(listpersonas.get(i).getId() + " - "
                    +listpersonas.get(i).getNombres() + " - "
                    +listpersonas.get(i).getApellidos());
        }
    }

    private void openDetalleActivity(personas selectedPerson) {
        Intent intent = new Intent(this, ActivityDetalle.class);
        intent.putExtra("selected_person", selectedPerson);
        startActivityForResult(intent, REQUEST_EDIT_PERSON);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EDIT_PERSON && resultCode == RESULT_OK) {
            // Actualiza la lista aquí
            ObtenerTabla();
            ArrayAdapter<String> apd = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arreglopersonas);
            list.setAdapter(apd);
        }
    }

    public void ActivityIngresarNuevaPersona(View view){
        Intent nuevo = new Intent(this, ActivityIngresar.class);
        startActivityForResult(nuevo, REQUEST_EDIT_PERSON);
    }
}