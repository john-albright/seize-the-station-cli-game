/**
 * 
 * Java Data Structures Lab 03
 * Description: This program simulates a game in which a player has to
 * try to take back a train station captured by enemies. The station
 * is 300 ft away and the player has 20 turns to get to the station. The
 * player can play the game at three varying difficulties. On the way,
 * the player may meet a teammate that can help or he or she may lose 
 * a variable amount of HP depending on the obstacle encountered. 
 * Programmer: John Albright
 * Date Created: February 12, 2021
 * 
 */

import java.util.Random;
import java.util.Scanner;

public class SeizeTheStation 
{
    public static void main(String args[])
    {
        // declare a Scanner class object and Random class object 
        Scanner scan = new Scanner(System.in);
        Random randomGen = new Random();
        // variable to decrease distance to target 
        int randAddDist = 0;
        // variable to track remaining distance to target
        int randDistToMove = 0;
        // variable to use to supplement player / game interaction   
        int randInteract = 0;
        // variable to define player obstacle		  
        char interact = '\0';
        // variable to allow player to proceed to target
        char again = '\0';
        // variable to set initial distance to goal
        int goal = 300; 
        // variable to set initial player health
        int health = 100;
        // variable to approximate hp
        int approxHP = 0; 
        // variable to determine the obstacle
        int randObstacle = 0;
        // variable to calculate distance remaining
        int distRemaining = 0;
        // optional variable for the total HP printer
        // int healthBeforeObstacle = 0;
        // variable to keep track of damage done when obstacle is encountered
        int obstacleDamage = 0;
        // variables for difficulty level of game
        char difficulty = '\0';
        int explosiveRating = 101;
        boolean explosionSwitch = false;
        int minMovingDistance = 0;
        char extraGuy = '\0';
        // selective control for difficulty level of game
        do 
        {
            System.out.println("\n-----------------------------------");
            System.out.println("Choose your difficulty level:");
            System.out.println("E = EASY  M = MEDIUM   H = HARD");
            difficulty = scan.next().charAt(0);
        } while (difficulty != 'E' && difficulty != 'M' && difficulty != 'H');

        // changes parameters to make the game harder
        if (difficulty == 'E')
        {
            System.out.println("This should be a breeze!");
            extraGuy = 'j';
            minMovingDistance = 26;
        }
        else if (difficulty == 'M')
        {
            System.out.println("This shouldn't be too bad...");
            extraGuy = 'g';
            minMovingDistance = 21;
            
        }
        else if (difficulty == 'H')
        {
            System.out.println("You have to get through a mine field!");
            extraGuy = 'd';
            minMovingDistance = 16;
            explosiveRating = 85;
            explosionSwitch = true;
        }

        // define a loop for at most twenty actions
        for (int count = 1; count <= 20; count++)
        {            
            // signal the intention of the player
            do {
                System.out.println("\n-----------------------------------");
                System.out.println("\nAre you ready to proceed? ( Y or N )");
                again = scan.next().charAt(0); 
            } while (again != 'Y' && again != 'N');
            
            // exit the program if the user selects 'N'
            if (again == 'N') break;

            // define an obstacle
            interact = (char)(randomGen.nextInt(26) + 'a');
            if (interact >= 'a' && interact <= 'm')
            {
                // random number sets distance to move toward the objective
                randDistToMove = minMovingDistance + randomGen.nextInt(20);
                randAddDist += (randDistToMove + randInteract);
                System.out.println("Move forward " + (randDistToMove + randInteract) + " ft");
            }
            else
            {
                // optional assignment for total HP printer
                // healthBeforeObstacle = health;
                randObstacle = randomGen.nextInt(101);
                if (randObstacle >= explosiveRating && explosionSwitch)
                {
                    System.out.println("A bomb exploded resulting in massive damage.");
                    obstacleDamage = 40;
                }
                else if (randObstacle <= (explosiveRating - 1) && randObstacle >= 80)
                {
                    System.out.println("An enemy threw a projectile at you!");
                    obstacleDamage = 20;
                }
                else if (randObstacle <= 79 && randObstacle >= 60)
                {
                    System.out.println("A stray dog bit your leg!");
                    obstacleDamage = 15;
                }
                else if (randObstacle <= 59 && randObstacle >= 40)
                {
                    System.out.println("You slipped into a puddle of water!");
                    obstacleDamage = 10;
                }
                else 
                {
                    System.out.println("You tripped on a some rocks!");
                    obstacleDamage = 5;
                }
                // update the remaining health after the attack
                health -= obstacleDamage;
                // set the health to 0 if it's negative
                if (health < 0) health = 0;
                // optional printer to show the decrease in health
                /* System.out.println("health = " + healthBeforeObstacle + " - " + 
                                   obstacleDamage + " = " + health + " HP");*/
                // prints HP bar
                String remainingHP = "";
                String lostHP = "";
                approxHP = (int)(Math.round(health));
                for (int i = 0; i < approxHP; i++)
                {
                    remainingHP += "\u25A0";
                }
                for (int i = 0; i < 100 - approxHP; i++)
                {
                    lostHP += "\u25A1";
                }
                System.out.println("HP: " + remainingHP + lostHP);
            }

            // determines if a partner joins the game
            // a partner allows the user to move 10 more feet per turn
            if (count == 5)
            {
                if (interact >= 'a' && interact <= extraGuy)
                {
                    randInteract = 10;
                    System.out.println("One of your partners joined you on your pursuit to the station!");
                }
                else 
                {
                    System.out.println("No teammates in sight... You're on you're own...");
                }
            }
            
            // calculates the distance remaining, taking into account
            // the distance traveled by the user and his or her partner
            distRemaining = goal - (randAddDist + randInteract);
            

            if (randAddDist >= goal)
            {
                System.out.println("\n-----------------------------------");
                System.out.println(":)  :)  :)");
                System.out.println("You've seized and secured the station in " + count + " rounds!");
                System.out.println("You had " + health + " HP left!\n");
                break;
            }
            if (health <= 0)
            {
                System.out.println("\n-----------------------------------");
                System.out.println("\n:(  :(  :(");
                System.out.println("You didn't make it after " + count + " rounds!");
                System.out.println("Your HP dropped to " + health + "!");
                System.out.println("The station was "+ distRemaining + " feet away!\n");
                break;
            }
            if (count == 20)
            {
                System.out.println("\n-----------------------------------");
                System.out.println("\n:(  :(  :(");
                System.out.println("You didn't make it after " + count + " rounds!");
                System.out.println("The station was "+ distRemaining + " feet away!");
                System.out.println("You had a " + health + " HP left!\n");
            }
        } // end loop
        scan.close();
    } // end main method
} // end class