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
public class PrepareDeleteMunicipioServlet extends HttpServlet {

	@Autowired
	private MunicipioService municipioService;

	private static final long serialVersionUID = 1L;

	public PrepareDeleteMunicipioServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idMunicipioParameter = request.getParameter("idMunicipio");

		if (!NumberUtils.isCreatable(idMunicipioParameter)) {
			request.setAttribute("errorMessage", "Si è verificato un errore");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		try {
			Municipio municipioInstance = municipioService
					.caricaMunicipioEagerAbitanti(Long.parseLong(idMunicipioParameter));
			if (municipioInstance == null) {
				request.setAttribute("errorMessage", "elemento non trovato");
				request.getRequestDispatcher("ExecuteListMunicipioServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}
			request.setAttribute("municipio_attribute", municipioInstance);

		} catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore");
			request.getRequestDispatcher("home");
		}
		request.getRequestDispatcher("/municipio/delete.jsp").forward(request, response);
	}
}
