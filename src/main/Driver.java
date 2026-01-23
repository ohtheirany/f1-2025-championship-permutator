public class Driver {
    private String name;
    private int points = 0;

        /*
    public Driver(String name, int points){
        this.name = name;
        this.points += points;
    }
         */

    public Driver(String name){
        this.name = name;
    }

    //getters
    public String getName(){
        return this.name;
    }

    //setters
    public void setName(String name){
        this.name = name;
    }

    public void addPoints(int points){
        this.points += points;
    }

    //others
    public boolean morePointsThan(Driver other){
        if (this.points > other.points){
            return true;
        }
        return false;
    }
}
