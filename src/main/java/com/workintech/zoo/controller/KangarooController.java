package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Gender;
import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
        kangaroos.put(1, new Kangaroo(1, "Lena", 35, Gender.FEMALE, 1.6, false));
    }

    @GetMapping("/hi")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello", HttpStatus.CREATED);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Kangaroo> findAll() {
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo find(@PathVariable int id) {
        ZooValidation.isIdValid(id);
        ZooValidation.checkKangarooExistence(kangaroos, id, false);
        return kangaroos.get(id);
    }

    @PostMapping("/")
    public Kangaroo save(@RequestBody Kangaroo kangaroo) {
        //ZooValidation.isIdValid(kangaroo.getId());
        ZooValidation.checkKangarooExistence(kangaroos, kangaroo.getId(), true);
        ZooValidation.checkKangarooWeight(kangaroo.getWeight());
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable int id, @RequestBody Kangaroo kangaroo) {
        ZooValidation.isIdValid(id);
        ZooValidation.checkKangarooWeight(kangaroo.getWeight());
        kangaroo.setId(id);
        if(kangaroos.containsKey(id)){
            kangaroos.put(id, kangaroo);
            return kangaroo;
        } else {
            return save(kangaroo);
        }
    }

    @DeleteMapping("/{id}")
    public Kangaroo remove(@PathVariable int id){
        ZooValidation.isIdValid(id);
        ZooValidation.checkKangarooExistence(kangaroos, id, false);
        return kangaroos.remove(id);
    }

}
