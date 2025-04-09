package dev.mvc.ajax;

public class FoodVO {
    private int foodno;
    private String name;

    public FoodVO(int foodno, String name) {
        this.foodno = foodno;
        this.name = name;
    }
    public int getFoodno() {
        return foodno;
    }
    public void setFoodno(int foodno) {
        this.foodno = foodno;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
