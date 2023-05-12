package com.jason;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name="word_relation_table")
public class WordRelationModel {

    @Id
    @SequenceGenerator(
            name = "word_relation_id_sequence",
            sequenceName = "word_relation_id_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "word_relation_id_sequence"
    )

    private Integer id;
    private String wordOne;
    private String wordTwo;
    private Relation relation;

    public void lowerCaseAndWhiteSpaceValidation() {
        toLowerCase();
        remoteWhitespace();
    }

    private void toLowerCase(){
        wordOne = wordOne.toLowerCase();
        wordTwo = wordTwo.toLowerCase();
    }

    private void remoteWhitespace(){
        wordOne = wordOne.replaceAll("\\s+","");
        wordTwo = wordTwo.replaceAll("\\s+","");
    }

    public boolean allowedCharacters() {
        return checkAllowedCharacters(wordOne) && !checkAllowedCharacters(wordTwo);
    }

    private boolean checkAllowedCharacters(String word){
        Pattern pattern = Pattern.compile("[^((0-9)|(a-z)|(A-Z)|\\s)]");
        Matcher matcher = pattern.matcher(word);
        return matcher.find();
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", wordOne='" + wordOne + '\'' +
                ", wordTwo='" + wordTwo + '\'' +
                ", relation=" + relation +
                '}';
    }

    enum Relation {
        SYNONYM,
        ANTONYM,
        RELATED

    }
    public WordRelationModel() {
    }

    public WordRelationModel(Integer id, String wordOne, String wordTwo, Relation relation) {
        this.id = id;
        this.wordOne = wordOne;
        this.wordTwo = wordTwo;
        this.relation = relation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWordOne() {
        return wordOne;
    }

    public void setWordOne(String wordOne) {
        this.wordOne = wordOne;
    }

    public String getWordTwo() {
        return wordTwo;
    }

    public void setWordTwo(String wordTwo) {
        this.wordTwo = wordTwo;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordRelationModel that = (WordRelationModel) o;
        return Objects.equals(id, that.id) && Objects.equals(wordOne, that.wordOne) && Objects.equals(wordTwo, that.wordTwo) && relation == that.relation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wordOne, wordTwo, relation);
    }
}
