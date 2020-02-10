package com.czxy.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Auther: 传智新星
 * @Date: 2019/9/13 20:15
 * @Description:
 */
@Entity
@Table(name = "l_emailreceive")
public class Email {


    @Id
    private Integer receiveemailid;         //接受邮件主键
    private String receiveemailcontent;     //接受邮件内容
    private Integer receiveemailstatus;     //接受邮件状态
    private Date receiveemaildate;        //接受邮件时间
    private String receiveusermail;       //发送邮件用户邮箱
    private Integer receiveuserid;        //接受邮件用户id

    private User user;

    @Override
    public String toString() {
        return "Email{" +
                "receiveemailid=" + receiveemailid +
                ", receiveemailcontent='" + receiveemailcontent + '\'' +
                ", receiveemailstatus=" + receiveemailstatus +
                ", receiveemaildate=" + receiveemaildate +
                ", receiveusermail='" + receiveusermail + '\'' +
                ", receiveuserid=" + receiveuserid +
                ", user=" + user +
                '}';
    }

    public Integer getReceiveemailid() {
        return receiveemailid;
    }

    public void setReceiveemailid(Integer receiveemailid) {
        this.receiveemailid = receiveemailid;
    }

    public String getReceiveemailcontent() {
        return receiveemailcontent;
    }

    public void setReceiveemailcontent(String receiveemailcontent) {
        this.receiveemailcontent = receiveemailcontent;
    }

    public Integer getReceiveemailstatus() {
        return receiveemailstatus;
    }

    public void setReceiveemailstatus(Integer receiveemailstatus) {
        this.receiveemailstatus = receiveemailstatus;
    }

    public Date getReceiveemaildate() {
        return receiveemaildate;
    }

    public void setReceiveemaildate(Date receiveemaildate) {
        this.receiveemaildate = receiveemaildate;
    }

    public String getReceiveusermail() {
        return receiveusermail;
    }

    public void setReceiveusermail(String receiveusermail) {
        this.receiveusermail = receiveusermail;
    }

    public Integer getReceiveuserid() {
        return receiveuserid;
    }

    public void setReceiveuserid(Integer receiveuserid) {
        this.receiveuserid = receiveuserid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Email() {
    }

    public Email(Integer receiveemailid, String receiveemailcontent, Integer receiveemailstatus, Date receiveemaildate, String receiveusermail, Integer receiveuserid, User user) {
        this.receiveemailid = receiveemailid;
        this.receiveemailcontent = receiveemailcontent;
        this.receiveemailstatus = receiveemailstatus;
        this.receiveemaildate = receiveemaildate;
        this.receiveusermail = receiveusermail;
        this.receiveuserid = receiveuserid;
        this.user = user;
    }
}
