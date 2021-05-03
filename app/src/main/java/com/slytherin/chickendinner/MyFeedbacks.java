package com.slytherin.chickendinner;

public class MyFeedbacks {
    private String fName;
    private String fEmail;
    private String fNote;

    public MyFeedbacks() {

    }

    public MyFeedbacks(String fName, String fEmail, String fNote) {
        this.fName = fName;
        this.fEmail = fEmail;
        this.fNote = fNote;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfEmail() {
        return fEmail;
    }

    public void setfEmail(String fEmail) {
        this.fEmail = fEmail;
    }

    public String getfNote() {
        return fNote;
    }

    public void setfNote(String fNote) {
        this.fNote = fNote;
    }
}
