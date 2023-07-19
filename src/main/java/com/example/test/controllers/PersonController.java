package com.example.test.controllers;

import com.example.test.entity.Person;
import com.example.test.response.BaseResponse;
import com.example.test.rest.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.test.services.PersonService;

@RestController
public class PersonController implements IPerson {

    @Autowired
    private PersonService personService;

    public BaseResponse getAllPerson() {
        return new BaseResponse( 200, "success", personService.findAllTwo());
    }

    public BaseResponse findByIdPerson( Long id ) throws Exception{
        return new BaseResponse( 200, "success", personService.getPersonById( id ));
    }

    public BaseResponse savePerson( Person person ) throws Exception{
        return new BaseResponse( 200, "success", personService.savePerson( person ));
    }

    public BaseResponse updatePerson( Person person ) throws Exception{
        return new BaseResponse( 200, "success", personService.updatePerson( person));
    }

    public BaseResponse deletePerson( Long id ) throws Exception{
        personService.deletePerson( id );
        return BaseResponse.success();
    }

}
