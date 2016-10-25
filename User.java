public class User {
    int wins = 0, draws = 0, losses = 0, mWins = 0, mDraws = 0, mLosses = 0;
    String uName = "", password = "";
    
    
    public User(String name, String pass)
    {
        uName = name;
        for(int i = 0; i<8; i++)
            uID = uID + (char)((int)(((Math.random())*26) + 65));
        password = pass;
    }
    public User(String name, int w, int d, int l, String id)
    {
        uName = name;
        wins = w;
        draws = d;
        losses = l;   
        uID = id;
        for(int i = 0; i<6; i++)
            uID = uID + (char)((int)(((Math.random())*26) + 65));
    }
    public void newWin()
    {
        wins++;
        mWins++;
    }
     public void newDraw()
    {
        draws++;
        mDraws++;
    }
      public void newLoss()
    {
        losses++;
        mLosses++;
    }
      public void resetMatch()
      {
          mWins = 0;
          mDraws = 0;
          mLosses = 0;
      }
}
