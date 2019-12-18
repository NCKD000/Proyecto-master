package cl.inacap.rca_apr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void menujuegos(View view){
        Intent intent = new Intent(this, MenuJuegosActivity.class);
        startActivity(intent);
    }

    public void menupacientes(View view){
        Intent intent = new Intent(this, MenuPacientesActivity.class);
        startActivity(intent);
    }
}
