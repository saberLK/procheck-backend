package ma.pc.Procheck.Service;

import java.util.List;
import java.util.Optional;

import ma.pc.Procheck.Entity.Candidature;

public interface CandidatureService {
	
	//get all candidatures
	public List<Candidature> getAllCandidatures();
	
	//get candidature by id
	public Optional<Candidature> getCandidatureById(Long id);
	
	//save new candidature
	public Candidature saveCandidature(Candidature candidature);
	

}
