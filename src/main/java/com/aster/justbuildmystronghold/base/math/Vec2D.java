package com.aster.justbuildmystronghold.base.math;

import java.math.BigDecimal;

public class Vec2D {
    public double x;
    public double y;
    public void about(){
        this.x=new BigDecimal(x).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        this.y=new BigDecimal(y).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    @Override
    public String toString() {
        return "["+x+","+y+"]";
    }
    public Vec2D(double x,double y) {
        this.x=x;
        this.y=y;
    }
}
