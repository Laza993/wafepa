package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Record;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.service.RecordService;
import jwd.wafepa.service.UserService;
import jwd.wafepa.web.DTO.RecordDTO;

@Component
public class RecordDTOtoRecord implements Converter<RecordDTO, Record> {
	
	@Autowired
	private RecordService recordService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private UserService userService;
	
	@Override
	public Record convert(RecordDTO source) {
		Record record = null;
		if(source.getId() == null) {
			 record = new Record();
		}else {
			 record = recordService.findOne(source.getId());
		}
		record.setActivity(activityService.findOne(source.getActivityId()));
		record.setUser(userService.getOne(source.getUserId()));
		record.setDuration(source.getDuration());
		record.setIntensity(source.getIntensity());
		record.setTime(source.getTime());
		return record;
	}
	
	public List<Record> convert(List<RecordDTO> dtos){
		List<Record> records = new ArrayList<Record>();
		for(RecordDTO dto : dtos) {
			Record record = convert(dto);
			records.add(record);
		}
		return records;
	}

}
