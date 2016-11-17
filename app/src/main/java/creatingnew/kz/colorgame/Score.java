package creatingnew.kz.colorgame;

/**
 * Created by Алишер on 16.06.2016.
 */
public class Score implements Comparable<Score>{

    private String scoreDate;
    public int scoreNum;

    public Score(String scoreDate, int scoreNum) {
        this.scoreDate = scoreDate;
        this.scoreNum = scoreNum;
    }

    @Override
    public int compareTo(Score another) {
        return another.scoreNum>scoreNum? 1 : another.scoreNum<scoreNum? -1 : 0;
    }

    public String getScoreText(){
        return scoreDate + " - "+ scoreNum;
    }
}
