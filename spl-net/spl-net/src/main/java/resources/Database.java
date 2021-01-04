package resources;


import resources.Admin;
import resources.Course;
import resources.Student;
import resources.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * Passive object representing the Database where all courses and users are stored.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add private fields and methods to this class as you see fit.
 */
public class Database {


    //to prevent user from creating new Database

    private HashMap<Integer, Course> courselist;
    private HashMap<String, Admin> adminsList;
    private HashMap<String, Student> studentsList;

    private static class SingletonHolder {
        private static Database instance = new Database();
    }

    private Database() {
        adminsList = new HashMap<String, Admin>();
        studentsList = new HashMap<String, Student>();
        courselist = new HashMap<Integer, Course>();


    }

    /**
     * Retrieves the single instance of this class.
     */
    public static Database getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * loades the courses from the file path specified
     * into the Database, returns true if successful.
     */
    public boolean initialize(String coursesFilePath) {
        try (BufferedReader b = new BufferedReader(new FileReader(coursesFilePath))) {
            String line = b.readLine();
            while (line != null) {
                String courses = line;
                int courseNum = 0;
                String courseName = "";
                String kdamCourseList = "";
                int numOfMaxStudents = 0;
                int place = 0;
                List<Integer> kdamCourse;
                place = courses.indexOf('|');
                String courseN = courses.substring(0, place - 1);//check if it include the end of the range
                courses = courses.substring(place + 1);//rest of the line
                courseNum = stringToInt(courseN);
                place = courses.indexOf('|');
                courseName = courses.substring(0, place - 1);//check if it include the end of the range
                courses = courses.substring(place + 1);
                place = courses.indexOf('|');
                kdamCourseList = courses.substring(0, place - 1);//check if it include the end of the range
                kdamCourse = stringToListInt(kdamCourseList);
                courses = courses.substring(place + 1);
                String numOfMS = courses;//check if it include the end of the range
                numOfMaxStudents = stringToInt(numOfMS);
                Course course = new Course(courseNum, courseName, numOfMaxStudents, kdamCourse);
                courselist.put(courseNum, course);
                line = b.readLine();


            }
            return true;


        }
        catch (Exception e) {
        }
        return false;
    }







