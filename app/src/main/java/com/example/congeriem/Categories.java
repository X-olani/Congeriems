package com.example.congeriem;

public class Categories {

    private String ID;
        private String Category;
        private int Goal;

        public Categories(){

        }
        public Categories( String id, String category, int goal) {

            this.ID=id;
          this.  Category = category;
           this.Goal=goal;

        }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setGoal(int goal) {
        Goal = goal;
    }

    public String getCategory() {
            return Category;
        }

        public  int getGoal(){return Goal;}

    public String getID() {
        return ID;
    }


}


