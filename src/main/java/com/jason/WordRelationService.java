package com.jason;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordRelationService {

    private final WordRelationRepository wordRelationRepository;

    public WordRelationService(WordRelationRepository wordRelationRepository) {
        this.wordRelationRepository = wordRelationRepository;
    }

    public void saveRelationModel(WordRelationModel wordRelationModel) {
        wordRelationModel.lowerCaseAndWhiteSpaceValidation();
        wordRelationRepository.save(wordRelationModel);
    }

    public List<WordRelationModel> returnAllEntries() {
        return wordRelationRepository.findAll();
    }

    public List<WordRelationModel> filterByRelation(Integer filter){
        return wordRelationRepository.findRelation(filter);
    }

}

