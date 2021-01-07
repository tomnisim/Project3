package resources;



import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public class Student extends User {
    //private List<Integer> finishCourses;
    private ConcurrentHashMap<Integer, Integer> finishCourses;
    private Database database=Database.getInstance();



    public Student(String userName,String password) {
        super(userName,password);
        this.finishCourses=new ConcurrentHashMap<Integer, Integer>();
    }
    public void addCourse(int courseNumber){
        this.finishCourses.put(courseNumber, courseNumber);
    }
    public String getStat() {
        String answer="Student: "+this.userName+'\n';
        answer=answer+"Courses: ";
        ConcurrentHashMap<Integer, Course> ordered = database.getCourselistOredered();
        List<Integer> ans = new LinkedList<>();
        for (int i=0;i<ordered.size();i++)
        {
            if (this.finishCourses.containsKey(ordered.get(i).getId()))
            {
                ans.add(ordered.get(i).getId());
            }
        }
        answer = answer+ans.toString().replaceAll(" ", "");

        return answer;
    }

    public boolean finishKdamCourses(ConcurrentHashMap<Integer, Integer> kdamCoursesList) {
        int length = kdamCoursesList.size();
        for (Integer i : kdamCoursesList.keySet())
        {
            if (this.finishCourses.containsKey(i))
                length--;
        }

        return length==0;
    }
    public String getMyCourses()
    {
        String answer="";
        ConcurrentHashMap<Integer, Course> ordered = database.getCourselistOredered();
        List<Integer> ans = new LinkedList<>();
        for (int i=0;i<ordered.size();i++)
        {
            if (this.finishCourses.containsKey(ordered.get(i).getId()))
            {
                ans.add(ordered.get(i).getId());
            }
        }
        answer = answer+ans.toString().replaceAll(" ", "");
        return answer;
    }
    public void unregister(int course){
        if (finishCourses.containsKey(course)){
            this.finishCourses.remove(course);
        }

    }

}
