package it.prova.municipioabitantespringbootservletjpa.web.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Utente;
import it.prova.municipioabitantespringbootservletjpa.service.RuoloService;
import it.prova.municipioabitantespringbootservletjpa.service.UtenteService;
import it.prova.municipioabitantespringbootservletjpa.utility.UtilityForm;

@Component
public class ExecuteModificaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UtenteService utenteService;
	@Autowired
	private RuoloService ruoloService;

	public ExecuteModificaUtenteServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("idUtente");
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");
		String confermaPasswordParam = request.getParameter("confermaPassword");
		String[] ruoliParam = request.getParameterValues("ruolo.id");
		Utente utenteInstance = UtilityForm.prepareUtenteFromParams(nomeParam, cognomeParam, usernameParam,
				passwordParam, ruoliParam);
		try {

			if (!UtilityForm.validateUtenteBean(utenteInstance, confermaPasswordParam)) {
				request.setAttribute("utenteDaModificare", utenteInstance);
				request.setAttribute("ruoli_list_attribute", ruoloService.listAll());
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("edit.jsp").forward(request, response);
				return;
			}
			utenteInstance.setId(Long.parseLong(idParam));
			utenteService.aggiorna(utenteInstance);

		} catch (RuntimeException e) {
			e.printStackTrace();
			request.setAttribute("utenteDaModificare", utenteInstance);
			request.setAttribute("ruoli_list_attribute", ruoloService.listAll());
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("edit.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListUtenteServlet?operationResult=SUCCESS");
	}

}
