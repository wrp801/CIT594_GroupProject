package edu.upenn.cit594.ui;

import edu.upenn.cit594.processor.Processor;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {

    protected Processor processor;
    protected Scanner in;

    public UserInterface(Processor processor) {
        this.processor = processor;
        in = new Scanner(System.in);
    }

    public void start() {

        System.out.println("What do you want to know?" +
                "\nEnter the number corresponding with the desired calculation." +
                "\n0 - Exit program" +
                "\n1 - Total population of all ZIP codes" +
                "\n2 - Total parking fines per capita for each ZIP code" +
                "\n3 - Average market value for residences in a specified ZIP code" +
                "\n4 - Average total livable area for residences in a specified ZIP code" +
                "\n5 - Total residential market value per capita for a specified ZIP code" +
                "\n6 - Show <CUSTOM FEATURE>");

        int choice = -1;

        try {
            choice = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please input a single integer.");
            System.exit(0);  // Would be better to loop them back to decision step but not required
        }

        if (choice < 0 || choice > 6) {
            System.out.println("Please input a number 0 through 6.");
            System.exit(0);
        }

        if (choice == 0) System.exit(0);
        if (choice == 1) processor.displayTotalPopulation();
        if (choice == 1) processor.displayFinesPerCapita();

    }

}
