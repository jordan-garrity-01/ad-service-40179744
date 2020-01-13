package com.example.searchdbconnectionapi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

@org.springframework.stereotype.Repository
public interface AdRepository extends PagingAndSortingRepository<AdResult, Long> {

    Page<AdResult> findAllByContentContaining(Pageable pageable, String content);

}
