package it.prova.municipioabitantespringbootservletjpa.web.servlet.municipio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Municipio;
import it.prova.municipioabitantespringbootservletjpa.service.MunicipioService;
import it.prova.municipioabitantespringbootservletjpa.utility.UtilityForm;

//@WebServlet("/ExecuteModificaMunicipioServlet")
@Component
public class ExecuteModificaMunicipioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MunicipioService municipioService;

	public ExecuteModificaMunicipioServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParameter = request.getParameter("idMunicipio");
		String codiceMunicipioParameter = request.getParameter("codice");
		String descrizioneMunicipioParameter = request.getParameter("descrizione");
		String ubicazioneMunicipioParameter = request.getParameter("ubicazione");

		Municipio municipioInstance = UtilityForm.createMunicipioFromParams(codiceMunicipioParameter,
				descrizioneMunicipioParameter, ubicazioneMunicipioParameter);
		municipioInstance.setId(Long.parseLong(idParameter));

		if (!UtilityForm.validateMunicipioBean(municipioInstance)) {

			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.setAttribute("municipio_attribute", municipioInstance);
			request.getRequestDispatcher("/municipio/edit.jsp").forward(request, response);
			return;
		}

		try {

			municipioService.aggiorna(municipioInstance);
			request.setAttribute("municipi_list_attribute", municipioService.listAllMunicipi());
			request.setAttribute("successMessage", "Operazione effettuata con successo");

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("/municipio/list.jsp").forward(request, response);
	}

}
