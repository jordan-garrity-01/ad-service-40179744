package com.example.searchdbconnectionapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ConditionalOnProperty(
        name = "search.type",
        havingValue = "post")
public class AdController {

    @Autowired
    AdRepository searchRepository;
    Logger logger = LoggerFactory.getLogger(AdController.class);

    @PostMapping(path = "/search", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AdResult> addSearchResult(@RequestBody @Validated AdResult searchResult){
        logger.info("Storing search results for url = "+searchResult.getUrl());
        AdResult saved = searchRepository.save(searchResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping(path = "/search",  produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<AdResult>> getTuples(@RequestParam String content) {
        logger.info("Performing search for string = "+content);
        Page<AdResult> searchPage = searchRepository.findAllByContentContaining(PageRequest.of(0,5), content);
        return ResponseEntity.ok(searchPage.getContent());
    }


}
