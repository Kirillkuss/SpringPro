package com.example.test.controllers;

import com.example.test.entity.Person;
import com.example.test.response.BaseResponse;
import com.example.test.rest.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.test.services.PersonService;

@RestController
public class PersonController implements IPerson {

    @Autowired
    private PersonService personService;

    public ResponseEntity getAllPerson() {
        return ResponseEntity.status( HttpStatus.OK )
                             .body( personService.findAllTwo());
    }

    public ResponseEntity findByIdPerson( Long id ) throws Exception{
        return ResponseEntity.status( HttpStatus.OK ).body( personService.getPersonById( id )); 
    }

    public ResponseEntity savePerson( Person person ) throws Exception{
        return ResponseEntity.status( HttpStatus.CREATED ).body( personService.savePerson( person ));
    }

    public ResponseEntity updatePerson( Person person ) throws Exception{
        return ResponseEntity.status( HttpStatus.OK).body( personService.updatePerson( person)); 
    }

    public ResponseEntity deletePerson( Long id ) throws Exception{
        personService.deletePerson( id );
        return ResponseEntity.status( HttpStatus.NO_CONTENT )
                             .body( BaseResponse.success() ) ;
    }

}
