package Controller;

import Model.CarnetModel;
import org.jdom2.Element;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Master on 15/03/2017.
 */
public class CarnetController extends HttpServlet {

//    public static final String VUE = "/WEB-INF/carnet.jsp";
    public static final String VUE = "/index.jsp";
    public static final String CHAMP_FONCTION = "fonction";
    private static CarnetModel cm = null;
    private static List<String> validFunctions = Arrays.asList("getPersonnes", "getPersonneByName",
                                                                "getPersonneById", "getItems", "getItemById",
                                                                "updatePersonne", "addItemToPersonne", "updateItem",
                                                                "deleteItem", "deletePersonne", "addPersonne");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.lauchFunction(request.getParameter( CHAMP_FONCTION ), request);

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Element> personnes = this.getCarnetModel().getPersonnes();

        request.setAttribute("personnes", personnes );

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }


    protected CarnetModel getCarnetModel()
    {
        if (this.cm != null)
            return this.cm;
        return new CarnetModel();
    }

    protected void lauchFunction(String function, HttpServletRequest request)
    {
        String nom        = request.getParameter( "nom" );
        String prenom     = request.getParameter( "prenom" );
        String idPersonne = request.getParameter( "idPersonne" );
        String idItem     = request.getParameter( "idItem" );
        String nomItem    = request.getParameter( "nomItem" );
        String typeItem   = request.getParameter( "typeItem" );
        String date       = request.getParameter( "date" );

        if (!function.equals("") && validFunctions.contains(function))
        {
            CarnetModel cm = this.getCarnetModel();

            if (function.equals("addPersonne") && !nom.equals("") && !prenom.equals("") )
                cm.addPersonne(nom, prenom);
            else if (function.equals("deletePersonne") && !idPersonne.equals(""))
                cm.deletePersonne(idPersonne);
            else if (function.equals("deleteItem") && !idItem.equals(""))
                cm.deleteItem(idItem);
            else if (function.equals("addItemToPersonne") && !idPersonne.equals("") && !nomItem.equals("") && !typeItem.equals("") && !date.equals(""))
                cm.addItemToPersonne(idPersonne, date, nomItem, typeItem);
            else if (function.equals("updateItem") && !idItem.equals("") && !nomItem.equals("") && !typeItem.equals("") && !date.equals(""))
                cm.updateItem(idItem, date, nomItem, typeItem);
            else if (function.equals("updatePersonne") && !nom.equals("") && !prenom.equals("") && !idPersonne.equals("") )
                cm.updatePersonne(idPersonne, nom, prenom);
        }
    }
}
