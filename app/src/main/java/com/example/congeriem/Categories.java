package com.example.congeriem;

public class Categories {

    private int ID;
        private String Category;
        private int Goal;

        public Categories( int id, String category, int goal) {

            ID=id;
            Category = category;
            Goal=goal;

        }




        public String getCategory() {
            return Category;
        }

        public  int getGoal(){return Goal;}

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return Category ;

    }
}


