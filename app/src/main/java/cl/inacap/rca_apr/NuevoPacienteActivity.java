package cl.inacap.rca_apr;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import cl.inacap.rca_apr.modelo.NuevoDatabaseHelper;
import cl.inacap.rca_apr.modelo.Paciente;


public class NuevoPacienteActivity extends AppCompatActivity implements View.OnClickListener{

    private NuevoDatabaseHelper helper=new NuevoDatabaseHelper(this);
    Button IngresarFecha;
    EditText ingresarfecha;
    private int año,mes,dia;

    // Configuracion para los botones de la fecha.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_paciente);

        IngresarFecha=(Button)findViewById(R.id.IngresarFecha);
        ingresarfecha=(EditText)findViewById(R.id.ingresarfecha);
        IngresarFecha.setOnClickListener(this);
    }

    // Configuracion del calendario, para mostrar la fecha en dia,mes,año.
    @Override
    public void onClick(View vi) {
        if(vi==IngresarFecha){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            año=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthYear, int dayOfMonth){
                    ingresarfecha.setText(dayOfMonth+"/"+monthYear+"/"+year);
                }
            }
                    ,año,mes,dia);
            datePickerDialog.show();
        }
    }

    // Se obtienes los valores del paciente y con el helper se guardan en la BDD.
    public void ingresarPaciente(View view){
        String nombre=((TextView)findViewById(R.id.ingresarNombre)).getText().toString();
        String apellido=((TextView)findViewById(R.id.ingresarApellido)).getText().toString();
        String edadStr=((TextView)findViewById(R.id.ingresarEdad)).getText().toString();
        String diagnostico=((TextView)findViewById(R.id.ingresardiag)).getText().toString();
        String observaciones=((TextView)findViewById(R.id.ingresarosb)).getText().toString();
        String fechaIngreso=((TextView)findViewById(R.id.ingresarfecha)).getText().toString();
        int edad=0;
        try{
            edad=Integer.parseInt(edadStr);
        }catch (NumberFormatException ex){
            Toast.makeText(this,"Debe ingresar un numero en la edad",Toast.LENGTH_SHORT).show();
        }

        Paciente paciente=new Paciente(nombre,apellido,edad,diagnostico,observaciones,fechaIngreso);
        helper.ingresarPaciente(paciente);
        finish();
    }

}
