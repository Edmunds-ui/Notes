package lv.edmunds.Notes;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AllNotes {
    //Saņemt Nore: AllNotes.getInstance().getArray().get(pozicijas nr)
    //Pievienot Note: AllNotes.getInstance().addToArray()
    //Saglabat izmaiņas: AllNotes.getInstance().saveToInternalStorage()

    private static AllNotes mInstance;
    private ArrayList<Note> list = new ArrayList<Note>();

    public static AllNotes getInstance() {
        if(mInstance == null)
            mInstance = new AllNotes();

        return mInstance;
    }
    private AllNotes() {
        list = new ArrayList<Note>();
    }
    // izgūt masīvu
    public ArrayList<Note> getArray() {
        return this.list;
    }
    //Pievienot elementu masīvam
    public void addToArray(Note value) {
        list.add(value);
    }

    public void overide(ArrayList<Note> temp){
        list = temp;
    }
    //Paslept delete ikonu
    public void removeAllDeleteIcons(){
        for(int i = 0; i < list.size(); i++){
            list.get(i).setDeleteIconVisibility(View.GONE);
        }
    }
    //Ierakstam datus no internalStorage
    public void saveToInternalStorage(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput("all", Context.MODE_PRIVATE);
            ObjectOutputStream of = new ObjectOutputStream(fos);
            of.writeObject(list);
            of.flush();
            of.close();
            fos.close();
        } catch (Exception e) {
            Log.e("InternalStorage", e.getMessage());
        }
    }

}
