package cl.inacap.rca_apr.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class NuevoDatabaseHelper  extends SQLiteOpenHelper {
    private static final String DB_NAME="pacientes2.db";
    private static final int DB_VERSION=1;

    public NuevoDatabaseHelper(Context context) {super(context, DB_NAME,null,DB_VERSION);}  // Para conectar la BDD


    //Se crea la tabla pacientes.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlTxt="create table PACIENTES("
                +"_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"NOMBRE TEXT, "
                +"APELLIDO TEXT, "
                +"EDAD INTEGER, "
                +"DIAGNOSTICO TEXT, "
                +"OBSERVACIONES TEXT, "
                +"FECHAINGRESO TEXT, "
                +"ESTADO INTEGER);";
        sqLiteDatabase.execSQL(sqlTxt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }

    //Se ingresa un paciente con los valores de la clase paciente.
    public void ingresarPaciente(Paciente paciente){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valores=new ContentValues();
        valores.put("NOMBRE",paciente.getNombre());
        valores.put("APELLIDO",paciente.getApellido());
        valores.put("EDAD",paciente.getEdad());
        valores.put("DIAGNOSTICO",paciente.getDiag());
        valores.put("OBSERVACIONES",paciente.getOsb());
        valores.put("FECHAINGRESO",paciente.getFecha());
        if (paciente.isEstado()){
            valores.put("ESTADO",1);
        }else{
            valores.put("ESTADO",0);
        }
        db.insert("PACIENTES",null,valores);
        db.close();
    }

    //Se crea una lista para mostrar los pacientes.
    public List<Paciente> listaPacientes(){
        List<Paciente> pacientes=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query("PACIENTES",
                new String[]{"NOMBRE","APELLIDO","EDAD","DIAGNOSTICO","OBSERVACIONES","FECHAINGRESO","ESTADO"},
                null,null,null,
                null,null
                );
        cursor.moveToFirst();

        int estadoInt;
        boolean estado;
        do {
            estadoInt=cursor.getInt(6);
            if (estadoInt==1) estado=true;
            else estado=false;

            pacientes.add(new Paciente(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    estado));
        }while (cursor.moveToNext());
        cursor.close();
        db.close();

        return pacientes;
    }

    // Esto es para obtener los datos de un solo paciente y ver el detalle de este.  Se guia con el nombre.
    public Paciente getPaciente(String nombre){
        Paciente p;
        SQLiteDatabase db=getReadableDatabase();
        String sqlTxt="SELECT NOMBRE, APELLIDO, EDAD, DIAGNOSTICO, OBSERVACIONES, FECHAINGRESO, ESTADO "
                        +"FROM PACIENTES WHERE NOMBRE=? ";
        String[] argumentos=new String[]{nombre};

        try{
            Cursor cursor=db.rawQuery(sqlTxt,argumentos);
            cursor.moveToFirst();
            boolean estado=false;
            if (cursor.getInt(6)==1) estado=true;
            p=new Paciente(cursor.getString(0),cursor.getString(1),
                    cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),estado);
        }catch (SQLException ex){
            p=null;
        }

        return p;
    }

    // Com este se cambia el estado para dar de alta al paciente.
    public String cambiarEstado(Paciente p){
        int estadoInt;
        if (p.isEstado()) estadoInt=1;
        else estadoInt=0;

        String sqlTxt="UPDATE PACIENTES SET ESTADO=? WHERE NOMBRE=?";
        Object[] argumentos=new Object[]{estadoInt,p.getNombre()};

        try{
            getWritableDatabase().execSQL(sqlTxt,argumentos);
            return "Se cambio correctamente el estado";
        }catch (SQLException ex){
            return "No se puede cambiar el estado";
        }
    }

    // Se eliminan los pacientes que tengan el estado de alta.
    public String eliminarPacientes(){
        String sqlTxt="DELETE FROM PACIENTES WHERE ESTADO=0";
        try{
            getWritableDatabase().execSQL(sqlTxt);
            return "Se eliminaro todos los pacientes";
        }catch (SQLException ex){
            return "No se pueden eliminar los pacientes";
        }
    }
}
