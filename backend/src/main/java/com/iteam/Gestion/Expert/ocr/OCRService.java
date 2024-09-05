package com.iteam.Gestion.Expert.ocr;
import java.io.InputStream;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class OCRService {

	   public String extractTextFromPdf(File file) {
	        try (PDDocument document = PDDocument.load(file)) {
	            PDFTextStripper pdfStripper = new PDFTextStripper();
	            return pdfStripper.getText(document);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "";
	        }
	    }

	    public String extractTextFromPdf(InputStream inputStream) {
	        try {
	            PDDocument document = PDDocument.load(inputStream);
	            PDFTextStripper pdfStripper = new PDFTextStripper();
	            return pdfStripper.getText(document);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "";
	        }
	    }

	    public String extractTextFromImage(File file) {
	        ITesseract tesseract = new Tesseract();
	        try {
	            return tesseract.doOCR(file);
	        } catch (TesseractException e) {
	            e.printStackTrace();
	            return "";
	        }
	    }

}
