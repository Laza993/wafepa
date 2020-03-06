package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Record;
import jwd.wafepa.service.RecordService;
import jwd.wafepa.support.RecordDTOtoRecord;
import jwd.wafepa.support.RecordToRecordDTO;
import jwd.wafepa.web.DTO.RecordDTO;

@RestController
@RequestMapping(value="/api/records")
public class ApiRecordController {
	
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private RecordToRecordDTO convertRecordToDTO;
	@Autowired
	private RecordDTOtoRecord convertDTOtoRecord;
	
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<RecordDTO> findOne(@PathVariable Long id){
		if(recordService.findOne(id).getActivity() == null || recordService.findOne(id).getUser() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}			
		RecordDTO retVal = convertRecordToDTO.convert(recordService.findOne(id));
		return new ResponseEntity<>(retVal, HttpStatus.OK);
		}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RecordDTO>> findAll(){
		List<RecordDTO> recordsDto = convertRecordToDTO.convert(recordService.findAll());
		return new ResponseEntity<>(recordsDto, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<RecordDTO> addRecord(@RequestBody RecordDTO dto){
		Record record = recordService.save(convertDTOtoRecord.convert(dto));		
		return new ResponseEntity<>(convertRecordToDTO.convert(record), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity<RecordDTO> delleteRecord(@PathVariable Long id){
		Record deleted = recordService.delete(id);
		return new ResponseEntity<>(convertRecordToDTO.convert(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public ResponseEntity<RecordDTO> editRecord(@PathVariable Long id,
												@RequestBody RecordDTO dto){
		if(!id.equals(dto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Record record = recordService.save(convertDTOtoRecord.convert(dto));
		return new ResponseEntity<>(convertRecordToDTO.convert(record), HttpStatus.OK);
	}
}
