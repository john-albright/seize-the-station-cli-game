/**
 * 
 * Java Data Structures Lab 03
 * Description: This program simulates a game in which a player has to
 * try to take back a train station captured by enemies. The station
 * is 300 ft away and the player has 20 turns to get to the station. The
 * player can play the game at three varying difficulties. On the way,
 * the player may meet a teammate that can help or he or she may lose 
 * a variable amount of HP depending on the obstacle encountered. 
 * 
 * Programmer: John Albright
 * Date Created: February 12, 2021
 * Date Last Modified: September 30, 2021
 * 
 */

import java.util.Random;
import java.util.Scanner;

public class SeizeTheStation 
{
    public static void main(String args[])
    {
        // Declare a Scanner class object and Random class object 
        Scanner scan = new Scanner(System.in);
        Random randomGen = new Random();

        // Introduce to the game
        PrintIntroduction();

        char sentinelValue = 'Y';

        do {

            // Variable to decrease distance to target 
            int randAddDist = 0;

            // Variable to determine the random distance moved (greater than the minimum)
            int randStep = 0;

            // Variable to track remaining distance to target
            int randDistToMove = 0;

            // Final distance to move after calculations
            int finalDist = 0;

            // Variable to use to supplement player / game interaction   
            int randInteract = 0;

            // Variable to define player obstacle		  
            char interact = '\0';

            // Variable to allow player to proceed to target
            char again = '\0';

            // Variable to set initial distance to goal
            int goal = 300; 

            // Variable to set initial player health
            int health = 100;

            // Variable to approximate hp
            int approxHP = 0; 

            // Variable to determine the obstacle
            int randObstacle = 0;

            // Variable to calculate distance remaining
            int distRemaining = 0;

            // Optional variable for the total HP printer
            // int healthBeforeObstacle = 0;

            // Variable to keep track of damage done when obstacle is encountered
            int obstacleDamage = 0;

            // Variables for difficulty level of game
            char difficulty = '\0';
            int explosiveRating = 101;
            boolean explosionSwitch = false;
            int minMovingDistance = 0;

            // Variables for round 5
            int extraGuy = 0;
            boolean notALetter = false;
            char extraGuyLower = '\0';
            char extraGuyUpper = '\0';
            char extraGuyCloserLower = '\0';
            char extraGuyCloserUpper = '\0';
            char letterForExtraGuy = '\0';

            // Selective control for difficulty level of game
            do {
                System.out.print("\nChoose your difficulty level: ");
                System.out.print("E = EASY  M = MEDIUM   H = HARD  ");
                difficulty = scan.next().toUpperCase().charAt(0);
            } while (difficulty != 'E' && difficulty != 'M' && difficulty != 'H');

            // Change parameters to make the game harder
            if (difficulty == 'E') {
                System.out.println("This should be a breeze!");
                extraGuy = 12; // 12/26 or 46% chance of finding your partner
                minMovingDistance = 26;
            } else if (difficulty == 'M') {
                System.out.println("This shouldn't be too bad...");
                extraGuy = 8; // 8/26 or 31% chance of finding your partner
                minMovingDistance = 21;
                
            } else if (difficulty == 'H') {
                System.out.println("You have to get through a mine field!");
                extraGuy = 4; // 4/26 or 15% chance of finding your partner
                minMovingDistance = 16;
                explosiveRating = 85;
                explosionSwitch = true;
            }

            // Define a loop for at most twenty actions
            for (int count = 1; count <= 20; count++) {    
                System.out.println("\n-----------------------------------");   
                System.out.println("ROUND " + count);     
                
                // Ask for the intention of the player
                do {
                    System.out.print("Are you ready to proceed? ( Y or N )  ");
                    again = scan.next().toUpperCase().charAt(0); 
                } while (again != 'Y' && again != 'N');
                
                // Exit the program if the user selects 'N'
                if (again == 'N') {
                    sentinelValue = 'N';
                    break;
                }

                // Print a line after a delay
                try {
                    Thread.sleep(600);
                    System.out.println();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // Define an obstacle
                interact = (char)(randomGen.nextInt(26) + 'a');
                if (interact >= 'a' && interact <= 'm') {
                    randStep = randomGen.nextInt(20); // Generate random number
                    randDistToMove = minMovingDistance + randStep; // Determine distance to move
                    randAddDist = randDistToMove + randInteract;
                    finalDist += randAddDist; // Add the partner's distance if available

                    // Debug print statement
                    /*
                        System.out.println("minimum distance: " + minMovingDistance + " + " 
                                            + "step: " + randStep + " + " + "help: " + randInteract + 
                                            " = " + randDistToMove);
                    */

                    // Print distance moved
                    System.out.println("> > > > Move forward " + randAddDist + " ft");

                } else {
                    // Optional assignment for total HP printer
                    // healthBeforeObstacle = health;

                    System.out.print("X X X X ");

                    randObstacle = randomGen.nextInt(101);
                    if (randObstacle >= explosiveRating && explosionSwitch) {
                        System.out.println("A bomb exploded resulting in massive damage.");
                        obstacleDamage = 40;
                    } else if (randObstacle <= (explosiveRating - 1) && randObstacle >= 80) {
                        System.out.println("An enemy threw a projectile at you!");
                        obstacleDamage = 20;
                    } else if (randObstacle <= 79 && randObstacle >= 60) {
                        System.out.println("A stray dog bit your leg!");
                        obstacleDamage = 15;
                    } else if (randObstacle <= 59 && randObstacle >= 40) {
                        System.out.println("You slipped into a puddle of water!");
                        obstacleDamage = 10;
                    } else {
                        System.out.println("You tripped on some rocks!");
                        obstacleDamage = 5;
                    }

                    // Update the remaining health after the attack
                    health -= obstacleDamage;

                    // Set the health to 0 if it's negative
                    if (health < 0) health = 0;

                    // Optional printer to show the decrease in health
                    // System.out.println("health = " + healthBeforeObstacle + " - "); 
                    // System.out.print(obstacleDamage + " = " + health + " HP");

                }
                
                // Print a line after a delay
                try {
                    Thread.sleep(1000);
                    System.out.println();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // Calculate the approximate HP
                approxHP = (int)(Math.round(health));

                PrintHP(approxHP);

                // Print a line after a delay
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // Determine if a partner joins the game
                // A partner allows the user to move 10 more feet per turn
                if (count == 5) {

                    // Print statement about a teammate joining
                    System.out.print("\nLet's see if a teammate shall join you. ");
                    System.out.print("Enter a letter: ");
                    letterForExtraGuy = scan.next().toLowerCase().charAt(0);

                    // Add "calculation" delay
                    try {
                        System.out.print("\nSearching");
                        Thread.sleep(200);
                        System.out.print(".");
                        Thread.sleep(200);
                        System.out.print(" .");
                        Thread.sleep(200);
                        System.out.print(" .");
                        Thread.sleep(200);
                        System.out.println();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    System.out.println("Letter chosen: " + letterForExtraGuy);

                    // Check if a letter was entered
                    if (letterForExtraGuy <= 'z' && letterForExtraGuy >= 'a') {
                        extraGuyLower = (char)(letterForExtraGuy - extraGuy/2);
                        extraGuyUpper = (char)(letterForExtraGuy + extraGuy/2);

                        extraGuyCloserLower = (char)(letterForExtraGuy - extraGuy/4);
                        extraGuyCloserUpper = (char)(letterForExtraGuy + extraGuy/4);

                        if (extraGuyLower < 'a') {
                            extraGuyLower = 'a';
                        }

                        if (extraGuyUpper > 'z') {
                            extraGuyUpper = 'z';
                        }

                        if (extraGuyCloserLower < 'a') {
                            extraGuyCloserLower = 'a';
                        }

                        if (extraGuyCloserUpper > 'z') {
                            extraGuyCloserUpper = 'z';
                        }

                        System.out.println("Target letter: " + interact);
                        //System.out.println("Range of letters: " + extraGuyLower + " - " + extraGuyUpper);
                    } else {
                        notALetter = true;
                    }
                    
                    try {
                        Thread.sleep(300);
                        System.out.println();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    // Use short circuit nature of boolean 
                    // If the character entered is not a letter, 
                    // the second boolean statement will not be triggered
                    if ((!notALetter) && (interact <= extraGuyUpper && interact >= extraGuyLower)) {
                        
                        // Set the variable to be added in later rounds
                        if (interact == letterForExtraGuy) {
                            randInteract = 15;
                            System.out.println("Right on the money!");
                        } else if (interact >= extraGuyCloserLower && interact <= extraGuyCloserUpper) {
                            randInteract = 11;
                            System.out.println("You were pretty close!");
                        } else {
                            randInteract = 10;
                            System.out.println("Barely got it!");
                        }

                        finalDist += randInteract; // Increment the current distance traveled
                        System.out.println("One of your teammates joined you on your journey to the station!");
                    }
                    else {
                        System.out.println("Noo!!! No teammates in sight... You're on your own...");
                    }

                    // Delay the program
                    try {
                        Thread.sleep(300);
                        System.out.println();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                // Print the total distance traveled
                System.out.println("Distance traveled: " + finalDist + "ft");

                // Calculate the distance remaining, taking into account
                // the distance traveled by the user and his or her partner
                distRemaining = goal - finalDist;
                
                // Delay the program
                try {
                    Thread.sleep(300);
                    System.out.println();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                if (distRemaining <= 0) {
                    System.out.println("\n-----------------------------------");
                    System.out.println(":)  :)  :)");
                    System.out.println("You've seized and secured the station in " + count + " rounds!");
                    System.out.println("You had " + health + " HP left!");
                    System.out.println("Game mode: " + difficulty + "\n");
                    break;
                }

                if (health <= 0) {
                    System.out.println("\n-----------------------------------");
                    System.out.println("\n:(  :(  :(");
                    System.out.println("You didn't make it after " + count + " rounds!");
                    System.out.println("Your HP dropped to " + health + "!");
                    System.out.println("The station was "+ distRemaining + " feet away!");
                    System.out.println("Game mode: " + difficulty + "\n");
                    break;
                }

                if (count == 20) {
                    System.out.println("\n-----------------------------------");
                    System.out.println("\n:(  :(  :(");
                    System.out.println("You didn't make it after " + count + " rounds!");
                    System.out.println("The station was "+ distRemaining + " feet away!");
                    System.out.println("You had a " + health + " HP left!");
                    System.out.println("Game mode: " + difficulty + "\n");
                }
            } // end loop

            
            // Ask the player if he or she would like to play again
            char finalResponse;

            do {
                System.out.print("\nWould you like to play the game again? ( Y or N )  ");
                finalResponse = scan.next().toUpperCase().charAt(0);
            } while (finalResponse != 'Y' && finalResponse != 'N');

            if (finalResponse == 'Y') {
                sentinelValue = 'Y';
            } else {
                sentinelValue = 'N';
            }   

        } while (sentinelValue == 'Y');

        scan.close();
    } // end main method

    static void PrintIntroduction() {
        System.out.println("\n************************************************************************");
        System.out.println("************************* SEIZE THE STATION ****************************");
        System.out.println("\nWelcome! You will have 20 rounds to reach the station.");
        System.out.println("You will have a chance to move closer each round.");
        System.out.println("However, you may be met with an obstacle and lose HP.");
        System.out.println("If your HP bar reaches 0 before you get to the station, you lose.");
        System.out.println("After 5 rounds, you may meet a partner who will help you travel");
        System.out.println("to the station faster.");
        System.out.println("Good luck! ¡Buena suerte! Boa sorte! Bonne chance! Viel Glück! 頑張って！\n");
        System.out.println("************************************************************************");
    }

    static void PrintHP(int currentHP) {
        String remainingHP = "";
        String lostHP = "";

        for (int i = 0; i < currentHP; i++) {
            remainingHP += "\u25A0";
        }

        for (int i = 0; i < 100 - currentHP; i++) {
            lostHP += "\u25A1";
        }

        System.out.println("HP: " + remainingHP + lostHP);
    }

} // end class