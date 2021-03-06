package it.prova.municipioabitantespringbootservletjpa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Ruolo;
import it.prova.municipioabitantespringbootservletjpa.model.StatoUtente;
import it.prova.municipioabitantespringbootservletjpa.model.Utente;

@Component
public class UtenteDAOImpl implements UtenteDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Utente> list() {
		return entityManager.createQuery("from Utente", Utente.class).getResultList();
	}

	@Override
	public Optional<Utente> findOne(Long id) {
		Utente result = entityManager.find(Utente.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Utente utenteInstance) {
		if (utenteInstance == null) {
			throw new RuntimeException("Problema valore in input");
		}
		utenteInstance = entityManager.merge(utenteInstance);
	}

	@Override
	public void insert(Utente utenteInstance) {
		if (utenteInstance == null) {
			throw new RuntimeException("Problema valore in input");
		}

		entityManager.persist(utenteInstance);
	}

	@Override
	public void delete(Utente utenteInstance) {
		if (utenteInstance == null) {
			throw new RuntimeException("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(utenteInstance));
	}

	// questo metodo ci torna utile per capire se possiamo rimuovere un ruolo non
	// essendo collegato ad un utente
	public List<Utente> findAllByRuolo(Ruolo ruoloInput) {
		TypedQuery<Utente> query = entityManager.createQuery("select u FROM Utente u join u.ruoli r where r = :ruolo",
				Utente.class);
		query.setParameter("ruolo", ruoloInput);
		return query.getResultList();
	}

	@Override
	public Optional<Utente> findByUsernameAndPassword(String username, String password) {
		TypedQuery<Utente> query = entityManager.createQuery(
				"select u FROM Utente u  " + "where u.username = :username and u.password=:password ", Utente.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		return query.getResultStream().findFirst();
	}

	@Override
	public Optional<Utente> login(String username, String password) {
		TypedQuery<Utente> query = entityManager.createQuery(
				"select u FROM Utente u left join fetch u.ruoli r "
						+ "where u.username = :username and u.password=:password and u.stato=:statoUtente",
				Utente.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		query.setParameter("statoUtente", StatoUtente.ATTIVO);
		return query.getResultStream().findFirst();
	}

	@Override
	public List<Utente> findByExample(Utente example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder(
				"select u from Utente u left join fetch u.ruoli r where u.id = u.id");

		if (StringUtils.isNotEmpty(example.getUsername())) {
			whereClauses.add(" u.username  like :username ");
			paramaterMap.put("username", "%" + example.getUsername() + "%");
		}
		if (StringUtils.isNotEmpty(example.getNome())) {
			whereClauses.add("u.nome like :nome ");
			paramaterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCognome())) {
			whereClauses.add(" u.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + example.getCognome() + "%");
		}
		if (example.getDateCreated() != null) {
			whereClauses.add("u.dateCreated >= :dateCreated ");
			paramaterMap.put("dateCreated", example.getDateCreated());
		}
		if (example.getStato() != null) {
			whereClauses.add("u.stato = :stato ");
			paramaterMap.put("stato", example.getStato());
		}

		// se cerco per ruolo devo cercare in un set di ruoli

		if (example.getRuoli() != null && !example.getRuoli().isEmpty()) {
			whereClauses.add(" r.id in :idList ");
			paramaterMap.put("idList",
					example.getRuoli().stream().map(ruolo -> ruolo.getId()).collect(Collectors.toList()));

		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Utente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Utente.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	public Utente findOneEager(Long id) {
		TypedQuery<Utente> query = entityManager.createQuery("from Utente u left join fetch u.ruoli a where u.id = ?1",
				Utente.class);
		query.setParameter(1, id);
		return query.getSingleResult();
	}
	
	@Override
	public Long countByAdmin() {
		TypedQuery<Long> query = entityManager.createQuery("select count (u.id) FROM Utente u join u.ruoli r where r.id = '1' and u.stato = 'ATTIVO'",
				Long.class);
		return query.getSingleResult();
	}
}
