package com.simplecoding.simlesuggestion.es.filedbsuggested.repository;

import com.simplecoding.simlesuggestion.es.filedbsuggested.entity.FileDbSuggested;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDbSuggestedRepository extends ElasticsearchRepository<FileDbSuggested, String> {
}
