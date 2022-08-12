package org.example.virtualkey.screens;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.example.virtualkey.entities.Directory;
import org.example.virtualkey.services.ScreenService;

public class FileOptionsScreen implements Screen {
	
	private Directory dir = new Directory();	
	private ArrayList<String> options = new ArrayList<>();
    public FileOptionsScreen() {
    	
    	options.add("1. To Add a New File");
        options.add("2. To Delete a File");
        options.add("3. Search for a File");
        options.add("4. Return to Main Menu");
        
    }
    
    @Override
    public void Show() {
    	System.out.println("File Option-Menu");
        for (String s : options) {
            System.out.println(s);
        }
    }

    public void GetUserInput() {
        int selectedOption;
        while ((selectedOption = this.getOption()) != 4) {
            this.NavigateOption(selectedOption);
        }
    }

    @Override
    public void NavigateOption(int option) {        
    	switch(option) {

            case 1: 
                this.AddFile();                
                this.Show();
                break;
                
            case 2: 
                this.DeleteFile();                
                this.Show();
                break;
                
            case 3: 
                this.SearchFile();
                this.Show();
                break;
            
            default:
                System.out.println("Invalid Option");
                break;                               
        }
    }
    
    public void AddFile() {
    	
        System.out.println("Please enter the Filename: ");
        String fileName = this.getInputString();
        System.out.println("Enterted Filename: " + fileName);
        
        System.out.println("Enter the text: ");
        String inputtext = this.getInputString();       
        System.out.println("Entered Text: "+ inputtext);
        
		try {
			Path path = FileSystems.getDefault().getPath(Directory.name + fileName).toAbsolutePath();
			File file = new File(dir.getName() + fileName);
			
		      if (file.createNewFile()) {
		    	  System.out.println("Created File: " + file.getName());
		    	  dir.getFiles().add(file);
		    	  
		    	  BufferedWriter out = new BufferedWriter(
		                  new FileWriter(file));
		 
		              out.write(inputtext);		              
		              out.close();
		    	  		    	  
		      } else {
		        System.out.println("File Already Exits");
		      }
		}catch (IOException e){
			System.out.println(e);
		}
	}
            
    public void DeleteFile() {
    	
    	System.out.println("Please Enter the Filename: ");
        String fileName = this.getInputString();
        System.out.println("Entered Filename: " + fileName);
                    
		Path path = FileSystems.getDefault().getPath(Directory.name + fileName).toAbsolutePath();
		File file = path.toFile();
	      
		if (file.delete()) {
	    	  System.out.println("Deleted File: " + file.getName());
	    	  dir.getFiles().remove(file);
	      } else {
	        System.out.println(fileName + ": File Not Found");
	      }
    }
    
    public void SearchFile() {
       	Boolean found = false;
    	
    	System.out.println("Please Enter the Filename: ");
        String fileName = this.getInputString();
        System.out.println("Entered Filename: " + fileName);        
        ArrayList<File> files = dir.getFiles();                
        for(int i = 0; i < files.size(); i++) {
			if(files.get(i).getName().equals(fileName)) {
				System.out.println("Found " + fileName);
				found = true;
			}
        }        
        if (found == false) {
        	System.out.println("File not found");
        }
    }    
    private String getInputString() {
        Scanner in = new Scanner(System.in);
        return(in.nextLine());
    }   
    private int getOption() {
        Scanner in = new Scanner(System.in);
        int returnOption = 0;
        try {
            returnOption = in.nextInt();
        }
        catch (InputMismatchException ex) {
        	System.out.println("Invalid input");
        }
        return returnOption;
    }
}
