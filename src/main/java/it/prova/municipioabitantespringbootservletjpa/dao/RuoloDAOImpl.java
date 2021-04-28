package it.prova.municipioabitantespringbootservletjpa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Ruolo;

@Component
public class RuoloDAOImpl implements RuoloDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Ruolo> list() {
		return entityManager.createQuery("from Ruolo", Ruolo.class).getResultList();

	}

	@Override
	public Optional<Ruolo> findOne(Long id) {
		Ruolo result = entityManager.find(Ruolo.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Ruolo ruoloInstance) {
		if (ruoloInstance == null) {
			throw new RuntimeException("Problema valore in input");
		}
		ruoloInstance = entityManager.merge(ruoloInstance);
	}

	@Override
	public void insert(Ruolo ruoloInstance) {
		if (ruoloInstance == null) {
			throw new RuntimeException("Problema valore in input");
		}
		entityManager.persist(ruoloInstance);
	}

	@Override
	public void delete(Ruolo ruoloInstance) {
		if (ruoloInstance == null) {
			throw new RuntimeException("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(ruoloInstance));
	}

	@Override
	public Ruolo findByDescrizioneAndCodice(String descrizione, String codice) {
		TypedQuery<Ruolo> query = entityManager
				.createQuery("select r from Ruolo r where r.descrizione=?1 and r.codice=?2", Ruolo.class)
				.setParameter(1, descrizione).setParameter(2, codice);

		return query.getResultStream().findFirst().orElse(null);
	}

	@Override
	public List<Ruolo> findByExample(Ruolo example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select r from Ruolo r where r.id = r.id ");

		if (StringUtils.isNotEmpty(example.getDescrizione())) {
			whereClauses.add(" r.descrizione  like :descrizione ");
			paramaterMap.put("descrizione", "%" + example.getDescrizione() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add("r.codice like :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Ruolo> typedQuery = entityManager.createQuery(queryBuilder.toString(), Ruolo.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
