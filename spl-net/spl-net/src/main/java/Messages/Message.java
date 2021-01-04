package Messages;

public interface  Message<T> {



     int getOpcode();
      String toString();
      String getUser();
      String getPassword();
      Integer getCourseNumber();
      Message operate(T database);

boolean isNeedConnectUser();
 void setConnectUser(String userName);
}

