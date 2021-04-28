package it.prova.municipioabitantespringbootservletjpa.web.servlet.municipio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Municipio;
import it.prova.municipioabitantespringbootservletjpa.service.MunicipioService;

@Component
public class ExecuteDeleteMunicipioServlet extends HttpServlet {

	@Autowired
	private MunicipioService municipioService;

	private static final long serialVersionUID = 1L;

	public ExecuteDeleteMunicipioServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idMunicipioParameter = request.getParameter("inputId");

		Municipio municipioInstance;
		if (!NumberUtils.isCreatable(idMunicipioParameter)) {

			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/municipio/list.jsp").forward(request, response);
			return;
		}

		try {
			municipioInstance = municipioService.caricaMunicipioEagerAbitanti(Long.parseLong(idMunicipioParameter));
			if (!municipioInstance.getAbitanti().isEmpty()) {
				request.setAttribute("errorMessage",
						"Non puoi eliminare un municipio con degli abitanti ancora associati");
				request.setAttribute("municipi_list_attribute", municipioService.listAllMunicipi());
				request.getRequestDispatcher("/municipio/list.jsp");
			} else {

				municipioService.rimuovi(municipioInstance);
				request.setAttribute("municipi_list_attribute", municipioService.listAllMunicipi());
				request.setAttribute("successMessage", "operazione effettuata con successo");

			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "attenzione, qualcosa è andato storto");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/municipio/list.jsp").forward(request, response);
	}

}
