package com.kim.Tutorials.mycontrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kim.Tutorials.model.Tutorial;
import com.kim.Tutorials.myServices.TutorialServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//@RestController
@Controller

@RequestMapping("/api")
public class TutorialController {

   @Autowired
      private TutorialServices tutorialServices;  
   
   
   @GetMapping("/tutorials")
   public String getAllTutorials(Model model)         {
	   List<Tutorial> tutorials = tutorialServices.getAllTutorials();
	   model.addAttribute("tutorials", tutorials);
       return "tutorialsList";
      
   }

   @GetMapping("/tutorials/{id}")
   public Optional<Tutorial> getTutorialById(@PathVariable("id") long id) {
    /* the TutorialRepository provides a method findById(). This methods takes the id of the
    Tutorial to find. This method used to be findOne(). But since Spring data jpa 2.0 it's changed to findById().
     */
       return  tutorialServices.getTutorialById(id);
   }
  /*  To add new Tutorials is really easy. You do this by using the TutorialRepository save() method.
   */
   @PostMapping("/tutorial")
   public void createTutorial(@RequestBody Tutorial tutorial) {
       tutorialServices.addTutorial(tutorial);
   }
   
   @PostMapping("/tutorials")
   public void createTutorials(@RequestBody List<Tutorial> tutorials) {
       tutorialServices.addTutorials(tutorials);
   }


// To update a tutorial record, we used the same save() and findById()
   @PutMapping("/tutorials/{id}")
   public void updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
       Optional<Tutorial> tutorialData = tutorialServices.getTutorialById(id);

       if (tutorialData.isPresent()) {
           Tutorial _tutorial = tutorialData.get();
           _tutorial.setTitle(tutorial.getTitle());
           _tutorial.setDescription(tutorial.getDescription());
           _tutorial.setPublished(tutorial.isPublished());
           tutorialServices.addTutorial(_tutorial);
       }
   }

   @DeleteMapping("/tutorials/{id}")
   public void deleteTutorial(@PathVariable("id") long id) {
       tutorialServices.deleteTutorial(id);
   }
  /* To delete a tutorials record, you simply use the deleteById() method provided by the tutorialRepository.
   Then you pass in the id of the record you want to delete.
   */
   @DeleteMapping("/tutorials")
   public void deleteAllTutorials() {

       tutorialServices.deleteAllTutorials();
   }

   @GetMapping("/tutorials/published")
   public ResponseEntity<List<Tutorial>> findByPublished() {
       return tutorialServices.findByPublished();   }  }


