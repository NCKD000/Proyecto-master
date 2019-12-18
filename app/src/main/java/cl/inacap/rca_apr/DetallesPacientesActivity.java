package cl.inacap.rca_apr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import cl.inacap.rca_apr.modelo.Paciente;
import cl.inacap.rca_apr.modelo.NuevoDatabaseHelper;


public class DetallesPacientesActivity extends AppCompatActivity {

    private Paciente paciente;
    private Intent intent;
    private NuevoDatabaseHelper helper = new NuevoDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_pacientes);

        //Obtener paciente
        intent = getIntent();
        String nombrePaciente = (String)intent.getExtras().get("nombrePaciente");
        paciente = helper.getPaciente(nombrePaciente);

        //Mostrar paciente con los datos.
        TextView txtNombre = (TextView)findViewById(R.id.txtNombre);
        txtNombre.setText("Nombres: "+paciente.getNombre());

        TextView txtApellido = (TextView)findViewById(R.id.txtApellido);
        txtApellido.setText("Apellidos: "+paciente.getApellido());

        TextView txtedad = (TextView)findViewById(R.id.txtEdad);
        txtedad.setText("Edad: "+paciente.getEdad());

        TextView txtDiag = (TextView)findViewById(R.id.txtDiag);
        txtDiag.setText("Diagnostico: "+paciente.getDiag());

        TextView txtOsb = (TextView)findViewById(R.id.txtOsb);
        txtOsb.setText("Observaciones: "+paciente.getOsb());

        TextView txtFechadeIngreso = (TextView)findViewById(R.id.txtFechadeIngreso);
        txtFechadeIngreso.setText("Fecha de Ingreso: "+paciente.getFecha());

        TextView txtEstado = (TextView)findViewById(R.id.txtEstado);
        Button cambiar = (Button)findViewById(R.id.estado);

        if (paciente.isEstado()){
            txtEstado.setText("Actualmente en tratamiento");
            cambiar.setText("Marcar de alta");
        }else{
            txtEstado.setText("Esta de alta");
            cambiar.setText("Marcar en tratamiento");
        }
    }

    // Hace un cambio de estado, el cual esta en el databasehelper.
    public void cambiarEstado(View view){
        paciente.setEstado(!paciente.isEstado());

        helper.cambiarEstado(paciente);
        setResult(RESULT_OK,intent);
        finish();
    }
}
