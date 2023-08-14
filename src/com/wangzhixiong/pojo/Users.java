package com.wangzhixiong.pojo;

/**
 * 建表语句如下
 * create table users (
 *     userid int(11) NOT NULL AUTO_INCREMENT,
 *     username VARCHAR(30) DEFAULT NULL,
 *     userpwd VARCHAR(30) DEFAULT NULL,
 *     usersex VARCHAR(2) DEFAULT NULL,
 *     phonenumber VARCHAR(30) DEFAULT NULL,
 *     qqnumber VARCHAR(20) DEFAULT NULL,
 *     PRIMARY KEY(userid)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Users
{
    private int userid;
    private String username;
    private String userpwd;
    private String usersex;
    private String phonenumber;
    private String qqnumber;

    public Users()
    {
    }

    public int getUserid()
    {
        return userid;
    }

    public void setUserid(int userid)
    {
        this.userid = userid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUserpwd()
    {
        return userpwd;
    }

    public void setUserpwd(String userpwd)
    {
        this.userpwd = userpwd;
    }

    public String getUsersex()
    {
        return usersex;
    }

    public void setUsersex(String usersex)
    {
        this.usersex = usersex;
    }

    public String getPhonenumber()
    {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    public String getQqnumber()
    {
        return qqnumber;
    }

    public void setQqnumber(String qqnumber)
    {
        this.qqnumber = qqnumber;
    }

    public Users(int userid, String username, String userpwd, String usersex, String phonenumber, String qqnumber)
    {
        this.userid = userid;
        this.username = username;
        this.userpwd = userpwd;
        this.usersex = usersex;
        this.phonenumber = phonenumber;
        this.qqnumber = qqnumber;
    }
}
