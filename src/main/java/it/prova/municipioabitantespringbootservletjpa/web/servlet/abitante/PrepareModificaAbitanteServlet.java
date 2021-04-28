package it.prova.municipioabitantespringbootservletjpa.web.servlet.abitante;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Abitante;
import it.prova.municipioabitantespringbootservletjpa.service.AbitanteService;
import it.prova.municipioabitantespringbootservletjpa.service.MunicipioService;

@Component
public class PrepareModificaAbitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareModificaAbitanteServlet() {
		super();
	}

	@Autowired
	private AbitanteService abitanteService;

	@Autowired
	private MunicipioService municipioService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String abitanteId = request.getParameter("idAbitante");

		// FilmService filmService = MyServiceFactory.getFilmServiceInstance();
		Abitante result = null;

		if (!NumberUtils.isCreatable(abitanteId)) {

			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore!");
			request.getRequestDispatcher("/abitante/list.jsp").forward(request, response);
			return;

		}
		RequestDispatcher rd = null;

		try {

			result = abitanteService.caricaSingoloElementoEager(Long.parseLong(abitanteId));
			request.setAttribute("abitante_attribute", result);
			request.setAttribute("list_municipio_attr", municipioService.listAllMunicipi());

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		rd = request.getRequestDispatcher("/abitante/edit.jsp");
		rd.forward(request, response);
	}

}
