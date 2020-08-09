package org.nazriaz.sb.service;

import org.nazriaz.sb.entity.History;
import org.nazriaz.sb.repository.HistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class HistoryService {
    @Autowired
    HistoryRepo historyRepo;

    public List<History> findAll() {
        return StreamSupport.stream(historyRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }
    public String save(History history){
        if (history==null){
            return "Null History";
        }
        historyRepo.save(history);
        return "OK";
    }
}
