package com.desafio.prevent.log.resources;

import com.desafio.prevent.log.domain.Log;
import com.desafio.prevent.log.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="/logs")
public class LogResource {
    @Autowired
    private LogService service;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Log> find(@PathVariable Integer id) {
        Log obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<Log>> findAll() {
        List<Log> obj = service.findAll();
        return ResponseEntity.ok().body(obj);
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
        return ResponseEntity.noContent().build();
    }
}
