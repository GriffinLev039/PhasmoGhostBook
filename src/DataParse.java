import org.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataParse {
    public static ArrayList<Ghost> fetchGhosts() {
        try {
            return parseJSON(getJSON("src/PhasmoWiki.json"));
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
            System.out.println("Program failed, try again.");
            return null;
        }
    }




    private static JSONObject getJSON(String path) throws FileNotFoundException {
        String jsonObj="";
        File file = new File(path);
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            jsonObj+=input.nextLine();
        }
        JSONObject obj = new JSONObject(jsonObj);
        return obj;
    }

    private static ArrayList<Ghost> parseJSON(JSONObject object) {
        ArrayList<Ghost> allGhosts = new ArrayList<>();
        JSONArray ghosts = object.getJSONArray("ghosts");
        System.out.println(ghosts.length());
        for (Object ghost : ghosts) {
            JSONObject ghostObj = (JSONObject) ghost;
            allGhosts.add(createGhost(ghostObj));
        }
        return allGhosts;
    }

    private static Ghost createGhost(JSONObject object) {
        String name = object.getString("ghost");
        String description = ""; //No easy way to parse atm, will figure out soon:tm:
        //The below snippet checks if the maxSpeed exists, and if so formats the speed string in a way that a range is displayed instead of
        //a flat speed.
        int minSpeedInt = object.getInt("min_speed");
        int maxSpeedInt = 0;
        try {
            maxSpeedInt = object.getInt("max_speed");
        }
        catch (Exception e) {
            maxSpeedInt = 0;
        }
        String speedStr = "";
        if (maxSpeedInt == 0) {
            if (minSpeedInt == 1.7) {
                speedStr = "NORMAL (1.7m/s)";
            } else if (minSpeedInt > 1.7) {
                speedStr = "FAST (" + minSpeedInt + "m/s)";
            } else {
                speedStr = "SLOW (" + minSpeedInt + "m/s)";
            }
        } else {
            speedStr = "VARIABLE (" + minSpeedInt + "-" + maxSpeedInt + "m/s)";
        }
        String[] evidence= new String[object.getJSONArray("evidence").length()];
        for (int i = 0; i < evidence.length; i++) {
            evidence[i]=object.getJSONArray("evidence").getString(i);
        }
        String quirk = ""; //No easy way to parse atm, will figure out soon:tm:
        String requiredEvidence = ""; //No easy way to parse atm, will figure out soon:tm:
        int huntingSanity = Integer.parseInt(object.getString("hunt_sanity").replace("%",""));
        Ghost ghostObj = new Ghost(name, description, speedStr, evidence, quirk, requiredEvidence, huntingSanity);
        return ghostObj;
    }

}