    // update database methods
    public void RegisterAdmin (String username, String password) {
        if (adminsList.containsKey(username) | studentsList.containsKey(username))
            throw new IllegalArgumentException("username is already registered");
        adminsList.put(username,new Admin(username,password));

    }
    public void StudentRegister(String username, String password) {
        if (adminsList.containsKey(username) | studentsList.containsKey(username))
            throw new IllegalArgumentException("username is already registered");
        studentsList.put(username,new Student(username,password));

    }
    public User login(String username, String password) {
        // no such a user
        if (!adminsList.containsKey(username) & !studentsList.containsKey(username))
            throw new IllegalArgumentException("there is no such an user");
        User user=null;
        // admin login
        if (adminsList.containsKey(username))
            user = adminsList.get(username);
        // student login
        if (studentsList.containsKey(username))
            user = studentsList.get(username);
        // wrong password
        if (user.getPassword()!=password)
            throw new IllegalArgumentException("wrong password");
        // already login
        if (user.getStatus())
            throw new IllegalArgumentException("the user is already logged in");
        // login
        user.login();
        return user;
    }
    public void logout(String username) {
        // no such a user
        if (!adminsList.containsKey(username) & !studentsList.containsKey(username))
            throw new IllegalArgumentException("there is no such an user");
        User user=null;
        // admin logout
        if (adminsList.containsKey(username))
            user = adminsList.get(username);
        // student logout
        if (studentsList.containsKey(username))
            user = studentsList.get(username);
        // already login
        if (!user.getStatus())
            throw new IllegalArgumentException("the user is not logged in");
        // logout
        user.logout();
    }
    public void registerToCourse(String userName,int courseNumber) {
        // no such a course
        if (!courselist.containsKey(courseNumber))
            throw new IllegalArgumentException("no such a course");
        // no seats available
        Course course = courselist.get(courseNumber);
        if (course.getSeatsAvailable()==0)
            throw new IllegalArgumentException("no seats available");
        // student doesnt complete kdamCourse
        if (!this.studentsList.get(userName).finishKdamCourses(this.courselist.get(courseNumber).getKdamCoursesList()))
            throw new IllegalArgumentException("the student doesnt complete all the required kdam courses");
        // the student isnt login
        if(!this.studentsList.get(userName).getStatus())
        {
            throw new IllegalArgumentException("the student isnt login");
        }
        if (!this.studentsList.containsKey(userName)){
            throw new IllegalArgumentException("admin cant register to a course");
        }
        this.courselist.get(courseNumber).registerStudent(userName);
        this.studentsList.get(userName).addCourse(courseNumber);

    }
    public String kdamCheck(int courseNumber,String username) {
        if (!courselist.containsKey(courseNumber))
            throw new IllegalArgumentException("there is not such a course");
        if (!this.studentsList.containsKey("username"))
            throw new IllegalArgumentException("kdam check valid only for student");
        return courselist.get(courseNumber).getKdam();

    }
    public String CourseStat(int courseNumber,String username) {
        if (!courselist.containsKey(courseNumber))
            throw new IllegalArgumentException("there is not such a course");
        if (this.studentsList.containsKey(username))
        {
            throw new IllegalArgumentException("student cant send admin message");
        }
        return courselist.get(courseNumber).getStat();

    }
    public String studentStat(String username,String AdminUsername) {
        if (!this.studentsList.containsKey(username))
            throw new IllegalArgumentException("there is no such an user");
        if (!this.adminsList.containsKey(adminsList))
        {
            throw new IllegalArgumentException("student cant send admin message");
        }
        return this.studentsList.get(username).getStat();

    }
    public boolean isRegisterd(String username,int courseNumber) {
        if (!this.studentsList.containsKey(username))
            throw new IllegalArgumentException("there is no such an user");
        if (!courselist.containsKey(courseNumber))
            throw new IllegalArgumentException("there is not such a course");
        return this.courselist.get(courseNumber).isStudentRegistered(username);

    }
    public boolean unregister(String username,int courseNumber) {
        if (!this.studentsList.containsKey(username))
            throw new IllegalArgumentException("there is no such an user");
        if (!courselist.containsKey(courseNumber))
            throw new IllegalArgumentException("there is not such a course");
        if (!this.courselist.get(courseNumber).isStudentRegistered(username))
            throw new IllegalArgumentException("the student is not registerd to this course");
        this.courselist.get(courseNumber).unregister(username);
        return true;
    }
    public String myCourses(String username) {
        String answer="[";
        if (!this.studentsList.containsKey(username))
            throw new IllegalArgumentException("there is no such an user");
        for (Course c:courselist.values())
        {
            if(c.isStudentRegistered(username))
            {
                answer=answer+c.getId()+",";
            }
        }
        return answer.substring(0,answer.length()-1)+"]";

    }
    // private methods
    private List<Integer> stringToListInt(String s) {
        String sCopy=s;
        sCopy=sCopy.substring(1,sCopy.length()-1);//remove the []
        List<Integer> ans = new LinkedList<>();
        while (sCopy.contains(","))
        {
            int place=sCopy.indexOf(',');
            ans.add(stringToInt(sCopy.substring(0,place-1)));
            sCopy=sCopy.substring(place+1);
        }
        if (sCopy.length()>0)
        {
            ans.add(stringToInt(sCopy));
        }
        return ans;
    }
    private int stringToInt(String s) {
        int pow=0;
        int ans=0;
        for (int i=s.length()-1;i>=0 ;i--)
        {
            ans=ans+((s.charAt(i)-'0')*(int)Math.pow(10,pow));
            pow++;
        }
        return ans;


    }
    private String listToString(List<Integer> list) {
        // have to implement
        return "";
    }
// tom rfe
}
