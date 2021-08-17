package com.desafio.prevent.log.resources;

import com.desafio.prevent.log.domain.Log;
import com.desafio.prevent.log.dto.LogDTO;
import com.desafio.prevent.log.dto.LogReportDTO;
import com.desafio.prevent.log.resources.response.ResponseMessage;
import com.desafio.prevent.log.services.FileService;
import com.desafio.prevent.log.services.LogService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigInteger;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/logs")
public class LogResource {
    @Autowired
    private LogService service;

    private final FileService fileService;
    private HttpHeaders headers;

    @Autowired
    public LogResource(FileService fileService) {
        this.fileService = fileService;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Log> find(@PathVariable Integer id) {
        Log obj = service.find(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return ResponseEntity.ok().headers(headers).body(obj);
    }

    @RequestMapping(value = "/page-interval", method = RequestMethod.GET)
    public ResponseEntity<List<LogDTO>> searchRecordsByPage(@RequestParam(value = "begin", defaultValue = "0") Integer begin,
                                                            @RequestParam(value = "end", defaultValue = "0") Integer end) {
        List<Log> list = service.findInterval(begin, end);
        List<LogDTO> listDto =
                list.stream().map(obj -> new LogDTO(obj))
                        .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Log objLog) {
        Log obj = service.insert(objLog);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Log obj, @PathVariable Integer id) {
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) throws FileUploadException {
        fileService.save(file);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
    }

    @RequestMapping(value = "/pages-info", method = RequestMethod.GET)
    public ResponseEntity<BigInteger> pageInfo() {
        BigInteger numReg = service.findPageInfo();
        return ResponseEntity.ok().body(numReg);
    }

    @RequestMapping(value = "/search-logs", method = RequestMethod.GET)
    public ResponseEntity<List<LogDTO>> search(@RequestParam(value = "search") String searchText) {
        List<Log> list = service.search(searchText);
        List<LogDTO> listDto =
                list.stream().map(obj -> new LogDTO(obj))
                        .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ResponseEntity<List<LogReportDTO>> searchByAggregation(@RequestParam(value = "option") String option) {
        List<LogReportDTO> list = service.searchByAggregation(option);
        return ResponseEntity.ok().body(list);
    }

}
