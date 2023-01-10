package com.marvelouspp.ppblog.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.marvelouspp.ppblog.domain.ResponseObject;
import com.marvelouspp.ppblog.domain.entity.Record;

public interface RecordService extends IService<Record>{
    
    public ResponseObject<?> getRecords();

    public void createRecord();
}
