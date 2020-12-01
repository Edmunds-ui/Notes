package lv.edmunds.Notes;

import java.io.Serializable;

public class Note implements Serializable {
    private static final long serialVersionUID = -558553967080513790L;

    private String message;
    private String alarmDate;
    private int deleteIconVisibility;


    public Note(){
        //deleteIconVisibility = View.GONE;
    }

    public String getMessage(){
        return message;
    }

    public String getAlarmDate(){
        return alarmDate;
    }

    public int getDeleteIconVisibility() {
        return deleteIconVisibility;
    }


    public void setDeleteIconVisibility(int deleteIconVisibility) {
        this.deleteIconVisibility = deleteIconVisibility;
    }
    public void setMessage (String m){
        message = m;
    }
    public void setAlarmDate (String ad) { alarmDate = ad; }

}
