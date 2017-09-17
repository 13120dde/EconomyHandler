package a13solutions.myEco.model;

/**
 * Created by 13120dde on 2017-09-16.
 */

public class ChildInfo {
    private String name = "";
    private int position;

    public ChildInfo(String name, int position) {
        this.name = name;
        this.position=position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
