package it.prova.municipioabitantespringbootservletjpa.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.municipioabitantespringbootservletjpa.dao.UtenteDAO;
import it.prova.municipioabitantespringbootservletjpa.model.Ruolo;
import it.prova.municipioabitantespringbootservletjpa.model.StatoUtente;
import it.prova.municipioabitantespringbootservletjpa.model.Utente;

@Component
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteDAO utenteDAO;
	
	@Autowired
	private RuoloService ruoloService;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<Utente> listAll() {
		return utenteDAO.list();
	}

	@Transactional(readOnly = true)
	public Utente caricaSingoloElemento(Long id) {
		return utenteDAO.findOne(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Utente utenteInstance) {
		utenteDAO.update(utenteInstance);
	}

	@Transactional
	public void inserisciNuovo(Utente utenteInstance) {
		utenteDAO.insert(utenteInstance);
	}

	@Transactional
	public void rimuovi(Utente utenteInstance) {
		if(utenteInstance.getRuoli().contains(ruoloService.caricaSingoloElemento(1L))) {
			if(utenteDAO.countByAdmin()<=1) {
				throw new RuntimeException("errore, impossibile disattivare l'ultimo admin rimasto");
			}else {
				if (utenteInstance.getStato().name().equalsIgnoreCase(StatoUtente.DISABILITATO.name())) {
					utenteInstance.setStato(StatoUtente.ATTIVO);
				} else if (utenteInstance.getStato().name().equalsIgnoreCase(StatoUtente.ATTIVO.name())) {
					utenteInstance.setStato(StatoUtente.DISABILITATO);
				} else if (utenteInstance.getStato().name().equalsIgnoreCase(StatoUtente.CREATO.name())) {
					utenteInstance.setStato(StatoUtente.ATTIVO);
				}
			}
		}else {
			if (utenteInstance.getStato().name().equalsIgnoreCase(StatoUtente.DISABILITATO.name())) {
				utenteInstance.setStato(StatoUtente.ATTIVO);
			} else if (utenteInstance.getStato().name().equalsIgnoreCase(StatoUtente.ATTIVO.name())) {
				utenteInstance.setStato(StatoUtente.DISABILITATO);
			} else if (utenteInstance.getStato().name().equalsIgnoreCase(StatoUtente.CREATO.name())) {
				utenteInstance.setStato(StatoUtente.ATTIVO);
			}
		}
		
	}

	@Transactional
	public void aggiungiRuolo(Utente utenteEsistente, Ruolo ruoloInstance) {
		// 'attacco' alla sessione di hibernate i due oggetti
		// così jpa capisce che se è già presente quel ruolo non deve essere inserito
		utenteEsistente = entityManager.merge(utenteEsistente);
		ruoloInstance = entityManager.merge(ruoloInstance);

		utenteEsistente.getRuoli().add(ruoloInstance);
		// l'update non viene richiamato a mano in quanto
		// risulta automatico, infatti il contesto di persistenza
		// rileva che utenteEsistente ora è dirty vale a dire che una sua
		// proprieta ha subito una modifica (vale anche per i Set ovviamente)

	}

	@Transactional(readOnly = true)
	public Utente findByUsernameAndPassword(String username, String password) {
		Optional<Utente> result = utenteDAO.findByUsernameAndPassword(username, password);
		return result.isPresent() ? result.get() : null;
	}

	@Transactional(readOnly = true)
	public Utente accedi(String username, String password) {
		Optional<Utente> result = utenteDAO.login(username, password);
		return result.isPresent() ? result.get() : null;
	}

	@Override
	public List<Utente> findByExample(Utente example) {
		return utenteDAO.findByExample(example);
	}

	@Override
	public Utente caricaUtenteEager(Long id) {
		return utenteDAO.findOneEager(id);
	}
}
