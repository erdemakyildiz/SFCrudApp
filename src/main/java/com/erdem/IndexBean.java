/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erdem;

import domain.Person;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import service.PersonService;

/**
 *
 * @author Erdem Akyıldız
 */
@ManagedBean(name = "IndexBean")
@SessionScoped
public class IndexBean {

    @EJB
    PersonService personService;

    private Person person = new Person();
    private String commandButtonValue = "Ekle";

    public String getCommandButtonValue() {
        return commandButtonValue;
    }

    public void setCommandButtonValue(String commandButtonValue) {
        this.commandButtonValue = commandButtonValue;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void savePerson() throws IOException {
        FacesMessage message = new FacesMessage();
        if (person.getAd().equals("") || person.getSoyad().equals("")) {
            message.setSummary("Hata");
            message.setDetail("Alanları Boş Bırakmayın");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return;
        }
        if (getCommandButtonValue().equals("Ekle")){
            personService.save(person);
            person = new Person();
        }else{
            personService.updatePerson(person);
            setCommandButtonValue("Ekle");
        }
        
        person = new Person();
    }

    public List<Person> getPersons() {
        return personService.getAllPerson();
    }

    public void deletePerson(Person person) {
        personService.deletePerson(person);
    }
    
    public void updatePerson(Person person) {
        this.person = person;
        setCommandButtonValue("Güncelle");
    }

}
