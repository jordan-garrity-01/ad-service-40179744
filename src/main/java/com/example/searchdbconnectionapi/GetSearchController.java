package com.example.searchdbconnectionapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@ConditionalOnProperty(
        name = "search.type",
        havingValue = "get")
public class GetSearchController {

    @Autowired private SearchRepository searchRepository;
    Logger logger = LoggerFactory.getLogger(GetSearchController.class);

    @GetMapping(path = "/search",  produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<SearchResult>> getTuples(@RequestParam String content) {
        logger.info("Performing search for string = "+content);
        Page<SearchResult> searchPage = searchRepository.findAllByContentContaining(PageRequest.of(0,5), content);
        return ResponseEntity.ok(searchPage.getContent());
    }

}
