
package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Record;
import jwd.wafepa.web.DTO.RecordDTO;

@Component
public class RecordToRecordDTO implements Converter<Record, RecordDTO>  {

	@Override
	public RecordDTO convert(Record source) {
		RecordDTO dto = new RecordDTO();
		dto.setId(source.getId());
		dto.setActivityId(source.getActivity().getId());
		dto.setActivityName(source.getActivity().getName());
		dto.setUserId(source.getUser().getId());
		dto.setUserName(source.getUser().getFirstName());
		dto.setDuration(source.getDuration());
		dto.setIntensity(source.getIntensity());
		dto.setTime(source.getTime());
		return dto;
	}
	
	public List<RecordDTO> convert(List<Record> records){
		List<RecordDTO> dtos = new ArrayList<RecordDTO>();
		for(Record record : records) {
			if( record.getActivity() == null || record.getUser() == null || record.getIntensity() == null || record.getTime() == null ) {
				continue;
			}else {
				RecordDTO dto =  convert(record);
				dtos.add(dto);
			}
		}
		return dtos;
	}
	
}
