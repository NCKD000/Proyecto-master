package cl.inacap.rca_apr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import cl.inacap.rca_apr.modelo.Paciente;
import cl.inacap.rca_apr.modelo.NuevoDatabaseHelper;


public class MenuPacientesActivity extends AppCompatActivity {

    private NuevoDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pacientes);
    }

    // Con la ayuda del helper carga la lista con todos los pacienten ingresados.
    public void verLista(View view){
        helper = new NuevoDatabaseHelper(this);
        try{
            ArrayList<Paciente> pacientes=(ArrayList<Paciente>) helper.listaPacientes();
            Intent intent = new Intent(this, ListaPacientesActivity.class);
            startActivity(intent);
        }catch (Exception ex){
            Toast.makeText(this,"La lista esta vacia",Toast.LENGTH_SHORT).show();
        }
    }

    // Redirige a la pantalla de agregar paciente.
    public void ingresarNuevo(View view){
        Intent intent = new Intent(this, NuevoPacienteActivity.class);
        startActivity(intent);
    }

    // Elimina todos los pacientes que esten de alta.
    public void eliminarPaciente(View view){
        helper = new NuevoDatabaseHelper(this);
        String msg = helper.eliminarPacientes();
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}
