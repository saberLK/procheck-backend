package ma.pc.Procheck.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ma.pc.Procheck.Entity.Candidature;
import ma.pc.Procheck.Service.CandidatureService;

@RestController
@RequestMapping("/api")
public class CandidatureController {
	
	//directory of uploaded cv see application.properties
	@Value("${upload_dir}")
	private String uploadDir;
	 
	@Autowired
	private CandidatureService candidatureService;
	
	//get all candidature API
	@CrossOrigin
	@GetMapping("/all")
	public List<Candidature> getAllCandidatures(){
		return candidatureService.getAllCandidatures();
	}
	
	//get candidature by id
	@CrossOrigin
	@GetMapping(value="/candidature/{id}")
	public Optional<Candidature> getCandidature(@PathVariable Long id) {
		return candidatureService.getCandidatureById(id);
	}
	
	//get CV by given Name
	@CrossOrigin
	@GetMapping("getcv/{cvName}/{lastName}/{firstName}")
    public ResponseEntity<byte[]> getCVByName(@PathVariable String cvName,@PathVariable String lastName,@PathVariable String firstName) {
        try {
            Path cvPath = Paths.get(uploadDir+'/'+lastName + ' ' + firstName, cvName);
            byte[] cvBytes = Files.readAllBytes(cvPath);
            MediaType contentType = null;
            if(cvName.contains("pdf")) {
            	contentType = MediaType.APPLICATION_PDF;
            }
            else if (cvName.contains("jpeg") || cvName.contains("jpg") ) {
            	contentType = MediaType.IMAGE_JPEG;
            }
            return ResponseEntity.ok()
                    .contentType(contentType) // Set the appropriate content type
                    .body(cvBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
	
	//Upload cv to directory of the candidat
	@CrossOrigin
	@PostMapping(value="/cv")
	public void uploadCv(@RequestParam("cvFile") MultipartFile cvFile,@RequestParam("lastName") String lastName,@RequestParam("firstName") String firstName){

		// Create directory if not exists
        File directory = new File(uploadDir+ lastName + ' ' +firstName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
        	String originalFileName = cvFile.getOriginalFilename();
            String filePath = directory.getAbsolutePath() + "/" + cvFile.getOriginalFilename();
            System.out.println("directory abosolute path"+directory.getAbsolutePath());
            File dest = new File(filePath);
            cvFile.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed");
        }
	return;
	
	}
	
	
	//save new candidature
	@CrossOrigin
	@PostMapping(value="/candidature")
	public Candidature saveCandidature(@RequestBody Candidature candidature) {
		return candidatureService.saveCandidature(candidature);
	}
	
	
	
}
