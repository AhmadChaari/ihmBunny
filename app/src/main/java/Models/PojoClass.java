package Models;

/**
 * Created by kuldeep on 23/01/18.
 */

public class PojoClass {

    private String text_name,text_location,text_menu1,text_menu2,text_menu3,text_review;
    private int image, image_location,indicate;

    public String getText_name() {
        return text_name;
    }

    public void setText_name(String text_name) {
        this.text_name = text_name;
    }

    public String getText_location() {
        return text_location;
    }

    public void setText_location(String text_location) {
        this.text_location = text_location;
    }

    public String getText_menu1() {
        return text_menu1;
    }

    public void setText_menu1(String text_menu1) {
        this.text_menu1 = text_menu1;
    }

    public String getText_menu2() {
        return text_menu2;
    }

    public void setText_menu2(String text_menu2) {
        this.text_menu2 = text_menu2;
    }

    public String getText_menu3() {
        return text_menu3;
    }

    public void setText_menu3(String text_menu3) {
        this.text_menu3 = text_menu3;
    }

    public String getText_review() {
        return text_review;
    }

    public void setText_review(String text_review) {
        this.text_review = text_review;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage_location() {
        return image_location;
    }

    public void setImage_location(int image_location) {
        this.image_location = image_location;
    }

    public int getIndicate() { return indicate;}

    public void setIndicate(int indicate) { this.indicate = indicate; }
}