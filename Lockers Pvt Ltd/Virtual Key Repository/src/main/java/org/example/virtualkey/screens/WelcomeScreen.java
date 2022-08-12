package org.example.virtualkey.screens;

import org.example.virtualkey.services.DirectoryService;
import org.example.virtualkey.services.ScreenService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WelcomeScreen implements Screen {

    private String welcome = "Welcome to Virtual Keys Repository";
    private String devName = "Developer Name: Mukesh Kanna";
    private ArrayList<String> options = new ArrayList<>();

    public WelcomeScreen() {
        options.add("1. Show All Files");
        options.add("2. Show File Option-Menu");
        options.add("3. Exit");
    }
    
    public void introWS() {
    	System.out.println(welcome);
        System.out.println(devName);
        System.out.println("\n*********************************");
        Show();
    }
       
    @Override
    public void Show() {
    	System.out.println("\n");
    	System.out.println("Main Menu");
        for (String s : options)  {
            System.out.println(s);
        }
    }

    public void GetUserInput() {
        int selectedOption  = 0;
        while ((selectedOption = this.getOption()) != 3) {
            this.NavigateOption(selectedOption);
        }
    }

    @Override
    public void NavigateOption(int option) {
        switch(option) {

            case 1: 
                this.ShowFiles();               
                this.Show();               
                break;
                
            case 2: 
            	ScreenService.setCurrentScreen(ScreenService.FileOptionsScreen);
                ScreenService.getCurrentScreen().Show();
                ScreenService.getCurrentScreen().GetUserInput();               
                this.Show();                
                break;
                
            default:
                System.out.println("Invalid Option");
                break;
        }        
    }
    public void ShowFiles() {
    	DirectoryService.PrintFiles();
    }
    
    private int getOption() {
        Scanner in = new Scanner(System.in);
        int returnOption = 0;
        try {
            returnOption = in.nextInt();
        }
        catch (InputMismatchException ex) {
        }
        return returnOption;
    }
}
