package kg.kerkin.recyclearmain;

/**
 * Created by user on 03.02.2016.
 */
public class ListAdapterSimple_Agents {
    public String uid;
    public String name;


    public ListAdapterSimple_Agents(String name, String uid) {
        this.name = name;
        this.uid = uid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUID() {
        return this.uid;
    }

    public void setUID(String uid) {
        this.uid = uid;
    }



}
