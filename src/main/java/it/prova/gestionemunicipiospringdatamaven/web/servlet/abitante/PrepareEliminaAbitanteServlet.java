package it.prova.gestionemunicipiospringdatamaven.web.servlet.abitante;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.gestionemunicipiospringdatamaven.model.Abitante;
import it.prova.gestionemunicipiospringdatamaven.service.abitante.AbitanteService;

@WebServlet("/PrepareEliminaAbitanteServlet")
public class PrepareEliminaAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AbitanteService abitanteService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

    public PrepareEliminaAbitanteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long idAbitante = StringUtils.isNumeric(request.getParameter("idAbitante"))?Long.parseLong(request.getParameter("idAbitante")):null;

		try {
			Abitante abitanteDaInviare = abitanteService.caricaSingoloAbitanteEager(idAbitante);
			request.setAttribute("abitanteAttribute", abitanteDaInviare);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/abitante/delete.jsp").forward(request, response);
	}

}
