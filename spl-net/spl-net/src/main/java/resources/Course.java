package resources;


import java.util.LinkedList;
import java.util.List;

public class Course {
    private int id;
    private String name;
    private int numOfMaxStudents;
    private int seatAvailable;
    private List<String> studentsRegistered;
    private List<Integer> kdamCoursesList;

    public Course(int id,String name,int numOfMaxStudents,List<Integer> kdamCoursesList){
        this.name=name;
        this.id=id;
        this.numOfMaxStudents=numOfMaxStudents;
        this.kdamCoursesList=kdamCoursesList;
        this.seatAvailable=numOfMaxStudents;
        this.studentsRegistered = new LinkedList<String>();
    }

    public List<Integer> getKdamCoursesList() {
        return kdamCoursesList;
    }

    public String getKdam() {
        String answer="";
        for (int i=0;i<this.kdamCoursesList.size();i++)
        {
            answer=answer+this.kdamCoursesList.get(i).toString()+",";
        }

        return answer.substring(0, answer.length()-1);
    }

    public int getSeatsAvailable() {
        return this.numOfMaxStudents-this.studentsRegistered.size();
    }

    public String getStat() {
        String answer="Course: "+name+'\n';
        answer=answer+" Seats Available: " + seatAvailable +"/"+numOfMaxStudents+'\n';
        answer=answer+" Students Registered:[";
        for (int i=0;i<studentsRegistered.size();i++){
            answer=answer+this.studentsRegistered.get(i);
        }
        answer=answer+"]";
        return answer;


    }

    public boolean isStudentRegistered(String username) {
        return this.studentsRegistered.contains(username);
        // have to change the field - not student, string
    }

    public void unregister(String username) {
        this.studentsRegistered.remove(username);
    }

    public void registerStudent(String userName) {
        this.studentsRegistered.add(userName);
    }
}
