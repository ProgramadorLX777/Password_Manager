package com.example.securepasswords.OpcionesPassword;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.securepasswords.DataPassword;
import com.example.securepasswords.Encriptacion;
import com.example.securepasswords.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AgregarPassword extends AppCompatActivity {

    EditText txtTitulo, txtNombreSitio, txtNombreUsuario, txtPass, txtNotasAdicionales;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_password);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("");

        InicializarVariables();

        // Inicializa Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("passwords");
    }

    private void InicializarVariables() {
        txtTitulo = findViewById(R.id.txtTitulo);
        txtNombreSitio = findViewById(R.id.txtNombreSitio);
        txtNombreUsuario = findViewById(R.id.txtNombreUsuario);
        txtPass = findViewById(R.id.txtPass);
        txtNotasAdicionales = findViewById(R.id.txtNotasAdicionales);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_guardar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.guardarPassword) {
            guardarContraseña();
        }
        return super.onOptionsItemSelected(item);
    }

    private void guardarContraseña() {
        try {
            // Obtén los datos
            String titulo = txtTitulo.getText().toString();
            String nombreSitio = txtNombreSitio.getText().toString();
            String nombreUsuario = txtNombreUsuario.getText().toString();
            String pass = txtPass.getText().toString();
            String notasAdicionales = txtNotasAdicionales.getText().toString();

            // Encripta los datos
            String key = "ThisIsASecretKey";  // Usa una clave segura y almacénala de forma segura
            String encryptedTitulo = Encriptacion.encrypt(titulo, key);
            String encryptedNombreSitio = Encriptacion.encrypt(nombreSitio, key);
            String encryptedNombreUsuario = Encriptacion.encrypt(nombreUsuario, key);
            String encryptedPass = Encriptacion.encrypt(pass, key);
            String encryptedNotasAdicionales = Encriptacion.encrypt(notasAdicionales, key);

            // Guarda los datos en Firebase
            DataPassword passwordData = new DataPassword(encryptedTitulo, encryptedNombreSitio, encryptedNombreUsuario, encryptedPass, encryptedNotasAdicionales);
            databaseReference.push().setValue(passwordData);

            Toast.makeText(this, "Contraseña guardada y encriptada", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar la contraseña", Toast.LENGTH_SHORT).show();
        }
    }
}
