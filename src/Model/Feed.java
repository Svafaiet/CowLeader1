package Model;

public class Feed {
    private String name;
    private int loveDegree;
    private int weightIncrease;
    private int milkIncrease;

    public Feed(String name, int loveDegree, int weightIncrease, int milkIncrease) {
        this.name = name;
        this.loveDegree = loveDegree;
        this.weightIncrease = weightIncrease;
        this.milkIncrease = milkIncrease;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof String) {
            return name.equals(obj);
        }
        if (obj instanceof Feed) {
            return name.equals(((Feed) obj).name);
        }
        return super.equals(obj);
    }

    public String getName() {
        return name;
    }

    public int getLoveDegree() {
        return loveDegree;
    }

    public int getWeightIncrease() {
        return weightIncrease;
    }

    public int getMilkIncrease() {
        return milkIncrease;
    }

}
