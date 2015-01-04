/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import domain.Person;
import facade.PersonFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Erdem Akyıldız
 */
@Stateless
public class PersonService {
    
    @EJB
    private PersonFacade personFacade;

    public PersonService() {
    }
    
    public void save(Person person){
        personFacade.create(person);
    }
    
    public List<Person> getAllPerson(){
        return personFacade.findAll();
    }
    
    public void deletePerson(Person person){
        personFacade.remove(person);
    }
    
    public void updatePerson(Person person){
        personFacade.edit(person);
    }
            
}
