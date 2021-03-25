package menu;

public class ScoreEntry implements Comparable{
    private int score;
    private String initials;

    public ScoreEntry(int score, String initials){
        this.score = score;
        this.initials = initials;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    @Override
    public int compareTo(Object o) {
        ScoreEntry entry = (ScoreEntry) o;

        return score - entry.score;
    }
}
