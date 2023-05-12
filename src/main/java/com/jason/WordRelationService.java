package com.jason;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordRelationService {

    private final WordRelationRepository wordRelationRepository;

    public WordRelationService(WordRelationRepository wordRelationRepository) {
        this.wordRelationRepository = wordRelationRepository;
    }

    public ResponseEntity<String> saveRelationModel(WordRelationModel wordRelationModel) {

        if (wordRelationModel.containsNoAllowedCharacters()) {
            return respondNoAllowedCharacters();
        }

        if (checkExistingRelationExists(wordRelationModel)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Relation between words already established.");
        }

        if (checkInverseRelation(wordRelationModel)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Inverted Relation between words already exists.");
        }

        return respondOkWithValidatedSave(wordRelationModel);
    }

    private ResponseEntity<String> respondOkWithValidatedSave(WordRelationModel wordRelationModel) {
        wordRelationModel.lowerCaseAndWhiteSpaceValidation();
        wordRelationRepository.save(wordRelationModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created :" + wordRelationModel);
    }

    private ResponseEntity<String> respondNoAllowedCharacters() {
        String response = "Only characters from A to Z (upper and lower case) and space allowed";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    public List<WordRelationModel> returnAllEntries() {
        return wordRelationRepository.findAll();
    }

    public List<WordRelationModel> filterByRelation(Integer filter) {
        return wordRelationRepository.filterRelation(filter);
    }

    public boolean checkExistingRelationExists(WordRelationModel wordRelationModel) {
        String wordOne = wordRelationModel.getWordOne();
        String wordTwo = wordRelationModel.getWordTwo();

        try {
            WordRelationModel dbModel = wordRelationRepository.findRelation(wordOne, wordTwo).get(0);
            if (dbModel.getRelation() != wordRelationModel.getRelation()) {
                return true;
            }} catch (Exception ignored) {}

        return false;
    }

    private boolean checkInverseRelation(WordRelationModel wordRelationModel) {

        String wordOne = wordRelationModel.getWordOne();
        String wordTwo = wordRelationModel.getWordTwo();

        try {
            if(wordRelationRepository.findRelation(wordTwo,wordOne).size()>0){
                return true;
            }} catch (Exception ignored) {}

        return false;
    }


}

