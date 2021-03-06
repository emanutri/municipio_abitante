package it.prova.municipioabitantespringbootservletjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.prova.municipioabitantespringbootservletjpa.web.servlet.abitante.ExecuteDeleteAbitanteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.abitante.ExecuteInsertAbitanteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.abitante.ExecuteListAbitanteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.abitante.ExecuteModificaAbitanteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.abitante.ExecuteSearchAbitanteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.abitante.ExecuteVisualizzaAbitanteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.abitante.PrepareDeleteAbitanteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.abitante.PrepareInsertAbitanteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.abitante.PrepareModificaAbitanteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.abitante.PrepareSearchAbitanteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.auth.LoginServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.municipio.ExecuteDeleteMunicipioServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.municipio.ExecuteInsertMunicipioServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.municipio.ExecuteListMunicipioServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.municipio.ExecuteModificaMunicipioServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.municipio.ExecuteSearchMunicipioServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.municipio.ExecuteVisualizzaMunicipioServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.municipio.PrepareDeleteMunicipioServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.municipio.PrepareModificaMunicipioServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.utente.ExecuteDeleteUtenteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.utente.ExecuteInsertUtenteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.utente.ExecuteListUtenteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.utente.ExecuteModificaUtenteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.utente.ExecuteSearchUtenteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.utente.ExecuteVisualizzaUtenteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.utente.PrepareInsertUtenteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.utente.PrepareModificaUtenteServlet;
import it.prova.municipioabitantespringbootservletjpa.web.servlet.utente.PrepareSearchUtenteServlet;

@Configuration
public class SerlvetRegistrationConfig {
	// N.B. se le servlet usano bean al loro interno vanno affidate a spring
	// altrimenti va bene @WebServlet

	@Autowired
	private LoginServlet loginServlet;
	@Autowired
	private ExecuteSearchMunicipioServlet executeSearchMunicipioServlet;
	@Autowired
	private ExecuteInsertMunicipioServlet executeInsertMunicipioServlet;
	@Autowired
	private ExecuteListMunicipioServlet executeListMunicipioServlet;
	@Autowired
	private PrepareSearchAbitanteServlet prepareSearchAbitanteServlet;
	@Autowired
	private PrepareInsertAbitanteServlet prepareInsertAbitanteServlet;
	@Autowired
	private PrepareDeleteAbitanteServlet prepareDeleteAbitanteServlet;
	@Autowired
	private PrepareModificaAbitanteServlet prepareModificaAbitanteServlet;
	@Autowired
	private PrepareDeleteMunicipioServlet prepareDeleteMunicipioServlet;
	@Autowired
	private PrepareModificaMunicipioServlet prepareModificaMunicipioServlet;
	@Autowired
	private PrepareSearchUtenteServlet prepareSearchUtenteServlet;
	@Autowired
	private PrepareInsertUtenteServlet prepareInsertUtenteServlet;
	@Autowired
	private PrepareModificaUtenteServlet prepareModificaUtenteServlet;

	@Autowired
	private ExecuteInsertAbitanteServlet executeInsertAbitanteServlet;
	@Autowired
	private ExecuteListAbitanteServlet executeListAbitanteServlet;
	@Autowired
	private ExecuteVisualizzaAbitanteServlet executeVisualizzaAbitanteServlet;
	@Autowired
	private ExecuteSearchAbitanteServlet executeSearchAbitanteServlet;
	@Autowired
	private ExecuteDeleteAbitanteServlet executeDeleteAbitanteServlet;
	@Autowired
	private ExecuteModificaAbitanteServlet executeModificaAbitanteServlet;
	@Autowired
	private ExecuteVisualizzaMunicipioServlet executeVisualizzaMunicipioServlet;
	@Autowired
	private ExecuteDeleteMunicipioServlet executeDeleteMunicipioServlet;
	@Autowired
	private ExecuteModificaMunicipioServlet executeModificaMunicipioServlet;
	@Autowired
	private ExecuteSearchUtenteServlet executeSearchUtenteServlet;
	@Autowired
	private ExecuteInsertUtenteServlet executeInsertUtenteServlet;
	@Autowired
	private ExecuteListUtenteServlet executeListUtenteServlet;
	@Autowired
	private ExecuteVisualizzaUtenteServlet executeVisualizzaUtenteServlet;
	@Autowired
	private ExecuteDeleteUtenteServlet executeDeleteUtenteServlet;
	@Autowired
	private ExecuteModificaUtenteServlet executeModificaUtenteServlet;

