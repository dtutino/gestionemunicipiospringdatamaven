package it.prova.gestionemunicipiospringdatamaven.web.servlet.municipio;

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

import it.prova.gestionemunicipiospringdatamaven.dto.MunicipioDTO;
import it.prova.gestionemunicipiospringdatamaven.model.Municipio;
import it.prova.gestionemunicipiospringdatamaven.service.municipio.MunicipioService;

@WebServlet("/ExecuteModificaMunicipioServlet")
public class ExecuteModificaMunicipioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MunicipioService municipioService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

    public ExecuteModificaMunicipioServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long idMunicipio = StringUtils.isNumeric(request.getParameter("idMunicipio"))?Long.parseLong(request.getParameter("idMunicipio")):null;
		String descrizioneInput = request.getParameter("descrizione");
		String codiceInput = request.getParameter("codice");
		String ubicazioneInput = request.getParameter("ubicazione");

		MunicipioDTO municipioDTO = new MunicipioDTO(idMunicipio, descrizioneInput, codiceInput, ubicazioneInput);

		
		List<String> municipioErrors = municipioDTO.errors();
		if (!municipioErrors.isEmpty()) {
			request.setAttribute("municipioAttribute", municipioDTO);
			request.setAttribute("municipioErrors", municipioErrors);
			request.getRequestDispatcher("/municipio/edit.jsp").forward(request, response);
			return;
		}
		
		Municipio municipioInstance = MunicipioDTO.buildModelFromDto(municipioDTO);
		municipioService.aggiorna(municipioInstance);

		request.setAttribute("messaggioConferma", "Inserimento avvenuto con successo");
		request.setAttribute("listaMunicipi", municipioService.listAllMunicipi());
		request.getRequestDispatcher("/municipio/results.jsp").forward(request, response);
	}

}
