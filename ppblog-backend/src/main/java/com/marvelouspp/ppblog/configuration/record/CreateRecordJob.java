package com.marvelouspp.ppblog.configuration.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.marvelouspp.ppblog.service.RecordService;

@Component
public class CreateRecordJob {

    @Autowired
    RecordService recordService;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void updateHeat() {
        recordService.createRecord();
    }
}
