package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        int deuceOrAbove = 4;
        if (m_score1==m_score2){
            return evenScore();
        }
        else if (m_score1>=deuceOrAbove || m_score2>=deuceOrAbove) {
            return endGameScore();
        }
        else {
            return pointsToScoreName(m_score1) +"-"+pointsToScoreName(m_score2);
        }
    }

    private String pointsToScoreName(int tempScore) {
        if (tempScore == 0) return "Love";
        else if (tempScore == 1) return "Fifteen";
        else if (tempScore == 2) return "Thirty";
        else { return "Forty"; }
    }

    private String endGameScore() {
        String score;
        int minusResult = m_score1-m_score2;
        if (minusResult==1) score ="Advantage player1";
        else if (minusResult ==-1) score ="Advantage player2";
        else if (minusResult>=2) score = "Win for player1";
        else score ="Win for player2";
        return score;
    }

    private String evenScore() {
        String score = "";
        if(m_score1<4){
            return pointsToScoreName(m_score1) + "-All";
        } else {
            return "Deuce";
        }
    }
}