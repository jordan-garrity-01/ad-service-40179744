package com.example.searchdbconnectionapi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface SearchRepository extends PagingAndSortingRepository<SearchResult, Long> {

    Page<SearchResult> findAllByContentContaining(Pageable pageable, String content);

}
