package kamegu.trial.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import kamegu.trial.entity.City;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {

	@PersistenceContext
	EntityManager em;

	public List<City> cities() {
		return em.createQuery("FROM City", City.class).getResultList();
	}

	@Transactional
	public void add(String name) {
		City city = new City();
		city.setName(name);
		em.persist(city);
	}
}
