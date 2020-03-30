import java.util.List;

public class Route {
    private List<Arrete> arretes;

    public Route(List<Arrete> arretes) {
        this.arretes = arretes;
    }

    public List<Arrete> getArretes() {
        return arretes;
    }

    public void setArretes(List<Arrete> arretes) {
        this.arretes = arretes;
    }

    public void addArrete(Arrete arrete){
        arretes.add(arrete);
    }

    public double getDistanceTotal(){
       return arretes.stream().mapToDouble(Arrete::getDistance).sum();
    }

    @Override
    public String toString() {
        return "Route{" +
                "arretes=" + arretes +
                '}';
    }
}
