package com.aster.justbuildmystronghold.base.math;


public class EulerAngles {
    public double pitch;
    public double yaw;
    public double roll;

    public EulerAngles(double pitch, double yaw, double roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public EulerAngles(double w, double x, double y, double z) {
        // roll (x-axis rotation)
        double sinr_cosp = 2 * (w * x + y * z);
        double cosr_cosp = 1 - 2 * (x * x + y * y);
        this.roll = (double) Math.atan2(sinr_cosp, cosr_cosp);

        // pitch (y-axis rotation)
        double sinp = 2 * (w * y - z * x);
        if (Math.abs(sinp) >= 1) {
            this.pitch = Math.copySign(1.57075f, sinp); // use 90 degrees if out of range
        } else {
            this.pitch = (double) Math.asin(sinp);
        }

        // yaw (z-axis rotation)
        double siny_cosp = 2 * (w * z + x * y);
        double cosy_cosp = 1 - 2 * (y * y + z * z);
        this.yaw = (double) Math.atan2(siny_cosp, cosy_cosp);
    }

    public Quaternion ToQuaternion() {
        //欧拉角转四元数，角度减半是因为四元数旋转计算时需要旋转两次，具体原理请查看四元数原理
        double cy = (double) Math.cos(yaw * 0.5f);
        double sy = (double) Math.sin(yaw * 0.5f);
        double cp = (double) Math.cos(pitch * 0.5f);
        double sp = (double) Math.sin(pitch * 0.5f);
        double cr = (double) Math.cos(roll * 0.5f);
        double sr = (double) Math.sin(roll * 0.5f);
        Quaternion q = new Quaternion();
        q.w = cy * cp * cr + sy * sp * sr;
        q.x = cy * cp * sr - sy * sp * cr;
        q.y = sy * cp * sr + cy * sp * cr;
        q.z = sy * cp * cr - cy * sp * sr;
        return q;
    }

    @Override
    public String toString() {
        return "EulerAngles{" +
                "pitch=" + pitch +
                ", yaw=" + yaw +
                ", roll=" + roll +
                '}';
    }
}
