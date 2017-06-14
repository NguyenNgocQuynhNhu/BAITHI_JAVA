package customlistviewcustomlistview.cheng.com.baithuchanhso3;

/**
 * Created by chien on 10/25/16.
 */

public class Contact {
    private boolean isMale;
    private String mName;
    private String mNumber;
    private String mdate;
    private String email;

    public Contact(boolean isMale, String mName, String mNumber,String mdate,String email) {
        this.isMale = isMale;
        this.mName = mName;
        this.mNumber = mNumber;
        this.mdate = mdate;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}
