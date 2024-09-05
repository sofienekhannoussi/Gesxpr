package com.iteam.Gestion.Expert.ocr;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PdfOcrKeywordExtractionService {

    public List<String> extractAllKeywords(String text) {
        List<String> keywords = new ArrayList<>();

        // Utiliser une expression régulière pour extraire tous les mots du texte
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(text);

        // Parcourir les correspondances et les ajouter à la liste des mots-clés
        while (matcher.find()) {
            String word = matcher.group();
            // Ajouter le mot à la liste des mots-clés si ce n'est pas déjà présent
            if (!keywords.contains(word)) {
                keywords.add(word);
            }
        }

        return keywords;
    }
}
