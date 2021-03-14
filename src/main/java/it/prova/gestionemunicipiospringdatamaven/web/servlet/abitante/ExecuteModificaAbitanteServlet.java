package it.prova.gestionemunicipiospringdatamaven.web.servlet.abitante;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.prova.gestionemunicipiospringdatamaven.dto.AbitanteDTO;
import it.prova.gestionemunicipiospringdatamaven.model.Abitante;
import it.prova.gestionemunicipiospringdatamaven.service.abitante.AbitanteService;
import it.prova.gestionemunicipiospringdatamaven.service.municipio.MunicipioService;

@WebServlet("/ExecuteModificaAbitanteServlet")
public class ExecuteModificaAbitanteServlet extends HttpServlet {
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

    public ExecuteModificaAbitanteServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long idAbitante = StringUtils.isNumeric(request.getParameter("idAbitante"))?Long.parseLong(request.getParameter("idAbitante")):null;
		String nomeInput = request.getParameter("nomeInput");
		String cognomeInput = request.getParameter("cognomeInput");
		int etaInput = StringUtils.isNumeric(request.getParameter("etaInput"))?Integer.parseInt(request.getParameter("etaInput")):null;
		String residenzaInput = request.getParameter("residenzaInput");
		String municipioId = request.getParameter("municipioId");
		Long municipioIdParsed = Long.parseLong(municipioId);
		AbitanteDTO abitanteDTO = new AbitanteDTO(idAbitante, nomeInput, cognomeInput, etaInput, residenzaInput, municipioService.caricaSingoloMunicipio(municipioIdParsed));

		
		List<String> abitanteErrors = abitanteDTO.errors();
		if (!abitanteErrors.isEmpty()) {
			request.setAttribute("abitanteAttribute", abitanteDTO);
			request.setAttribute("abitanteErrors", abitanteErrors);
			request.getRequestDispatcher("/abitante/edit.jsp").forward(request, response);
			return;
		}
		
		Abitante abitanteInstance = AbitanteDTO.buildModelFromDto(abitanteDTO);
		abitanteService.aggiorna(abitanteInstance);

		request.setAttribute("messaggioConferma", "Inserimento avvenuto con successo");
		abitanteService.findByExample2(abitanteInstance);
		request.getRequestDispatcher("/abitante/results.jsp").forward(request, response);
	}

}
