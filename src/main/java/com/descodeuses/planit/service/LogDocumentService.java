package com.descodeuses.planit.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.descodeuses.planit.entity.LogDocument;
import com.descodeuses.planit.repository.LogDocumentRepository;

import jakarta.servlet.http.HttpServletRequest;
@Service
public class LogDocumentService {

    @Autowired
    private LogDocumentRepository repo;

public void addLog(String text, HttpServletRequest request, String username) {
    LogDocument doc = new LogDocument();
    doc.setText(text);
    LocalDateTime now = LocalDateTime.now();
    doc.setTimestamp(now); 


    // ✅ Convertir à l’heure de Paris
    ZonedDateTime parisTime = now.atZone(ZoneId.systemDefault()); // ou ZoneId.of("Europe/Paris")
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String formatted = parisTime.format(formatter);
    
    Map<String, Object> extras = new HashMap<>();
    extras.put("username", username);
    doc.setExtras(extras);

    repo.save(doc);
    System.out.println("Horodatage local : " + formatted + " ➤ " + text);
}

}