package com.iteam.Gestion.Expert.services;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;


@Service
//La classe KeywordExtractor extrait et réduit morphologiquement les mots-clés de textes en français
//en utilisant l'analyseur linguistique FrenchAnalyzer d'Apache Lucene.
public class KeywordExtractor {
    // Méthode pour extraire des mots-clés à partir d'un texte donné

    public Set<String> extractKeywords(String text) {
        Set<String> keywords = new HashSet<>();
        // Utilisation d'un try-with-resources pour gérer les ressources de manière automatique
        try (FrenchAnalyzer analyzer = new FrenchAnalyzer();
             TokenStream tokenStream = analyzer.tokenStream(null, new StringReader(text))) {

            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                CharTermAttribute attr = tokenStream.getAttribute(CharTermAttribute.class);
                keywords.add(attr.toString());
            }
            tokenStream.end();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keywords;
    }
    // Méthode pour appliquer le stemming sur un ensemble de mots-clés: Prend un ensemble de mots-clés et applique 
    //le stemming (réduction morphologique) sur chaque mot.

    public Set<String> stemKeywords(Set<String> keywords) {
        FrenchAnalyzer analyzer = new FrenchAnalyzer();
        Set<String> stemmedKeywords = new HashSet<>();
        for (String keyword : keywords) {
            try (TokenStream tokenStream = analyzer.tokenStream(null, new StringReader(keyword))) {
                tokenStream.reset();
                StringBuilder sb = new StringBuilder();
                while (tokenStream.incrementToken()) {
                    CharTermAttribute attr = tokenStream.getAttribute(CharTermAttribute.class);
                    sb.append(attr.toString()).append(" ");
                }
                tokenStream.end();
                stemmedKeywords.add(sb.toString().trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stemmedKeywords;
    }
}
