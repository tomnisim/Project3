package resources;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Course {
    private int id;
    private String name;
    private int numOfMaxStudents;
    private int seatAvailable;
    private ConcurrentHashMap<String, String> studentsRegistered;
    private ConcurrentHashMap<Integer, Integer> kdamCoursesList;

    public Course(int id,String name,int numOfMaxStudents,ConcurrentHashMap<Integer, Integer> kdamCoursesList){
        this.name=name;
        this.id=id;
        this.numOfMaxStudents=numOfMaxStudents;
        this.kdamCoursesList=kdamCoursesList;
        this.seatAvailable=numOfMaxStudents;
        this.studentsRegistered = new ConcurrentHashMap<String, String>();
    }

    public ConcurrentHashMap<Integer, Integer> getKdamCoursesList() {
        return kdamCoursesList;
    }

    public String getKdam() {
        String answer="";
        return answer+this.kdamCoursesList.keySet().toString().replaceAll(" ","");
    }

    public int getSeatsAvailable() {
        return this.numOfMaxStudents-this.studentsRegistered.size();
    }

    public String getStat() {
        List<String> studentsReg = new LinkedList<>();
        for (String s :studentsRegistered.keySet())
        {
            studentsReg.add(s);
        }
        Collections.sort(studentsReg);
        String answer="Course: ("+id+") "+name+'\n';
        answer=answer+"Seats Available: " + seatAvailable +"/"+numOfMaxStudents+'\n';
        return answer+"Students Registered: "+studentsReg.toString().replaceAll(" ","");


    }

    public boolean isStudentRegistered(String username) {
        return this.studentsRegistered.containsKey(username);
    }

    public int getId() {
        return id;
    }

    public void unregister(String username) {
        this.studentsRegistered.remove(username);
        seatAvailable++;
    }

    public void registerStudent(String userName) {
        this.studentsRegistered.putIfAbsent(userName, userName);
        this.seatAvailable--;
    }
}
