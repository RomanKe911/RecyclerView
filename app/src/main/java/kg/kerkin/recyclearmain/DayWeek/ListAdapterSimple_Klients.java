package kg.kerkin.recyclearmain.DayWeek;

/**
 * Created by user on 03.02.2016.
 */
public class ListAdapterSimple_Klients {
    public String uid;
    public String name;
    public String adress;
    public String userUID;
    public String roadUID;
    public String roadName;
    public String clintINN;
    public Integer image;
    public String dayWeek;
    public Integer debet;


    public ListAdapterSimple_Klients(String name, String uid, String adress, String userUID, String dayWeek) {
        this.name = name;
        this.clintINN = clintINN;
        this.uid = uid;
        this.adress = adress;
        this.userUID = userUID;
        this.roadUID = roadUID;
        this.roadName = roadName;
        this.image = image;
        this.dayWeek = dayWeek;
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


    public String getAdress() {
        return this.adress;
    }


    public String getClintINN() {
        return this.clintINN;
    }

    public String getDayWeek() {
        return this.dayWeek;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


    public String getUserUID() {
        return this.userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }


    public String getRoadUID() {
        return this.roadUID;
    }

    public void setRoadUID(String roadUID) {
        this.roadUID = roadUID;
    }


    public String getRoadName() {
        return this.roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public Integer getImage() {
        return this.image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }


    private boolean isSelected;
    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }


}
