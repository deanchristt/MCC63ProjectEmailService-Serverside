package co.id.emailservice.serverside.controller;

import co.id.emailservice.serverside.helper.ExcelHelper;
import co.id.emailservice.serverside.message.ResponseMessage;
import co.id.emailservice.serverside.model.Participant;
import co.id.emailservice.serverside.model.dto.ParticipantData;
import co.id.emailservice.serverside.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/participant")
public class ParticipantController {

    private ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public ResponseEntity<List<Participant>> getAll() {
        return new ResponseEntity(participantService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participant> getById(@PathVariable Long id) {
        return new ResponseEntity(participantService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Participant> create(@RequestBody ParticipantData participantData) {
        return new ResponseEntity(participantService.create(participantData), HttpStatus.CREATED);
    }

    @PostMapping("/upload/{emailListNameId}")
    public void uploadFile(@RequestBody List<ParticipantData> participants,@PathVariable("emailListNameId") Long emailListNameId) {
//        String message = "";
//        if (ExcelHelper.hasExcelFormat(file)) {
//            try {
//                participantService.addParticipantsFromExcel(file, emailListNameId);
//                message = "Uploaded the file successfully: " + file.getOriginalFilename();
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//            } catch (Exception e) {
//                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//            }
//        }
//        message = "Please upload an excel file!";
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
        participantService.addParticipantsFromExcel(participants, emailListNameId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participant> update(@PathVariable Long id, @RequestBody Participant participant) {
        return new ResponseEntity(participantService.update(id, participant), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Participant> delete(@PathVariable Long id) {
        return new ResponseEntity(participantService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/getEmailParticipantByEmailListName/{id}")
    public ResponseEntity<List<Participant>> getFilterEmailParticipantByEmailListNameId(@PathVariable Long id){
        return new ResponseEntity(participantService.getFilterEmailParticipantByEmailListNameId(id), HttpStatus.OK);
    }

    @GetMapping("/getFilterParticipantByEmailListNameId/{id}")
    public ResponseEntity<List<Participant>> getFilterParticipantByEmailListNameId(@PathVariable Long id){
        return new ResponseEntity(participantService.getFilterParticipantByEmailListNameId(id), HttpStatus.OK);
    }

}