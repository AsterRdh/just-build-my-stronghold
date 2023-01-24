package com.aster.justbuildmystronghold.base.math;

import java.math.BigDecimal;

public class Vec3D {
    public double x;
    public double y;
    public double z;

    public void about(){
        this.x=new BigDecimal(x).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        this.y=new BigDecimal(y).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        this.z=new BigDecimal(z).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public Vec3D(Vec3D vec3D) {
        this.x = vec3D.x;
        this.y = vec3D.y;
        this.z = vec3D.z;
    }
    public Vec3D sub (Vec3D vec3D) {
        return new Vec3D(this.x-vec3D.x,this.y-vec3D.y,this.z- vec3D.z);
    }
    public Vec3D add (Vec3D vec3D) {
        return new Vec3D(this.x+vec3D.x,this.y+vec3D.y,this.z+ vec3D.z);
    }

    public Vec3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public String toString() {
        return "["+x+","+y+","+z+"]";
    }
}
