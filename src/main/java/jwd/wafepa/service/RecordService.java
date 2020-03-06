package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Record;

public interface RecordService {
	public Record save(Record record);
	public Record delete(Long id);
	public Record findOne(Long id);
	public List<Record> findAll();
	public List<Record> findAllByUser(Long id);
	
}
