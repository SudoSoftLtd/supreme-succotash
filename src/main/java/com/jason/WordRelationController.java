package com.jason;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class WordRelationController {

    private final WordRelationService wordRelationService;

    public WordRelationController(WordRelationService wordRelationService) {
        this.wordRelationService = wordRelationService;
    }

    @GetMapping
    public List<WordRelationModel> allRecords() {
        return wordRelationService.returnAllEntries();
    }

    @GetMapping("filter/{number}")
    public List<WordRelationModel> filter(@PathVariable final Integer number) {
        return wordRelationService.filterByRelation(number);
    }

    @PostMapping
    public ResponseEntity<String> newWordRelation(@RequestBody WordRelationModel newWordRelationRequest) {

        if(newWordRelationRequest.allowedCharacters()){
            String response = "Only characters from A to Z (upper and lower case) and space allowed";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        wordRelationService.saveRelationModel(newWordRelationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created :" + newWordRelationRequest);
    }

}
