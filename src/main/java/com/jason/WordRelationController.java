package com.jason;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class WordRelationController {

    private final WordRelationService wordRelationService;

    public WordRelationController(WordRelationService wordRelationService) {
        this.wordRelationService = wordRelationService;
    }

    @PostMapping
    public ResponseEntity<WordRelationModel> newWordRelation(@RequestBody WordRelationModel newWordRelationRequest) {
        wordRelationService.saveRelationModel(newWordRelationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newWordRelationRequest);
    }

}
