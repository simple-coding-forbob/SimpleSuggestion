package com.simplecoding.simlesuggestion.es.gallerysuggested.repository;

import com.simplecoding.simlesuggestion.es.gallerysuggested.entity.GallerySuggested;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GallerySuggestedRepository extends ElasticsearchRepository<GallerySuggested, String> {

}
