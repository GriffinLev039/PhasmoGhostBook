public class Ghost {
    private String name;
    private String description;
    private String speed;
    private String[] evidenceArr;
    private String quirk;
    private String requiredEvidence;
    private int huntingSanity;


    public Ghost() {
        this.name = "Spirit";
        this.description = "";
        this.speed = "Slow (XXX m/s)";
        this.evidenceArr = new String[]{"Freezing Temperature", "Ghost Orb", "Spirit Box"};
        this.quirk = "";
        this.requiredEvidence = "";
        this.huntingSanity = 0;
    }

    public Ghost(String name) {
        this.name = name;
        this.description = "";
        this.speed = "Slow (XXX m/s)";
        this.evidenceArr = new String[]{"Freezing Temperature", "Ghost Orb", "Spirit Box"};
        this.quirk = "";
        this.requiredEvidence = "";
        this.huntingSanity = 0;
    }

    public Ghost(String name, String description, String speed, String[] evidenceArr, String Quirk, String requiredEvidence, int huntingSanity) {
        this.name = name;
        this.description = description;
        this.speed = speed;
        this.evidenceArr = evidenceArr;
        this.quirk = Quirk;
        this.requiredEvidence = requiredEvidence;
        this.huntingSanity = huntingSanity;
    }


    public int isValid(String[] deconfirmedEvidence, String[] confirmedEvidence, int evidenceMode) {
        int deconfMatchCount = 0;
        for (String deconfEvidence : deconfirmedEvidence) {
            if (!(deconfEvidence==null)) {
                for (String ghostEvidence : evidenceArr) {
                    if (deconfEvidence.equals(ghostEvidence)) {
                        deconfMatchCount++;
                    }
                    if (deconfEvidence.equals(requiredEvidence)) {
                        return 0;
                    }
                }
            }
        }
        if (deconfMatchCount >= evidenceMode) {
            return 0;
        }
        int confMatchCount = 0;
        for (String confEvidence: confirmedEvidence) {
            for (String ghostEvidence : evidenceArr) {
                if (!(confEvidence==null)) {
                if (confEvidence.equals(ghostEvidence)) {
                    confMatchCount++;
                }
                }
            }
        }
        if (confMatchCount == evidenceMode) {
            return 2;
        }
        return 1;
    }

    public String[] getEvidence() {
        return evidenceArr;
    }
    public String getName() {return this.name;}
    @Override
    public String toString() {
        return "Ghost: " + this.name + "\n Evidence: " + evidenceArr[0] + " | " + evidenceArr[1] + " | " + evidenceArr[2];
    }
}