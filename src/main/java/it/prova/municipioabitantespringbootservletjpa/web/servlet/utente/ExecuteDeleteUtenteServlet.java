package it.prova.municipioabitantespringbootservletjpa.web.servlet.utente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Utente;
import it.prova.municipioabitantespringbootservletjpa.service.UtenteService;

@Component
public class ExecuteDeleteUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UtenteService utenteService;

	public ExecuteDeleteUtenteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParameter = request.getParameter("idUtente");
		Utente utenteInstance = utenteService.caricaUtenteEager(Long.parseLong(idParameter));

		if (!NumberUtils.isCreatable(idParameter)) {

			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("list.jsp").forward(request, response);
			return;
		}
		try {
			//meth cambia stato
			utenteService.rimuovi(utenteInstance);

			utenteService.aggiorna(utenteInstance);

			request.setAttribute("utenti_list_attribute", utenteService.listAll());
			request.setAttribute("successMessage", "operazione riuscita.");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("utenti_list_attribute", utenteService.listAll());
			request.setAttribute("errorMessage", "errore, impossibile disattivare l'ultimo admin rimasto.");
			request.getRequestDispatcher("list.jsp").forward(request, response);
			return;
		}

		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("ExecuteListUtenteServlet");
		rd.forward(request, response);
	}

}
