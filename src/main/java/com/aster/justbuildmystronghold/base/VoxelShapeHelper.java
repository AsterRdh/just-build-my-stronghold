package com.aster.justbuildmystronghold.base;


import com.aster.justbuildmystronghold.base.math.Quaternion;
import com.aster.justbuildmystronghold.base.math.Vec2D;
import com.aster.justbuildmystronghold.base.math.Vec3D;
import com.aster.justbuildmystronghold.base.math.Vec4D;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

public class VoxelShapeHelper{
    private Vec3D startPoint;
    private Vec3D endPoint;
    private Vec3D rotateCenter;

    public VoxelShapeHelper() {
    }
    public VoxelShapeHelper(double x1,double y1,double z1,double x2,double y2,double z2) {
        this.startPoint = new Vec3D(x1,y1,z1);
        this.endPoint = new Vec3D(x2,y2,z2);
        this.rotateCenter=new Vec3D(8,8,8);
    }


    public VoxelShapeHelper(Vec3D startPoint, Vec3D endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.rotateCenter=new Vec3D(8,8,8);
    }

    public VoxelShapeHelper(Vec3D startPoint, Vec3D endPoint, Vec3D rotateCenter) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.rotateCenter = rotateCenter;
    }

    public VoxelShape rotate (Vec3D startPoint, Vec3D endPoint, Vec3D rotateCenter, Quaternion rotation){

        Vec3D startPoint1 = rotation(startPoint, rotateCenter, new Quaternion(rotation.w, rotation.x, rotation.y,rotation.z) );
        Vec3D endPoint1 = rotation(endPoint, rotateCenter, new Quaternion(rotation.w, rotation.x, rotation.y,rotation.z));
        startPoint1.about();
        endPoint1.about();
        if (startPoint1.x > endPoint1.x){
            double x = startPoint1.x;
            startPoint1.x = endPoint1.x;
            endPoint1.x = x;
        }

        if (startPoint1.y > endPoint1.y){
            double y = startPoint1.y;
            startPoint1.y = endPoint1.y;
            endPoint1.y = y;
        }

        if (startPoint1.z > endPoint1.z){
            double z = startPoint1.z;
            startPoint1.z = endPoint1.z;
            endPoint1.z = z;
        }

        return Block.box(startPoint1.x, startPoint1.y, startPoint1.z, endPoint1.x, endPoint1.y, endPoint1.z);
    }

    public VoxelShape rotate (Vec4D vec4D){
        double sit = Math.toRadians(vec4D.w) / 2;
        Quaternion quaternion=new Quaternion(Math.cos(sit),vec4D.x*Math.sin(sit),vec4D.y*Math.sin(sit),vec4D.z*Math.sin(sit));
        return rotate(startPoint,endPoint,rotateCenter,quaternion);
    }

    public VoxelShape rotate (double w,double x,double y,double z){
        double sit = Math.toRadians(w) / 2;
        Quaternion quaternion=new Quaternion(Math.cos(sit),x*Math.sin(sit),y*Math.sin(sit),z*Math.sin(sit));
        return rotate(startPoint,endPoint,rotateCenter,quaternion);
    }

    public VoxelShape getShape(){
        startPoint.about();
        endPoint.about();
        if (startPoint.x > endPoint.x){
            double x = startPoint.x;
            startPoint.x = endPoint.x;
            endPoint.x = x;
        }

        if (startPoint.y > endPoint.y){
            double y = startPoint.y;
            startPoint.y = endPoint.y;
            endPoint.y = y;
        }

        if (startPoint.z > endPoint.z){
            double z = startPoint.z;
            startPoint.z = endPoint.z;
            endPoint.z = z;
        }

        return Block.box(startPoint.x, startPoint.y, startPoint.z, endPoint.x, endPoint.y, endPoint.z);
    }
//new Quaternion(90,0,0,1)
    private Vec3D rotation(Vec3D point, Vec3D origin, Quaternion rotation) {
        Vec3D temp=point.sub(origin);
        double[] doubles = {temp.x, temp.y, temp.z};
        Quaternion.VectorRotation(doubles,rotation);
        return new Vec3D(doubles[0]+origin.x,doubles[1]+origin.y,doubles[2]+origin.z);
    }



}