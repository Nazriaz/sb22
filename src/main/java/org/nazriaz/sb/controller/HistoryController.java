package org.nazriaz.sb.controller;

import org.nazriaz.sb.entity.History;
import org.nazriaz.sb.service.HistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {
    private final
    HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping
    public List<History> findAll() {
        return historyService.findAll();
    }

    @PostMapping
    public void add(@RequestBody History history) {
        historyService.save(history);
    }
}