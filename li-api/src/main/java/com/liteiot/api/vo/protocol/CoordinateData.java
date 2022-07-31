package com.liteiot.api.vo.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class:  CoordinateData
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/8 14:14
 * Desc:   坐标轴数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinateData {

    private int x;

    private int y;

    private int z;
}
