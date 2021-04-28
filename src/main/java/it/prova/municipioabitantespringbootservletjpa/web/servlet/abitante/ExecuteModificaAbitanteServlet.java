package it.prova.municipioabitantespringbootservletjpa.web.servlet.abitante;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Abitante;
import it.prova.municipioabitantespringbootservletjpa.service.AbitanteService;
import it.prova.municipioabitantespringbootservletjpa.service.MunicipioService;
import it.prova.municipioabitantespringbootservletjpa.utility.UtilityForm;

@Component
public class ExecuteModificaAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AbitanteService abitanteService;

	@Autowired
	private MunicipioService municipioService;

	public ExecuteModificaAbitanteServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParameter = request.getParameter("idAbitante");
		String nomeParameter = request.getParameter("nome");
		String cognomeParameter = request.getParameter("cognome");
		String dataParameter = request.getParameter("dataDiNascita");
		String residenzaParameter = request.getParameter("residenza");
		String municipioIdParameter = request.getParameter("municipio.id");

		Abitante abitanteInstance = UtilityForm.createAbitanteFromParams(nomeParameter, cognomeParameter,
				residenzaParameter, dataParameter, municipioIdParameter);
		abitanteInstance.setId(Long.parseLong(idParameter));

		try {

			if (!UtilityForm.validateAbitanteBean(abitanteInstance)) {

				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.setAttribute("abitante_attribute", abitanteInstance);
				request.setAttribute("list_municipio_attr", municipioService.listAllMunicipi());
				request.getRequestDispatcher("/abitante/edit.jsp").forward(request, response);
				return;
			}

			abitanteService.aggiorna(abitanteInstance);
			request.setAttribute("abitanti_list_attribute", abitanteService.listAllAbitanti());
			request.setAttribute("successMessage", "Operazione effettuata con successo");

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("/abitante/list.jsp").forward(request, response);
	}

}
