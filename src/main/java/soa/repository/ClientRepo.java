package soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soa.entities.Clients;

public interface ClientRepo extends JpaRepository<Clients,Long> {
}
