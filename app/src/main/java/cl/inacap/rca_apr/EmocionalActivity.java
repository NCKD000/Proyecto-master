package cl.inacap.rca_apr;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class EmocionalActivity extends AppCompatActivity {

    Button btnCambiar;
    ImageView emocional;

    private Button feliz;
    private Button triste;
    private Button enojado;
    private Button serio;
    private Button asustado;
    private Button llorar;



    Random r;
    Integer[] images ={
            R.drawable.r1,
            R.drawable.r2,
            R.drawable.r3,
            R.drawable.r4,
            R.drawable.r5,
            R.drawable.r6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emocional);
       // btnCambiar = findViewById(R.id.btnCambiar);
       // emocional = findViewById(R.id.emocional);
        //btnCambiar.setOnClickListener(this);

        emocional = (ImageView) findViewById(R.id.emocional);
        btnCambiar = (Button) findViewById(R.id.btnCambiar);
         r = new Random();

        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //display random imagenes
                Toast.makeText(getApplicationContext(),"siguiente", Toast.LENGTH_SHORT).show();
                emocional.setImageResource(images[r.nextInt(images.length)]);
            }
        });


        feliz = (Button)findViewById(R.id.feliz);
        feliz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Avance guardado", Toast.LENGTH_SHORT).show();
            }
        });

        enojado = (Button)findViewById(R.id.enojado);
        enojado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Avance guardado", Toast.LENGTH_SHORT).show();
            }
        });


        triste = (Button)findViewById(R.id.triste);
        triste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Avance guardado", Toast.LENGTH_SHORT).show();
            }
        });

        serio = (Button)findViewById(R.id.serio);
        serio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Avance guardado", Toast.LENGTH_SHORT).show();
            }
        });

        asustado = (Button)findViewById(R.id.asustado);
        asustado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Avance guardado", Toast.LENGTH_SHORT).show();
            }
        });

        llorar = (Button)findViewById(R.id.llorar);
        llorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Avance guardado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //@Override
    //public void onClick(View v) {
    //emocional.setImageResource(R.drawable.r2);
    //}

}

