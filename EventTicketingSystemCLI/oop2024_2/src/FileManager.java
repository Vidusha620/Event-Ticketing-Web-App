import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //Save configuration as a JSON file
    public static void saveAsJSON(SystemConfiguration systemConfiguration,String jsonFileName){
        try(FileWriter writer =new FileWriter(jsonFileName)){
            gson.toJson(systemConfiguration,writer); //Convert configuration to JSON and write in the file
            System.out.println("Configuration saved to JSON file: "+ jsonFileName);
        } catch (IOException e) {
            System.out.println("Error saving configuration: "+e.getMessage());;
        }

    }

    public static SystemConfiguration loadFromJSON(String jsonFileName){
        try(FileReader reader = new FileReader(jsonFileName)){
            return gson.fromJson(reader, SystemConfiguration.class);// Convert JSON back to an Object
        } catch (IOException e) {
            System.out.println("Error loading configuration: "+e.getMessage());
        }
        return null;
    }

}