	@Bean
	public ServletRegistrationBean<LoginServlet> createLoginServletBean() {
		ServletRegistrationBean<LoginServlet> bean = new ServletRegistrationBean<LoginServlet>(loginServlet,
				"/LoginServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteSearchMunicipioServlet> createExecuteSearchMunicipioServletBean() {
		ServletRegistrationBean<ExecuteSearchMunicipioServlet> bean = new ServletRegistrationBean<ExecuteSearchMunicipioServlet>(
				executeSearchMunicipioServlet, "/ExecuteSearchMunicipioServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteInsertMunicipioServlet> createExecuteInsertMunicipioServletBean() {
		ServletRegistrationBean<ExecuteInsertMunicipioServlet> bean = new ServletRegistrationBean<ExecuteInsertMunicipioServlet>(
				executeInsertMunicipioServlet, "/ExecuteInsertMunicipioServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteListMunicipioServlet> createExecuteListRegistaServletBean() {
		ServletRegistrationBean<ExecuteListMunicipioServlet> bean = new ServletRegistrationBean<ExecuteListMunicipioServlet>(
				executeListMunicipioServlet, "/ExecuteListMunicipioServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<PrepareSearchAbitanteServlet> createPrepareSearchAbitanteServletBean() {
		ServletRegistrationBean<PrepareSearchAbitanteServlet> bean = new ServletRegistrationBean<PrepareSearchAbitanteServlet>(
				prepareSearchAbitanteServlet, "/PrepareSearchAbitanteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<PrepareInsertAbitanteServlet> createPrepareInsertAbitanteServletBean() {
		ServletRegistrationBean<PrepareInsertAbitanteServlet> bean = new ServletRegistrationBean<PrepareInsertAbitanteServlet>(
				prepareInsertAbitanteServlet, "/PrepareInsertAbitanteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<PrepareDeleteAbitanteServlet> createPrepareDeleteAbitanteServletBean() {
		ServletRegistrationBean<PrepareDeleteAbitanteServlet> bean = new ServletRegistrationBean<PrepareDeleteAbitanteServlet>(
				prepareDeleteAbitanteServlet, "/PrepareDeleteAbitanteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<PrepareModificaAbitanteServlet> createPrepareModificaAbitanteServletBean() {
		ServletRegistrationBean<PrepareModificaAbitanteServlet> bean = new ServletRegistrationBean<PrepareModificaAbitanteServlet>(
				prepareModificaAbitanteServlet, "/PrepareModificaAbitanteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<PrepareDeleteMunicipioServlet> createPrepareDeleteMunicipioServletBean() {
		ServletRegistrationBean<PrepareDeleteMunicipioServlet> bean = new ServletRegistrationBean<PrepareDeleteMunicipioServlet>(
				prepareDeleteMunicipioServlet, "/PrepareDeleteMunicipioServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<PrepareModificaMunicipioServlet> createPrepareModificaMunicipioServletBean() {
		ServletRegistrationBean<PrepareModificaMunicipioServlet> bean = new ServletRegistrationBean<PrepareModificaMunicipioServlet>(
				prepareModificaMunicipioServlet, "/PrepareModificaMunicipioServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<PrepareInsertUtenteServlet> createPrepareInsertUtenteServletBean() {
		ServletRegistrationBean<PrepareInsertUtenteServlet> bean = new ServletRegistrationBean<PrepareInsertUtenteServlet>(
				prepareInsertUtenteServlet, "/utente/PrepareInsertUtenteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<PrepareSearchUtenteServlet> createPrepareSearchUtenteServletBean() {
		ServletRegistrationBean<PrepareSearchUtenteServlet> bean = new ServletRegistrationBean<PrepareSearchUtenteServlet>(
				prepareSearchUtenteServlet, "/utente/PrepareSearchUtenteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<PrepareModificaUtenteServlet> createPrepareModificaUtenteServletBean() {
		ServletRegistrationBean<PrepareModificaUtenteServlet> bean = new ServletRegistrationBean<PrepareModificaUtenteServlet>(
				prepareModificaUtenteServlet, "/utente/PrepareModificaUtenteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteInsertAbitanteServlet> createExecuteInsertAbitanteServletBean() {
		ServletRegistrationBean<ExecuteInsertAbitanteServlet> bean = new ServletRegistrationBean<ExecuteInsertAbitanteServlet>(
				executeInsertAbitanteServlet, "/ExecuteInsertAbitanteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteListAbitanteServlet> createExecuteListAbitanteServletBean() {
		ServletRegistrationBean<ExecuteListAbitanteServlet> bean = new ServletRegistrationBean<ExecuteListAbitanteServlet>(
				executeListAbitanteServlet, "/ExecuteListAbitanteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteVisualizzaAbitanteServlet> createExecuteVisualizzaAbitanteServletBean() {
		ServletRegistrationBean<ExecuteVisualizzaAbitanteServlet> bean = new ServletRegistrationBean<ExecuteVisualizzaAbitanteServlet>(
				executeVisualizzaAbitanteServlet, "/ExecuteVisualizzaAbitanteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteSearchAbitanteServlet> createExecuteSearchAbitanteServletBean() {
		ServletRegistrationBean<ExecuteSearchAbitanteServlet> bean = new ServletRegistrationBean<ExecuteSearchAbitanteServlet>(
				executeSearchAbitanteServlet, "/ExecuteSearchAbitanteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteDeleteAbitanteServlet> createExecuteDeleteAbitanteServletBean() {
		ServletRegistrationBean<ExecuteDeleteAbitanteServlet> bean = new ServletRegistrationBean<ExecuteDeleteAbitanteServlet>(
				executeDeleteAbitanteServlet, "/ExecuteDeleteAbitanteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteModificaAbitanteServlet> createExecuteModificaAbitanteServletBean() {
		ServletRegistrationBean<ExecuteModificaAbitanteServlet> bean = new ServletRegistrationBean<ExecuteModificaAbitanteServlet>(
				executeModificaAbitanteServlet, "/ExecuteModificaAbitanteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteVisualizzaMunicipioServlet> createExecuteVisualizzaMunicipioServletBean() {
		ServletRegistrationBean<ExecuteVisualizzaMunicipioServlet> bean = new ServletRegistrationBean<ExecuteVisualizzaMunicipioServlet>(
				executeVisualizzaMunicipioServlet, "/ExecuteVisualizzaMunicipioServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteDeleteMunicipioServlet> createExecuteDeleteMunicipioServletBean() {
		ServletRegistrationBean<ExecuteDeleteMunicipioServlet> bean = new ServletRegistrationBean<ExecuteDeleteMunicipioServlet>(
				executeDeleteMunicipioServlet, "/ExecuteDeleteMunicipioServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteModificaMunicipioServlet> createExecuteModificaMunicipioServletBean() {
		ServletRegistrationBean<ExecuteModificaMunicipioServlet> bean = new ServletRegistrationBean<ExecuteModificaMunicipioServlet>(
				executeModificaMunicipioServlet, "/ExecuteModificaMunicipioServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteSearchUtenteServlet> createExecuteSearchUtenteServletBean() {
		ServletRegistrationBean<ExecuteSearchUtenteServlet> bean = new ServletRegistrationBean<ExecuteSearchUtenteServlet>(
				executeSearchUtenteServlet, "/utente/ExecuteSearchUtenteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteInsertUtenteServlet> createExecuteInsertUtenteServletBean() {
		ServletRegistrationBean<ExecuteInsertUtenteServlet> bean = new ServletRegistrationBean<ExecuteInsertUtenteServlet>(
				executeInsertUtenteServlet, "/utente/ExecuteInsertUtenteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteListUtenteServlet> createExecuteListUtenteServletBean() {
		ServletRegistrationBean<ExecuteListUtenteServlet> bean = new ServletRegistrationBean<ExecuteListUtenteServlet>(
				executeListUtenteServlet, "/utente/ExecuteListUtenteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteVisualizzaUtenteServlet> createExecuteVisualizzaUtenteServletBean() {
		ServletRegistrationBean<ExecuteVisualizzaUtenteServlet> bean = new ServletRegistrationBean<ExecuteVisualizzaUtenteServlet>(
				executeVisualizzaUtenteServlet, "/utente/ExecuteVisualizzaUtenteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteDeleteUtenteServlet> createExecuteDeleteUtenteServletBean() {
		ServletRegistrationBean<ExecuteDeleteUtenteServlet> bean = new ServletRegistrationBean<ExecuteDeleteUtenteServlet>(
				executeDeleteUtenteServlet, "/utente/ExecuteDeleteUtenteServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteModificaUtenteServlet> createExecuteModificaUtenteServletBean() {
		ServletRegistrationBean<ExecuteModificaUtenteServlet> bean = new ServletRegistrationBean<ExecuteModificaUtenteServlet>(
				executeModificaUtenteServlet, "/utente/ExecuteModificaUtenteServlet");
		return bean;
	}
}
