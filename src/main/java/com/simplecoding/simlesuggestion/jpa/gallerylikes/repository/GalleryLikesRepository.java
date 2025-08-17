package com.simplecoding.simlesuggestion.jpa.gallerylikes.repository;

import com.simplecoding.simlesuggestion.jpa.gallerylikes.entity.GalleryLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryLikesRepository extends JpaRepository<GalleryLikes, Long> {

    @Query("select count(gl) from GalleryLikes gl\n" +
            "where gl.member.email=:email\n" +
            "and   gl.gallery.uuid=:uuid")
    long countEmailAndUuid(@Param("email") String email, @Param("uuid") String uuid);
}
