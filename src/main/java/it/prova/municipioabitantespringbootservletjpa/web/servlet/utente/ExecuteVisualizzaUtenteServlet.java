package it.prova.municipioabitantespringbootservletjpa.web.servlet.utente;

import java.io.IOException;
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
public class ExecuteVisualizzaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UtenteService utenteService;

	public ExecuteVisualizzaUtenteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idUtenteParameter = request.getParameter("idUtente");

		if (!NumberUtils.isCreatable(idUtenteParameter)) {
			request.setAttribute("errorMessage", "Si è verificato un errore");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		try {
			Utente utenteInstance = utenteService.caricaUtenteEager(Long.parseLong(idUtenteParameter));
			if (utenteInstance == null) {
				request.setAttribute("errorMessage", "elemento non trovato");
				request.getRequestDispatcher("ExecuteListUtenteServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}
			//carico utente eager per ottenere i ruoli associati
			request.setAttribute("show_utente_attr", utenteInstance.getRuoli());

			request.setAttribute("show_utente_attr", utenteInstance);
			
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore");
			request.getRequestDispatcher("home");
		}
		request.getRequestDispatcher("show.jsp").forward(request, response);
	}

}
