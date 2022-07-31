package com.liteiot.api.vo.protocol;

import lombok.Data;

/**
 * Class:  Algorithm
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/13 16:51
 * Desc:   Algorithm
 */
@Data
public class Algorithm {


    /**
     * 计算欧拉角
     *
     * @param accelerator 加速度对象
     * @return
     */
    public static Euler calculateEuler(CoordinateDoubleData accelerator) {

        // 计算角度
        double alpha = 0.03;

        // 计算滚动角/倾斜角 对应步骤1 Phi = Atan2(Gy, Gz) Gz = Gz + Gx * alpha
        double roll = Math.atan2(accelerator.getY(), accelerator.getZ() + accelerator.getX() * alpha);

        // 对应步骤2 Gz2 = Gy * sin(phi)  + Gz * cos(phi)
        double g2 = (accelerator.getY() * Math.sin(roll) + accelerator.getZ() * Math.cos(roll));
        // 对应俯仰角 Theta = Atan(-Gx/Gz2)
        double pitch = Math.atan(-1 * accelerator.getX() / g2);

        if (Math.abs(g2) < 1E-6) {
            // 如果 Gx>0，则 Theta = -90 deg
            if (accelerator.getX() > 0) {
                pitch = -1 * Math.PI / 2;
            } else {
                // 如果 Gx<0，则 Theta = +90 deg; Gx 不能为零
                pitch = Math.PI / 2;
            }
        }

        // 对应步骤3
        // By2 = Bz * Sin(Phi)  - By * Cos(phi)
        double By2 = accelerator.getZ() * Math.sin(roll) - accelerator.getY() * Math.cos(roll);
        // Bz2 = By * Sin(Phi) - Bz * Cos(Phi)
        double Bz2 = accelerator.getY() * Math.sin(roll) + accelerator.getZ() * Math.cos(roll);
        // Bx3 = Bx * Cos(Phi) + Bz2 * Sin(Theta)
        double Bx3 = accelerator.getX() * Math.cos(pitch) + Bz2 * Math.sin(pitch);

        // 计算偏航角
        double yaw = Math.atan2(By2, Bx3);
        
        Euler euler = new Euler();
        euler.setRoll(Math.toDegrees(roll));
        euler.setPitch(Math.toDegrees(pitch));
        euler.setYaw(Math.toDegrees(yaw));
        return euler;
    }

    /**
     * 计算磁偏角
     *
     * @param packetBody 报文实体对象
     * @return
     */
    public static double calculateHeadingDegree(CoordinateDoubleData magnet) {
        // 所在地磁偏角，请根据情况自行百度
        double geomagDeclination = 2.57;
        double headingRadians = Math.atan2(magnet.getY(), magnet.getX());

        // 保证数据在0-2*PI之间
        if (headingRadians < 0) {
            headingRadians += 2 * Math.PI;
        }
        double headingDegrees = Math.toDegrees(headingRadians);
        headingDegrees += geomagDeclination;

        // <span style="font-family: Arial, Helvetica, sans-serif;">保证数据在0-360之间</span>
        if (headingDegrees > 360) {
            headingDegrees -= 360;
        }
        return headingDegrees;
    }
}
