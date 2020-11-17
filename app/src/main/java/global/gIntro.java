package global;

import android.widget.EditText;

public class gIntro {
    private String studentName;
    private String studentJob;

    public gIntro(String studentName, String studentJob) {
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



