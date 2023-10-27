package com.learning.springsecurity.controller;

import com.learning.springsecurity.model.Notice;
import com.learning.springsecurity.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/notices/")
public class NoticesController {


    @Autowired
    private NoticeRepository noticeRepository;

    @GetMapping(value = "myNotices")
    public ResponseEntity<List<Notice>> getNotices() {
        List<Notice> notices = noticeRepository.findAllActiveNotices();
        if (notices != null ) {
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)) //keep response cached for 60 seconds. no update call to endpoint will be made
                    .body(notices);
        }else {
            return null;
        }
    }

}
