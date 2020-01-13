package com.example.searchdbconnectionapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@ConditionalOnProperty(
        name = "search.type",
        havingValue = "post")
public class AddSearchController {

    @Autowired SearchRepository searchRepository;
    Logger logger = LoggerFactory.getLogger(AddSearchController.class);

    @PostMapping(path = "/search", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SearchResult> addSearchResult(@RequestBody @Validated SearchResult searchResult){
        logger.info("Storing search results for url = "+searchResult.getUrl());
        SearchResult saved = searchRepository.save(searchResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}
