//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P04 Carrot Patch
// Course: CS 300 Spring 2021
//
// Author: Kaiwen Shi
// Email: kshi42@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: DOESN'T RECEIVE HELP FROM CLASSMATES
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
public class AddWolfButton extends Button {
    /**
     * AddWolf Button Constructor
     * @param xPosition x-axis position of button
     * @param yPosition y-axis position of button
     */
    public AddWolfButton(float xPosition, float yPosition) {
        super("Add Wolf", xPosition, yPosition);
    }

    /**
     * Override mousepressed method for every button.
     */
    @Override 
    public void mousePressed(){
        if(this.isMouseOver()){
            System.out.println("Add Wolf Button Pressed");
            processing.objects.add(new Wolf());
            
        }
    }
} 