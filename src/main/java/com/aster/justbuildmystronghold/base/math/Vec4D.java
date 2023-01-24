package com.aster.justbuildmystronghold.base.math;

import java.math.BigDecimal;

public class Vec4D {
    public double w;
    public double x;
    public double y;
    public double z;

    public void about(){
        this.w=new BigDecimal(x).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        this.x=new BigDecimal(x).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        this.y=new BigDecimal(y).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        this.z=new BigDecimal(z).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public Vec4D(Vec4D vec4D) {
        this.w = vec4D.w;
        this.x = vec4D.x;
        this.y = vec4D.y;
        this.z = vec4D.z;
    }
    public Vec4D sub (Vec4D vec3D) {
        return new Vec4D(this.w-vec3D.w,this.x-vec3D.x,this.y-vec3D.y,this.z- vec3D.z);
    }
    public Vec4D add (Vec4D vec3D) {
        return new Vec4D(this.w+vec3D.w,this.x+vec3D.x,this.y+vec3D.y,this.z+ vec3D.z);
    }

    public Vec4D(double w,double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public String toString() {
        return "["+w+","+x+","+y+","+z+"]";
    }
}
