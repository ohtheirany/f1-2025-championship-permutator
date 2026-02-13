public class SimDriver {
    private Driver driver;
    private int position;
    private int simulatedPoints;
    private int realPoints;

    public SimDriver(Driver driver){
        this.driver = driver;
        this.realPoints = driver.getPoints();
    }

    public SimDriver(Driver driver, int position){
        this.driver = driver;
        this.position = position;
        this.realPoints = driver.getPoints();

        setPointsForPos(position);
    }

    public void setPosition(int position){
        this.position = position;
        setPointsForPos(position);
    }

    public void setPointsForPos(int position){
        this.simulatedPoints = realPoints + RaceProcessor.awardPoints(position);
    }

    public Driver getDriver(){
        return this.driver;
    }

    public int getPosition(){
        return this.position;
    }

    public int getPoints(){
        return this.simulatedPoints;
    }
}
