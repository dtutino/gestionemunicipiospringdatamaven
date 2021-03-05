package it.prova.gestionemunicipiospringdatamaven.web.servlet.abitante;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.gestionemunicipiospringdatamaven.dto.AbitanteDTO;
import it.prova.gestionemunicipiospringdatamaven.model.Abitante;
import it.prova.gestionemunicipiospringdatamaven.service.abitante.AbitanteService;
import it.prova.gestionemunicipiospringdatamaven.service.municipio.MunicipioService;

@WebServlet("/ExecuteInsertAbitanteServlet")
public class ExecuteInsertAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AbitanteService abitanteService;
	
	@Autowired
	private MunicipioService municipioService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public ExecuteInsertAbitanteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeInput = request.getParameter("nomeInput");
		String cognomeInput = request.getParameter("cognomeInput");
		String etaInput = request.getParameter("etaInput");
		String residenzaInput = request.getParameter("residenzaInput");
		String municipioId = request.getParameter("municipioId");
		
		int etaParsed = Integer.parseInt(etaInput);
		Long municipioIdParsed = Long.parseLong(municipioId);
		
		AbitanteDTO abitanteDTO = new AbitanteDTO(nomeInput, cognomeInput, etaParsed, residenzaInput, municipioService.caricaSingoloMunicipio(municipioIdParsed));
		System.out.println(municipioService.caricaSingoloMunicipio(municipioIdParsed));
		
		List<String> abitanteErrors = abitanteDTO.errors();
		
		if (!abitanteErrors.isEmpty()) {
			request.setAttribute("abitanteAttribute", abitanteDTO);
			request.setAttribute("municipioAttribute", municipioService.caricaSingoloMunicipio(municipioIdParsed));
			request.setAttribute("abitanteErrors", abitanteErrors);
			request.getRequestDispatcher("/abitante/inserisciNuovo.jsp").forward(request, response);
			return;
		}
		
		Abitante abitanteInstance = AbitanteDTO.buildModelFromDto(abitanteDTO);
		abitanteService.inserisciNuovo(abitanteInstance);

		request.setAttribute("messaggioConferma", "Inserimento avvenuto con successo");
		request.setAttribute("listaAbitanti", abitanteService.listAllAbitanti());
		request.getRequestDispatcher("/abitante/results.jsp").forward(request, response);

	}

}
