package org.nazriaz.sb.controller;

import org.nazriaz.sb.entity.History;
import org.nazriaz.sb.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    HistoryService historyService;

    @GetMapping
    public List<History> findAll() {
        return historyService.findAll();
    }
    @PostMapping
    public String add(@RequestBody History history){
        return historyService.save(history);
    }
}
