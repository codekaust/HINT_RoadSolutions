package io.github.codekaust.cityprototype;

public class Path {

    public int weightTotal;
    public int weights[];
    public Intersection pathMembers[];
    int i, j;

    Path(int pathNumberOfMembers) {
        pathMembers = new Intersection[pathNumberOfMembers];
        for (int i=0;i<pathNumberOfMembers;i++){
            pathMembers[i] = new Intersection(0,0);
        }
        weights = new int[pathNumberOfMembers];

        this.i = i;
        this.j = j;
    }

    public int weightCalculator() {
        int weightTotal = 0;
        weights[0] = 0;
        for (int i = 1; i < weights.length; i++) {
            if (pathMembers[i].i < pathMembers[i - 1].i) {
                weights[1] = pathMembers[i].c2 + pathMembers[i].c3 + pathMembers[i].c4;
            }
            if (pathMembers[i].i > pathMembers[i - 1].i) {
                weights[1] = pathMembers[i].c1 + pathMembers[i].c3 + pathMembers[i].c4;
            }
            if (pathMembers[i].j < pathMembers[i - 1].j) {
                weights[1] = pathMembers[i].c2 + pathMembers[i].c1 + pathMembers[i].c4;
            }

            if (pathMembers[i].j > pathMembers[i - 1].j) {
                weights[1] = pathMembers[i].c2 + pathMembers[i].c1 + pathMembers[i].c3;
            }

            weightTotal+=weights[i];
        }
        return weightTotal;
    }
}
