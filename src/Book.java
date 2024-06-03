import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Book {
    private Map ghostMap;
    private Map evidenceMap;
    private int evidenceCount = 3;
    private final static String[] NAMES = new String[]{"Spirit", "Wraith", "Phantom", "Poltergeist", "Banshee", "Jinn", "Mare",
            "Revenant", "Shade", "Demon", "Yurei", "Oni", "Yokai", "Hantu", "Goryo",
            "Myling", "Onryo", "The Twins", "Raiju", "Obake", "The Mimic", "Moroi",
            "Deogen", "Thaye"};
    private final static String[] EVIDENCE = new String[]{"EMF5", "DOTS", "UV","Ghost Writing","Ghost Orb", "Spirit Box", "Freezing Temperatures"};
    private static ArrayList<Ghost> ghostArray = new ArrayList<>();
    private static void initializeMaps(String[] NAMES, String[] EVIDENCE, Map ghostMap, Map evidenceMap) {
        for (String name: NAMES) {
            ghostMap.put(name, 1);
        }
        for (String evidence: EVIDENCE) {
            evidenceMap.put(evidence, 1);
        }
    }

    private static void initializeArray() { //For this I will need to create a DB of all ghost info
        ghostArray = DataParse.fetchGhosts();
    }
    public Book() {
        this.ghostMap = new HashMap();
        this.evidenceMap = new HashMap();
        initializeMaps(NAMES, EVIDENCE, ghostMap, evidenceMap);
        initializeArray();
    }

    public String[] getValidEvidence() {
        String [] returnList = new String[evidenceMap.size()];
        for (int i = 0; i < EVIDENCE.length; i++) {
            if (evidenceMap.containsKey(EVIDENCE[2])) {
                returnList[i] = EVIDENCE[i];
            }
        }
        return returnList;
    }

    public String[] getInvalidEvidence() {
        String [] returnList = new String[evidenceMap.size()];
        for (int i = 0; i < EVIDENCE.length; i++) {
            if (evidenceMap.containsKey(EVIDENCE[0])) {
                returnList[i] = EVIDENCE[i];
            }
        }
        return returnList;
    }
    public void validateEvidence(String evidence) {
        evidenceMap.put(evidence, 2);
        remapGhosts();
    }
    public void invalidateEvidence(String evidence) {
        evidenceMap.put(evidence, 0);
        remapGhosts();
    }
    public String getGhostInfo(String name) {
        for (int i = 0; i < ghostArray.size(); i++) {
            if (ghostArray.get(i).getName().toUpperCase().matches(name.toUpperCase())) {
                return ghostArray.get(i).toString();
            }
        }

        return ghostArray.get(ghostArray.indexOf(name)).toString();
    }

    public void remapGhosts() {
        for (int i = 0; i < ghostArray.size(); i++) {
            Ghost chosenGhost = ghostArray.get(i);
            int ghostSwitch = chosenGhost.isValid(getInvalidEvidence(),getValidEvidence(),evidenceCount);
            switch (ghostSwitch) {
                case 0:
                    ghostMap.put(chosenGhost.getName(), 0);
                    break;
                case 1:
                    ghostMap.put(chosenGhost.getName(), 1);
                    break;
                case 2:
                    ghostMap.put(chosenGhost.getName(), 2);
                    break;
                default:
                    ghostMap.put(chosenGhost.getName(), 1);
                    break;
            }
        }
    }

    @Override
    public String toString() {
        remapGhosts();
        String returnString = "";
        returnString += "EVIDENCE \n";
        for (int j = 0; j < EVIDENCE.length; j++) {
            returnString += EVIDENCE[j] + " | " + evidenceMap.get(EVIDENCE[j]) + "\n";
        }
        returnString += "GHOSTS \n";
        for (int i = 0; i < NAMES.length; i++) {
            returnString+=(NAMES[i] +" | " + ghostMap.get(NAMES[i])) + "\n";
        }
        return returnString;
    }

    public void setEvidenceCount(int evidenceCount) {
        this.evidenceCount = evidenceCount;
    }

}
