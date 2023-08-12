package com.example.tlenguajes2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tlenguajes2023.configuracion.ConfigDB;
import com.example.tlenguajes2023.configuracion.SQLiteConnection;
import com.example.tlenguajes2023.configuracion.personas;

public class ActivityDetalle extends AppCompatActivity {
    personas selectedPerson;
    EditText edtNombre, edtApellidos, edtedad,edtcorreo, edtdireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        selectedPerson = (personas) getIntent().getSerializableExtra("selected_person");

        /*
        EditText txtNombre = findViewById(R.id.edtNombre);
        EditText txtApellidos = findViewById(R.id.edtApellidos);
        // Obtén referencias a los demás TextViews y elementos necesarios

        txtNombre.setText( selectedPerson.getNombres());
        txtApellidos.setText(selectedPerson.getApellidos());
        // Configura los demás TextViews con los datos de selectedPerson
*/
        edtNombre = findViewById(R.id.edtNombre);
        edtApellidos = findViewById(R.id.edtApellidos);
        edtedad =findViewById(R.id.edtEdad);
        edtcorreo = findViewById(R.id.edtCorreo);
        edtdireccion=findViewById(R.id.edtDireccion);

        edtNombre.setText(selectedPerson.getNombres());
        edtApellidos.setText(selectedPerson.getApellidos());
        edtedad.setText(selectedPerson.getEdad());
        edtcorreo.setText(selectedPerson.getCorreo());
        edtdireccion.setText(selectedPerson.getDireccion());
    }

    public void guardarCambios(View view) {
        SQLiteConnection conexion = new SQLiteConnection(getApplicationContext(), ConfigDB.namebd, null, 1);

        selectedPerson.setNombres(edtNombre.getText().toString());
        selectedPerson.setApellidos(edtApellidos.getText().toString());
        selectedPerson.setEdad(edtedad.getText().toString());
        selectedPerson.setCorreo(edtcorreo.getText().toString());
        selectedPerson.setDireccion(edtdireccion.getText().toString());

        if (conexion.actualizarPersona(selectedPerson)) {
            Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK); // Envía un resultado OK a ActivityList
            finish();
        } else {
            Toast.makeText(this, "Error al guardar los cambios", Toast.LENGTH_SHORT).show();
        }
    }
    public void eliminarRegistro(View view) {
        // Implementa la lógica para eliminar el registro aquí
        SQLiteConnection conexion = new SQLiteConnection(getApplicationContext(), ConfigDB.namebd, null, 1);

        if (conexion.eliminarPersona(selectedPerson.getId())) {
            Toast.makeText(this, "Registro eliminado", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish(); // Finaliza esta actividad después de eliminar el registro
        } else {
            Toast.makeText(this, "Error al eliminar el registro", Toast.LENGTH_SHORT).show();
        }
    }
}
