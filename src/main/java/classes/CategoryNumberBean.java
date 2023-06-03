package classes;



public class CategoryNumberBean {


    private int category;
    private int count = 0;

    private String categoryName = "a";

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategory(int category) {

        if(category == 1){
            this.categoryName = "Network";
        }
        else if(category == 2){
            this.categoryName = "Software";
        }
        else if(category == 3){
            this.categoryName = "Hardware";
        }
        else if(category == 4){
            this.categoryName = "Email";
        }
        else{
            categoryName = "Account";
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
