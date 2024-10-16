package com.iteam.Gestion.Expert.services;

import com.iteam.Gestion.Expert.configimage.ImageStorage;
import com.iteam.Gestion.Expert.dto.*;
import com.iteam.Gestion.Expert.entities.Expert;
import com.iteam.Gestion.Expert.entities.Mission;
import com.iteam.Gestion.Expert.entities.Postuleoffre;
import com.iteam.Gestion.Expert.reposetories.ExpertRepository;
import com.iteam.Gestion.Expert.reposetories.MissionRepesitory;
import com.iteam.Gestion.Expert.reposetories.PostuleoffreRepository;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostuleoffreServiceimpl implements PostuleoffreService{
    private final  PostuleoffreRepository postuleoffreRepository;
    private final MissionRepesitory missionRepesitory;

    private final ExpertRepository expertRepository;

    private final ImageStorage imageStorage;


    private final KeywordExtractor keywordExtractor;

    @Override
    public void deletePostule(Long id) {
        postuleoffreRepository.deleteById(id);
    }

    @Override
    public List<ListPostuledto> listeallPostule (Long id){
        return postuleoffreRepository.findAll().stream().map(
                ListPostuledto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<Listpostuleoffre> listePostuleByIdMission(Long id){
        return postuleoffreRepository.findByMissions_Id(id).stream().map(
                Listpostuleoffre::fromEntity).collect(Collectors.toList());
    }

    @Override
    public Listpostuleoffre listePostuleByIdExpert(Long idmission, Long idexpert) {

        return Listpostuleoffre.fromEntity(postuleoffreRepository.findByMissionsIdAndExpertId(idmission, idexpert));
    }

    @Override
    public ListPostuledto findByIdpostule(Long id) {


        Optional<Postuleoffre> postuleoffre = postuleoffreRepository.findById(id);
        if (postuleoffre.isPresent()) {
            ListPostuledto listPostuledto = ListPostuledto.fromEntity(postuleoffre.get());
            return listPostuledto;
        } else

            throw new RuntimeException("err");

    }


    /*
        @Override
        public Postuleoffredto addPostule(Postuleoffredto postuleoffredto, MultipartFile cvFile) {
            // Récupération des entités Expert et Mission
            Optional<Expert> expert = expertRepository.findById(postuleoffredto.getIdexpert());
            Optional<Mission> mission = missionRepesitory.findById(postuleoffredto.getIdmission());

            if (expert.isPresent() && mission.isPresent()) {
                // Extraction des mots-clés de la description de la mission
                String missionDescription = mission.get().getDescription();
                Set<String> missionKeywords = keywordExtractor.extractKeywords(missionDescription);
                Set<String> stemmedMissionKeywords = keywordExtractor.stemKeywords(missionKeywords);

                // Extraction du texte du CV avec OCR
                String cvText = extractTextFromPDFWithOCR(cvFile);
                Set<String> cvKeywords = keywordExtractor.extractKeywords(cvText);
                Set<String> stemmedCvKeywords = keywordExtractor.stemKeywords(cvKeywords);

                // Calcul du pourcentage de correspondance
                double matchingPercentage = calculateMatchingPercentage(stemmedCvKeywords, stemmedMissionKeywords);

                // Création et sauvegarde de la candidature
                Postuleoffre postuleoffre = Postuleoffre.builder()
                        .expert(expert.get())
                        .missions(mission.get())
                        .datepostule(new Date())
                        .matchingPercentage(matchingPercentage)
                        .build();

                Postuleoffre postulesaved = postuleoffreRepository.save(postuleoffre);

                return Postuleoffredto.fromEntity(postulesaved);
            } else {
                throw new RuntimeException("Expert ou Mission non trouvés");
            }
        }
    */




    @Override
    public Postuleoffredto addPostule(Postuleoffredto postuleoffredto, MultipartFile cvFile) {
        // Récupération des entités Expert et Mission
        Optional<Expert> expert = expertRepository.findById(postuleoffredto.getIdexpert());
        Optional<Mission> mission = missionRepesitory.findById(postuleoffredto.getIdmission());
         // debut methode
        Optional<Postuleoffre> existingPostule = postuleoffreRepository.findByExpertAndMissions(expert.get(), mission.get());

        if (existingPostule.isPresent()) {
            throw new IllegalStateException("L'expert a déjà postulé à cette mission.");
        }

        // Si aucune postulation n'existe, créer une nouvelle postulation

        //fin methode
        if (expert.isPresent() && mission.isPresent()) {
            // Extraction des mots-clés de la description de la mission
            String missionDescription = mission.get().getDescription();
            Set<String> missionKeywords = keywordExtractor.extractKeywords(missionDescription);
            Set<String> stemmedMissionKeywords = keywordExtractor.stemKeywords(missionKeywords);

            // Affichage de la description de la mission et des mots-clés
            System.out.println("Mission Description: " + missionDescription);
            System.out.println("Mission Keywords: " + missionKeywords);
            System.out.println("Stemmed Mission Keywords: " + stemmedMissionKeywords);

            // Extraction du texte du CV avec OCR
            String cvText = extractTextFromPDFWithOCR(cvFile);
            Set<String> cvKeywords = keywordExtractor.extractKeywords(cvText);
            Set<String> stemmedCvKeywords = keywordExtractor.stemKeywords(cvKeywords);

            // Affichage du texte extrait du CV et des mots-clés
            System.out.println("CV Text: " + cvText);
            System.out.println("CV Keywords: " + cvKeywords);
            System.out.println("Stemmed CV Keywords: " + stemmedCvKeywords);

            // Calcul du pourcentage de correspondance
            double matchingPercentage = calculateMatchingPercentage(stemmedCvKeywords, stemmedMissionKeywords);

            // Création et sauvegarde de la candidature
            Postuleoffre postuleoffre = Postuleoffre.builder()
                    .expert(expert.get())
                    .missions(mission.get())
                    .datepostule(new Date())
                    .matchingPercentage(matchingPercentage)
                    .build();

            Postuleoffre postulesaved = postuleoffreRepository.save(postuleoffre);

            return Postuleoffredto.fromEntity(postulesaved);
        } else {
            throw new RuntimeException("Expert ou Mission non trouvés");
        }
    }

    @Override
    public Long countallPostule() {
        return postuleoffreRepository.countAllPostules();
    }







    @Override
    public Expert uploadFile(Long Id, MultipartFile image) {
        ResponseEntity<Expert> userResponse = this.findbyIdExpert(Id);
        String imageName=imageStorage.store(image);
        String fileImageDownloadUrl= ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/offre/downloadFileCV/").path(imageName).toUriString();
        Expert expert = userResponse.getBody();
        if (expert!=null)
            expert.setCv(fileImageDownloadUrl);
        Expert expertsaved = expertRepository.save(expert);
        return expertsaved;
    }

    @Override
    public Postuleoffre existingPostule(Expert expert, Mission mission) {
      Postuleoffre existingPostule = postuleoffreRepository.findByExpertAndMissions(expert, mission).get();

      return  existingPostule;
    }


    public ResponseEntity<Expert> findbyIdExpert(Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(null); // ID est nul, renvoyer une réponse HTTP 400
        }

        Optional<Expert> expert = expertRepository.findById(id);
        if (expert.isPresent()) {
            return ResponseEntity.ok(expert.get()); // Si l'expert est trouvé, renvoyer OK avec l'expert
        } else {
            return ResponseEntity.notFound().build(); // Expert non trouvé, renvoyer 404
        }
    }

//    @GetMapping("/downloadFileCV/{imageName}")
//    public ResponseEntity<Resource> downloadFilecv(@PathVariable String imageName, HttpServletRequest request) {
//        return this.imageStorage.downloadUserImage(imageName, request);
//    }

    /** OCR: methode pour extracter les mots clés de cv sans bruit**/
    /** OCR: Methode pour extraire les mots clés du CV **/
    private String extractTextFromPDFWithOCR(MultipartFile pdf) {
        ITesseract tesseract = new Tesseract();
//        tesseract.setDatapath("C:\\Users\\rimzh\\OneDrive\\Bureau\\Gesxpr-master\\backend\\tessdata-main");
        tesseract.setDatapath("C:\\Users\\Skander\\OneDrive - Pharmalys Laboratories\\Desktop\\PFE\\backend\\tessdata");
        tesseract.setLanguage("fra");
        File tempFile = null;
        try {
            tempFile = File.createTempFile("temp", ".pdf");
            pdf.transferTo(tempFile);
            String extractedText = tesseract.doOCR(tempFile);
            extractedText = extractedText.replaceAll("[^\\p{L}\\p{Nd}\\s]+", " ");
            return extractedText;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'extraction du texte avec OCR", e);
        } finally {
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
        }
    }


    /** OCR: Méthode pour raciniser les mots-clés ==> Dans le contexte de traitement du langage naturel, la "racinisation" est le
     *  processus de réduction des mots à leur racine, qui est la forme de base commune
     *  à toutes les variations de ce mot. Par exemple,
     *  les mots "manger", "mangeait", "mangerais" seront tous racinisés à "mange"**/
    public List<String> stemKeywords(List<String> keywords) {
        FrenchAnalyzer analyzer = new FrenchAnalyzer();
        return keywords.stream()
                .map(keyword -> {
                    try (TokenStream tokenStream = analyzer.tokenStream(null, keyword)) {
                        tokenStream.reset();
                        StringBuilder sb = new StringBuilder();
                        while (tokenStream.incrementToken()) {
                            CharTermAttribute attr = tokenStream.getAttribute(CharTermAttribute.class);
                            sb.append(attr.toString()).append(" ");
                        }
                        tokenStream.end();
                        return sb.toString().trim();
                    } catch (IOException e) {
                        throw new RuntimeException("Erreur lors de la racinisation des mots-clés", e);
                    }
                })
                .collect(Collectors.toList());}




    /**OCR: Méthode pour calculer le pourcentage de correspondance entre cv et description **/
    private double calculateMatchingPercentage(Set<String> cvKeywords, Set<String> descriptionKeywords) {
        int totalCvKeywords = cvKeywords.size();
        long matchCount = cvKeywords.stream()
                .filter(descriptionKeywords::contains)
                .count();
        return (double) matchCount / totalCvKeywords * 100;
    }}