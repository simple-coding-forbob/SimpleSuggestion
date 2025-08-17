package com.simplecoding.simlesuggestion.es.filedbsuggested.repository;

import com.simplecoding.simlesuggestion.es.filedbsuggested.entity.FileDbSuggested;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDbSuggestedRepository extends ElasticsearchRepository<FileDbSuggested, String> {
}
