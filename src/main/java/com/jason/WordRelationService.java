package com.jason;

import org.springframework.stereotype.Service;

@Service
public class WordRelationService {

    private final WordRelationRepository wordRelationRepository;

    public WordRelationService(WordRelationRepository wordRelationRepository) {
        this.wordRelationRepository = wordRelationRepository;
    }

    public void saveRelationModel(WordRelationModel wordRelationModel) {
        wordRelationRepository.save(wordRelationModel);
    }

}

