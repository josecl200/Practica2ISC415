import modelos.Estudiante;

import java.util.ArrayList;

public class ControladorLista {
    private ArrayList<Estudiante> listaEstudiantes;
    private static ControladorLista instance;


    public ControladorLista() {
        this.listaEstudiantes = new ArrayList<Estudiante>();
    }

    public static ControladorLista getInstance() {
        if(instance==null){
            instance = new ControladorLista();
        }
        return instance;
    }


    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }
}
