package ma.pc.Procheck.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.pc.Procheck.Entity.Candidature;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long>{

}
