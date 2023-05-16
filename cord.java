package org.example;

import static org.example.HexManager.radius;

public class cord {
    private int j;
    private int i;
    public cord(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
    @Override
    public int hashCode() {
        int I=Math.abs(this.getI());
        int J=Math.abs(this.getJ());
            return I*(int)Math.pow(10,(int)Math.log10(radius/2)+21)+J;
    }
    public boolean equals(Object cord){
        cord a=(cord) cord;
        if(this.i==a.getI()&&this.j==a.getJ()){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getI()+" "+this.getJ();
    }
}
