package cl.inacap.rca_apr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MenuJuegosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_juegos);
    }

    public void emocional(View view) {
        Intent intent = new Intent(this, EmocionalActivity.class);
        startActivity(intent);
    }
}
