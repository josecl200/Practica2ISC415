import controladoras.ControladorLista;
import modelos.Estudiante;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
public class Main {

    public static void main(String[] args){
        //Esta aplicación web correrá en el puerto 8080, cambiar de ser necesario.
        port(8080);
        staticFiles.location("/publico");
        get("/",((request, response) -> {
            ArrayList<Estudiante> estudiantes = ControladorLista.getInstance().getListaEstudiantes();
            Map<String,Object> paramet = new HashMap<>();
            paramet.put("estudiantes", estudiantes);
            return new FreeMarkerEngine().render(new ModelAndView(paramet,"listar.fml"));
        }));

        get("/create",((request, response) -> {
            return new FreeMarkerEngine().render(new ModelAndView(null,"registrar.fml"));
        }));

        post("/create",((request, response) -> {
            Estudiante nuevo = new Estudiante(Integer.parseInt(request.queryParams("matricula")), request.queryParams("nombre"), request.queryParams("apellido"), request.queryParams("telefono"));
            ControladorLista.getInstance().addEstudiante(nuevo);
            response.redirect("/", 303);
            return null;
        }));

        get("/update/:matricula",((request, response) -> {
            Estudiante mod = ControladorLista.getInstance().getEstudiante(Integer.parseInt(request.params("matricula")));
            Map<String,Object> paramet = new HashMap<>();
            paramet.put("estudiante", mod);
            paramet.put("modificar", true);
            return new FreeMarkerEngine().render(new ModelAndView(paramet,"registrar.fml"));
        }));

        post("/update", ((request, response) -> {
            Estudiante nuevo = new Estudiante(Integer.parseInt(request.queryParams("matricula")), request.queryParams("nombre"), request.queryParams("apellido"), request.queryParams("telefono"));
            ControladorLista.getInstance().updateEstudiante(Integer.parseInt(request.queryParams("matricula")),nuevo);
            response.redirect("/", 303);
            return null;
        }));

        get("/delete/:matricula", (request, response) -> {
            ControladorLista.getInstance().deleteEstudiante(Integer.parseInt(request.params(":matricula")));
            response.redirect("/",200);
            return null;
        });


    }
}
