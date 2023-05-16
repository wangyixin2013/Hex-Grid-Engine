package org.example;

public class Hex {
    private cord cord;
    boolean wall;
    public Hex(int i, int j){
        this.cord=new cord(i,j);
    }
    public Hex(cord cord){
        this.cord=cord;
    }

    public boolean equals(Object hex1){
        Hex hex=(Hex)hex1;
        if(this.cord.getI()==hex.getCord().getI()&&this.cord.getJ()==hex.getCord().getJ()){
            return true;
        }
        return false;
    }

    public org.example.cord getCord() {
        return cord;
    }

    public void setCord(org.example.cord cord) {
        this.cord = cord;
    }

    @Override
    public String toString() {
        return cord.getI()+" "+cord.getJ();
    }
}
