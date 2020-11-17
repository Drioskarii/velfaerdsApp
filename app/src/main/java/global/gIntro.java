package global;

import android.widget.EditText;

public class gIntro {
    private String studentName;
    private String studentJob;
    private String studentGender;

    public gIntro(String studentName, String studentJob, String studentGender) {
        this.studentName = studentName;
        this.studentJob = studentJob;
        this.studentGender = studentGender;


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

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }
}



