package cl.inacap.rca_apr;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;
import cl.inacap.rca_apr.modelo.NuevoDatabaseHelper;
import cl.inacap.rca_apr.modelo.Paciente;


public class ListaPacientesActivity extends ListActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cargarLista();
    }

    // Con esto se carga la lista de pacientes que esta en la BDD.
    public void cargarLista(){
        lista = getListView();

        NuevoDatabaseHelper helper=new NuevoDatabaseHelper(this);

        List<Paciente> pacienteList=helper.listaPacientes();

        ArrayAdapter<Paciente> listaAdapter= new ArrayAdapter<Paciente>(this, android.R.layout.simple_expandable_list_item_1,pacienteList);
        lista.setAdapter(listaAdapter);

    }

    // Se recorre la lista de pacientes para listarlos con su nombre y estado.
    @Override
    public void onListItemClick(ListView listView, View view, int posicion, long id){

        Object o=lista.getItemAtPosition(posicion);
        String linea=o.toString();
        String[] separar=linea.split(":");
        Intent intent = new Intent(ListaPacientesActivity.this, DetallesPacientesActivity.class);
        intent.putExtra("nombrePaciente",separar[0]);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resulCode, Intent data){
        super.onActivityResult(requestCode,resulCode,data);
        if (requestCode==1){
            if (resulCode==RESULT_OK){
                cargarLista();
            }
        }
    }

}
