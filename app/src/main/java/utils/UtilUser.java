package utils;

import com.example.readingsoftware.datamodel.User;

/**
 * Created by 上官刀刀 on 2018/8/22.
 */

public class UtilUser {
    private static UtilUser instance=new UtilUser();
    private User user;
    private UtilUser()
    {

    }
    public static UtilUser getInstance()
    {
        return instance;
    }
    public void setUser(User user)
    {
        this.user=user;
    }

    public User getUser()
    {
        return user;
    }

}
