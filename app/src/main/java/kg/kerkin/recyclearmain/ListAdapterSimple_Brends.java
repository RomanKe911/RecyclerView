package kg.kerkin.recyclearmain;

/**
 * Created by user on 03.02.2016.
 */
public class ListAdapterSimple_Brends {
    public String brend;
    public String prefix;



    public ListAdapterSimple_Brends(String brend, String prefix) {
        this.brend = brend;
        this.prefix = prefix;
    }

    public String getBrend() {
        return this.brend;
    }

    public void setBrend(String brend) {
        this.brend = brend;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }


    private boolean isSelected;
    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
