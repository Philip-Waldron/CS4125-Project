public class LogIn {
    
    public LogIn()
    {
        
    }
           public boolean verify(User u, String pass) 
           {
               return (u.password == pass);
           }
}
