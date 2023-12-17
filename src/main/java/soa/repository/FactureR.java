package soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import soa.entities.Facture;
import soa.entities.Reglement;

import java.util.List;

public interface FactureR extends JpaRepository<Facture, Long> {
    @Query("SELECT f.ReglementList FROM Facture f WHERE f.num = :num")
    List<Reglement> findReglementListByNum(@Param("num") int num);
    @Query("SELECT f FROM Facture f WHERE f.num = :num")
    List<Facture> findFactureByNum(@Param("num") int num);
}
