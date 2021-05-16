/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import taw.dao.EventoFacade;
import taw.dao.InscripcionFacade;
import taw.dao.UsuarioFacade;
import taw.entity.Evento;
import taw.entity.Inscripcion;
import taw.entity.Usuario;

/**
 *
 * @author migue
 */
@WebServlet(name = "ServletRegistroEnEvento", urlPatterns = {"/ServletRegistroEnEvento"})
public class ServletRegistroEnEvento extends HttpServlet {

    
    @EJB
    public UsuarioFacade usuarioFacade;
    
    @EJB 
    public InscripcionFacade inscripcionFacade;
    
    @EJB
    public EventoFacade eventoFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //List<String> listaAsientosSeleccionados = new ArrayList<>();
        String strIdEvento = request.getParameter("idEvento");
        String strAsientosSeleccionados = request.getParameter("asientosSeleccionados");
        SimpleDateFormat formateador = new SimpleDateFormat("dd/mm/yyyy");
        String strNEntradasNoNumeradas = request.getParameter("nEntradas");
        
        
        
       
        
        Evento evento = this.eventoFacade.find(Integer.parseInt(strIdEvento));
        HttpSession sesion = request.getSession();
        Usuario usuario = this.usuarioFacade.find(sesion.getAttribute("idUsuario"));
        if(strNEntradasNoNumeradas == null || strNEntradasNoNumeradas.isEmpty()){
            if(strAsientosSeleccionados != ""){
             String[] aux = strAsientosSeleccionados.split(",");
                for(String s : aux){
                    String[] aux2 = s.split("-");
                    int fila = Integer.parseInt(aux2[0]);
                    int columna = Integer.parseInt(aux2[1]);

                    Inscripcion inscripcion = new Inscripcion();


                    Date date = new Date(System.currentTimeMillis());
                    inscripcion.setEvento(evento);
                    inscripcion.setFila(fila);
                    inscripcion.setColumna(columna);
                    inscripcion.setFechaInscripcion(date);
                    inscripcion.setUsuario(usuario);

                    this.inscripcionFacade.create(inscripcion);  
                }
            }
        }else{
            int nEntradasNoNumeradas = Integer.parseInt(strNEntradasNoNumeradas);
            Inscripcion inscripcion = new Inscripcion();
            Date date = new Date(System.currentTimeMillis());
            inscripcion.setEvento(evento);
            nEntradasNoNumeradas = 0 - nEntradasNoNumeradas;
            inscripcion.setFila(nEntradasNoNumeradas);
            inscripcion.setColumna(nEntradasNoNumeradas);
            inscripcion.setFechaInscripcion(date);
            inscripcion.setUsuario(usuario);
            this.inscripcionFacade.create(inscripcion); 
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("principal.jsp");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
