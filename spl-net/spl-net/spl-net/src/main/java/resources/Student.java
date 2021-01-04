package resources;


import java.util.LinkedList;
import java.util.List;

public class Student extends User {
    private List<Integer> finishCourses;
    public Student(String userName,String password) {
        super(userName,password);
        this.finishCourses=new LinkedList<Integer>();
    }
    public void addCourse(int courseNumber){
        this.finishCourses.add(courseNumber);
    }
    public String getStat() {
        String answer="Student: "+this.userName+' ';
        answer=answer+" Courses:[";
        for (int i=0;i<this.finishCourses.size();i++){
            answer=answer+this.finishCourses.get(i)+",";
        }
        answer=answer.substring(0, answer.length()-1)+"]";
        return answer;
    }

    public boolean finishKdamCourses(List<Integer> kdamCoursesList) {
        int length = kdamCoursesList.size();
        for (int i=0;i<kdamCoursesList.size();i++)
        {
            if (this.finishCourses.contains(kdamCoursesList.get(i)))
                length--;
        }
        return length==0;
    }
}
