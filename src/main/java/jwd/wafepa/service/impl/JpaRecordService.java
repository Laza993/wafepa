package jwd.wafepa.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Intensity;
import jwd.wafepa.model.Record;
import jwd.wafepa.repository.RecordRepository;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.service.RecordService;
import jwd.wafepa.service.UserService;


@Transactional
@Service
public class JpaRecordService implements RecordService {
	
	@Autowired
	private RecordRepository recordRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ActivityService activityService;
	
	@Override
	public Record save(Record record) {
		Record saved = recordRepository.save(record);
		return saved;
	}

	@Override
	public Record delete(Long id) {
		Record deleted = recordRepository.findOne(id);
		recordRepository.delete(id);
		return deleted;
	}

	@Override
	public Record findOne(Long id) {
		return recordRepository.findOne(id);
	}

	@Override
	public List<Record> findAll() {
		return recordRepository.findAll();
	}
	
	
//	@PostConstruct
	public void init() {

		Date dat1 = new Date();
		save(new Record(null, dat1, 100, Intensity.extreme, userService.getOne(2L), activityService.findOne(2L)));
		
	}

	@Override
	public List<Record> findAllByUser(Long id) {
		return recordRepository.findAllByUserId(id);
	}
}
