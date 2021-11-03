package huukhuong.multiplechoice.model;

public class Question {

    private int id;
    private String question;
    private String ansA;
    private String ansB;
    private String ansC;
    private String ansD;
    private String correct;
    private int subjectId;

    public Question() {
    }

    public Question(int id, String question, String ansA, String ansB, String ansC, String ansD, String correct, int subjectId) {
        this.id = id;
        this.question = question;
        this.ansA = ansA;
        this.ansB = ansB;
        this.ansC = ansC;
        this.ansD = ansD;
        this.correct = correct;
        this.subjectId = subjectId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnsA() {
        return ansA;
    }

    public void setAnsA(String ansA) {
        this.ansA = ansA;
    }

    public String getAnsB() {
        return ansB;
    }

    public void setAnsB(String ansB) {
        this.ansB = ansB;
    }

    public String getAnsC() {
        return ansC;
    }

    public void setAnsC(String ansC) {
        this.ansC = ansC;
    }

    public String getAnsD() {
        return ansD;
    }

    public void setAnsD(String ansD) {
        this.ansD = ansD;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                "\n question='" + question + '\'' +
                "\n ansA='" + ansA + '\'' +
                "\n ansB='" + ansB + '\'' +
                "\n ansC='" + ansC + '\'' +
                "\n ansD='" + ansD + '\'' +
                "\n correct='" + correct + '\'' +
                "\n subjectId=" + subjectId +
                '}';
    }
}
