package it.prova.gestionemunicipiospringdatamaven.web.servlet.municipio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestionemunicipiospringdatamaven.dto.MunicipioDTO;

@WebServlet("/PrepareInsertMunicipioServlet")
public class PrepareInsertMunicipioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareInsertMunicipioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("municipioAttribute", new MunicipioDTO());
		request.getRequestDispatcher("/municipio/insert.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
