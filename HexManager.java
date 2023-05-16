package org.example;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class HexManager {

    static int radius;
    private Hashtable<cord,Hex> grid=new Hashtable<>();
    private int r;
    cord i=new cord(1,0);
    cord j=new cord(0,1);
    cord k=new cord(1,-1);
    cord ni=new cord(-1,0);
    cord nj=new cord(0,-1);
    cord nk=new cord(-1,1);
/*
generates hex at most r steps away from center
 */
    public HexManager(int r) {
        radius = r;

        for (int w = 0; w <= r; w++) {
            //wasted O(3^n) code
            cord[] list = {i, nj, k};
//            cordPerm(list, w, 0, 0, grid);
            cord[] list2 = {ni, j, nk};
//            cordPerm(list2, w, 0, 0, grid);
            cord[] list3 = {i, j};
//            cordPerm(list3, w, 0, 0, grid);
            cord[] list4 = {ni, nj};
//            cordPerm(list4, w, 0, 0, grid);
            perm1(i,nj,k,w);
            perm1(ni,j,nk,w);
            perm2(i,j,w);
            perm2(ni,nj,w);
        }
    }

    public void perm1(cord c1, cord c2, cord c3, int r){
        for(int i=0;i<=r;i++) {
            for (int j = i; j <= r; j++) {
                int n1 = i;
                int n2 = j - i;
                int n3 = r - j;
                int x = n1 * c1.getI() + n2 * c2.getI() + n3 * c3.getI();
                int y = n1 * c1.getJ() + n2 * c2.getJ() + n3 * c3.getJ();
                cord cord = new cord(x, y);
                if (!grid.containsKey(cord)) {
//                       System.out.println(cord.getI()+" "+cord.getJ());
                    grid.put(cord, new Hex(cord));
                }
            }
        }
    }
    public void perm2(cord c1, cord c2, int r){
        for (int j = 0; j <= r; j++) {
            int n1 = j;
            int n2 = r - j;
            int x = n1 * c1.getI() + n2 * c2.getI();
            int y = n1 * c1.getJ() + n2 * c2.getJ();
            cord cord = new cord(x, y);
            if (!grid.containsKey(cord)) {
//                   System.out.println(cord.getI()+" "+cord.getJ());
                grid.put(cord, new Hex(cord));
            }
        }
    }

    /*
    line drawing
    input two cord, return all hex that the line passes
    works relatively accurate, approximate the line from both x and y perspective
     */
    public Hashtable<cord,Hex> drawLine(cord cord1, cord cord2){
        double slope=(double)(cord2.getJ()-cord1.getJ())/(cord2.getI()-cord1.getI());
        double b=cord2.getJ()-slope*cord2.getI();
        Hashtable<cord,Hex> re=new Hashtable<>();
        int imin=Math.min(cord1.getI(),cord2.getI());
        int imax=Math.max(cord1.getI(),cord2.getI());
        for(int i=imin;i<=imax;i++){
            int j=(int)Math.round(slope*i+b);
            cord temp=new cord(i,j);
            if(!re.containsKey(temp))
                System.out.println(temp);
                re.put(temp,grid.get(temp));
        }
        int jmin=Math.min(cord1.getJ(),cord2.getJ());
        int jmax=Math.max(cord1.getJ(),cord2.getJ());
        for(int j=jmin;j<=jmax;j++){
            int i=(int)Math.round((j-b)/slope);
            cord temp=new cord(i,j);
            if(!re.containsKey(temp))
                System.out.println(temp);
                re.put(temp,grid.get(temp));
        }
        return re;
    }
    /*
    same as previous function, except it return an arraylist that contains hex from cord1 to cord2 in order
     */
    public ArrayList<Hex> drawLineWithDirection(cord cord1, cord cord2){
        double slope=(double)(cord2.getJ()-cord1.getJ())/(cord2.getI()-cord1.getI());
        double b=cord2.getJ()-slope*cord2.getI();
        ArrayList <Hex> re=new ArrayList<>();
        if(Math.abs(cord1.getI()- cord2.getI())> Math.abs(cord1.getJ()- cord2.getJ())){//check point on x
            if(cord1.getI()> cord2.getI()){
                for(int i= cord1.getI();i>= cord2.getI();i--){
                    int j=(int)Math.round(slope*i+b);
                    cord temp=new cord(i,j);
                    re.add(grid.get(temp));
                }
            }
            else{
                for(int i= cord1.getI();i<= cord2.getI();i++){
                    int j=(int)Math.round(slope*i+b);
                    cord temp=new cord(i,j);
                    re.add(grid.get(temp));
                }
            }
        }
        else{//check points on y
            if(cord1.getJ()> cord2.getJ()){
                for(int j= cord1.getJ();j>= cord2.getJ();j--){
                    int i=(int)Math.round((j-b)/slope);
                    cord temp=new cord(i,j);
                    re.add(grid.get(temp));
                }
            }
            else{
                for(int j= cord1.getJ();j<= cord2.getJ();j++){
                    int i=(int)Math.round((j-b)/slope);
                    cord temp=new cord(i,j);
                    re.add(grid.get(temp));
                }
            }
        }
        return re;
    }

    //this is a fucking bullshit O(3^n) method that some idiot wrote
    public static void cordPerm(cord[] list, int i, int x, int y,Hashtable<cord,Hex> grid){
        if (i == 0){
            cord cord = new cord(x,y);
            if(!grid.containsKey(cord)) {
             //   System.out.println(cord.getI()+" "+cord.getJ());
                grid.put(cord,new Hex(cord));
            }
        }
        else{
            for(int j=0;j<list.length;j++){
                cordPerm(list,i-1,x+list[j].getI(),y+list[j].getJ(),grid);
            }
        }
    }
    static int factorial(int n){
        if (n == 0)
            return 1;
        else
            return(n * factorial(n-1));
    }
    public Hashtable<cord, Hex> getGrid() {
        return grid;
    }

    public void setGrid(Hashtable<cord, Hex> grid) {
        this.grid = grid;
    }

}
