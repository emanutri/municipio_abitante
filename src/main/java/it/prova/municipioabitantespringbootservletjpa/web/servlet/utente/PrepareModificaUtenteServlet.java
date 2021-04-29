package it.prova.municipioabitantespringbootservletjpa.web.servlet.utente;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Ruolo;
import it.prova.municipioabitantespringbootservletjpa.model.Utente;
import it.prova.municipioabitantespringbootservletjpa.service.RuoloService;
import it.prova.municipioabitantespringbootservletjpa.service.UtenteService;

@Component
public class PrepareModificaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UtenteService utenteService;
	@Autowired
	private RuoloService ruoloService;

	public PrepareModificaUtenteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("idUtente");

		try {
			Utente daModificare = utenteService.caricaUtenteEager(Long.parseLong(idParam));
			request.setAttribute("utenteDaModificare", daModificare);
			List<Ruolo> ruoli = ruoloService.listAll();
			request.setAttribute("ruoli_list_attribute", ruoli);
			RequestDispatcher rd = request.getRequestDispatcher("/utente/edit.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
	}

}
