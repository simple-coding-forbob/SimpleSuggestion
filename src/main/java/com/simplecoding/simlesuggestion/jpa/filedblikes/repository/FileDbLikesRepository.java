package com.simplecoding.simlesuggestion.jpa.filedblikes.repository;

import com.simplecoding.simlesuggestion.jpa.filedblikes.entity.FileDbLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDbLikesRepository extends JpaRepository<FileDbLikes, Long> {

    @Query("select count(fl) from FileDbLikes fl\n" +
            "where fl.member.email=:email\n" +
            "and   fl.fileDb.uuid=:uuid")
    long countEmailAndUuid(@Param("email") String email, @Param("uuid") String uuid);
}
