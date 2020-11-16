package global;

public class gQuestion {
    private String studentName;
    private String studentJob;

    public gQuestion(String studentName, String studentJob) {
        this.studentName = studentName;
        this.studentJob = studentJob;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentJob() {
        return studentJob;
    }

    public void setStudentJob(String studentJob) {
        this.studentJob = studentJob;
    }
}
