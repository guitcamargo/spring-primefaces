package br.com.guitcamargo.springprimefaces.repositories;

import br.com.guitcamargo.springprimefaces.domain.PlanetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetaRepository extends JpaRepository<PlanetaEntity, Long> {
}
