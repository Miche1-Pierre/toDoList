package com.example.toDoList.controller;

import com.example.toDoList.dao.PrioriteDao;
import com.example.toDoList.model.Priorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class PrioriteController {

    @Autowired
    private PrioriteDao prioriteDao;

    @GetMapping("/priorite")
    public List<Priorite> getAll() {

        return prioriteDao.findAll();
    }

    @GetMapping("/priorite/{id}")
    public ResponseEntity<Priorite> get(@PathVariable Integer id) {

        Optional<Priorite> optionalPriorite = prioriteDao.findById(id);

        if(optionalPriorite.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalPriorite.get(),HttpStatus.OK);
    }

    @PostMapping("/priorite")
    public ResponseEntity<Priorite> create(@RequestBody Priorite priorite) {

        priorite.setId(null);
        prioriteDao.save(priorite);

        return new ResponseEntity<>(priorite, HttpStatus.CREATED);
    }

    @PutMapping("/priorite/{id}")
    public ResponseEntity<Priorite> update(@RequestBody Priorite priorite, @PathVariable Integer id) {

        priorite.setId(id);

        Optional<Priorite> optionalPriorite = prioriteDao.findById(id);

        if(optionalPriorite.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        prioriteDao.save(priorite);

        return new ResponseEntity<>(priorite, HttpStatus.OK);
    }

    @DeleteMapping("/priorite/{id}")
    public ResponseEntity<Priorite> delete(@PathVariable Integer id) {

        Optional<Priorite> optionalPriorite = prioriteDao.findById(id);

        if(optionalPriorite.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        prioriteDao.deleteById(id);

        return new ResponseEntity<>(optionalPriorite.get(), HttpStatus.OK);
    }
}
