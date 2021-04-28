package it.prova.municipioabitantespringbootservletjpa.dao;

import java.util.List;

import it.prova.municipioabitantespringbootservletjpa.model.Municipio;

public interface MunicipioDAO extends IBaseDAO<Municipio> {

	public List<Municipio> findAllOrderByDescrizione();

	public List<Municipio> findAllByDescrizioneILike(String term);

	public Municipio findOneEagerAbitanti(Long id);
}
