package org.nazriaz.sb.repository;

import org.nazriaz.sb.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepo extends JpaRepository<History, Integer> {
}