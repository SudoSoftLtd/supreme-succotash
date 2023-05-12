package com.jason;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WordRelationRepository extends JpaRepository<WordRelationModel, Integer> {

        @Query(value =
                "SELECT * FROM word_relation_table WHERE relation = ?1", nativeQuery = true)
        List<WordRelationModel> filterRelation(Integer relationType);

        @Query(value =
                "SELECT * FROM word_relation_table WHERE word_one = ?1 AND word_two = ?2", nativeQuery = true)
        List<WordRelationModel> findRelation(String wordOne, String wordTwo);

}
