package org.nazriaz.sb.service;

import org.nazriaz.sb.entity.History;
import org.nazriaz.sb.repository.HistoryRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {
    private final
    HistoryRepo historyRepo;

    public HistoryService(HistoryRepo historyRepo) {
        this.historyRepo = historyRepo;
    }

    public List<History> findAll() {
        return new ArrayList<>(historyRepo.findAll());
    }

    public void save(History history) {
        historyRepo.save(history);
    }
}