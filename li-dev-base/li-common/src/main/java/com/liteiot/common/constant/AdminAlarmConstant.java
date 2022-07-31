package com.liteiot.common.constant;

public class AdminAlarmConstant {

    public static final String FALL = "fall";
    public static final String SWING = "swing";
    public static final String LEAN = "lean";
    public static final String SPIN = "spin";
    public static final String DISTANCE = "distance";
    public static final String TIMEOUT = "timeout";
    public static final String FREQUENCY = "frequency";


    public static final int FALL_WEIGHT = 55;
    public static final int LEAN_WEIGHT = 25;
    public static final int SPIN_WEIGHT = 15;
    public static final int SWING_WEIGHT = 5;


    // 注意前后的空格
    public static final String FALL_DESC = " 跌落 ";

    public static final String LEAN_DESC = " 倾斜%s度 ";

    public static final String SPIN_DESC = " 旋转%s度 ";

    public static final String SWING_DESC = " 摇摆 ";

    public static final String DISTANCE_DESC = " 存在位移 ";

}
