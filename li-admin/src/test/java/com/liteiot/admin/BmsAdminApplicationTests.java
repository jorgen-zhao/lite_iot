package com.liteiot.admin;

import com.alibaba.fastjson.JSON;
import com.liteiot.admin.modules.admin.biz.AlarmThresholdBiz;
import com.liteiot.admin.modules.admin.biz.DeviceExtraConfigBiz;
import com.liteiot.admin.modules.admin.biz.PacketInfoBiz;
import com.liteiot.admin.modules.admin.entity.DeviceExtraConfig;
import com.liteiot.admin.modules.admin.entity.DeviceInfo;
import com.liteiot.admin.modules.admin.handler.SpinAndLeanCalculateHandler;
import com.liteiot.admin.modules.admin.handler.TemperatureRectifyHandler;
import com.liteiot.admin.modules.admin.redis.CommonConfigCache;
import com.liteiot.admin.modules.admin.util.Sha256PasswordEncoder;
import com.liteiot.common.vo.DeviceSpinConfig;
import com.liteiot.common.vo.InitialAngle;
import com.liteiot.common.vo.SimpleLatestDeviceStatus;
import com.liteiot.admin.modules.auth.util.user.JwtTokenUtil;
import com.liteiot.api.vo.protocol.CoordinateData;
import com.liteiot.api.vo.protocol.CoordinateDoubleData;
import com.liteiot.api.vo.protocol.Euler;
import com.liteiot.common.constant.DeviceConstants;
import com.liteiot.common.redis.DeviceCommandCache;
import com.liteiot.common.util.jwt.IJWTInfo;
import com.liteiot.common.util.jwt.RsaKeyHelper;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Stream;

@SpringBootTest
@Slf4j
class BmsAdminApplicationTests {

    @Autowired
    private CommonConfigCache configCache;

    @Autowired
    private DeviceCommandCache deviceCommandCache;

    @Autowired
    private DeviceExtraConfigBiz deviceExtraConfigBiz;

    @Autowired
    @Qualifier("loadBalance")
    private RestTemplate loadBalancedRestTemplate;

    private static RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();

    private Sha256PasswordEncoder encoder = new Sha256PasswordEncoder();

    @Autowired
    private TemperatureRectifyHandler temperatureRectifyHandler;

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    @Autowired
    private PacketInfoBiz packetInfoBiz;

    @Autowired
    private AlarmThresholdBiz alarmThresholdBiz;

    @Autowired
    private LatestDeviceStatusMapper latestDeviceStatusMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SpinAndLeanCalculateHandler spinAndLeanCalculateHandler;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("测试解析token解析")
    void testParseTokenUsingPubkey() throws Exception {
        String token = "Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJJZCI6IjEiLCJuYW1lIjoiYWRtaW4iLCJpZCI6ImVXNFJQenl0IiwiZXhwIjoxNjQ5NTcyMTAwfQ.vtba_wBPM8GT-jiB7_SWgot3wsE4upy6pbLytWl5tufadDQFEQeEzcj_7pAJDske4jNwgTIo577OP2HU23d1v7n93mMFeIDXbC3tzyI-HpehFJAQq_raUqqmKNEZC7K41HO607y-dWrpfgo-S9tZCoN7-0DJK3M0LEElYUGPNSM";
        IJWTInfo infoFromToken = jwtTokenUtil.getInfoFromToken(token);
        log.info("infoFromToken:{}", infoFromToken);
    }

    @Test
    @DisplayName("测试获取设备超时配置数据")
    void testQueryTimeoutConfigDevices() {
        List<String> configTimeoutDevices = alarmThresholdBiz.queryTimeoutConfigDevices();
        log.info("configTimeoutDevices = {}", configTimeoutDevices);
        List<SimpleLatestDeviceStatus> list = latestDeviceStatusMapper.querySimpleDeviceStatus(configTimeoutDevices);
        log.info("list = {}", list);
    }

    @Test
    @DisplayName("测试设备频繁上报")
    void testDeviceFrequentReport() {
        String imei = "0860527058315343";
        packetInfoBiz.asyncUpdateDeviceStatue(imei);
    }

    @Test
    @DisplayName("测试批量更新状态与时间")
    void testUpdateStatusAndTime() {
        List<String> imeiList = Arrays.asList("0860527052653103", "0860527052653582");
        deviceInfoMapper.batchUpdateDeviceStatue(imeiList, 3, new Date());
    }


    @Test
    @DisplayName("测试新增是否返回Id")
    void testInsert() {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setImei("123456789012345");
        deviceInfo.setGroupId(1);
        int insert = deviceInfoMapper.insert(deviceInfo);
        log.info("insert = {}, deviceInfo = {}", insert, deviceInfo);
    }

    @Test
    @DisplayName("测试旋转温漂校正")
    void testTemperatureSpinRectify() {
        String imei = "0860527058314981";
        double temperature = 5.41;
        // x=238, y=54, z=796
        CoordinateData magnet = new CoordinateData(238, 54, 796);
        CoordinateDoubleData spinRectify = temperatureRectifyHandler.spinRectify(imei, temperature, magnet);
        log.info("spinRectify = {}", JSON.toJSONString(spinRectify));
    }

    @Test
    @DisplayName("测试倾斜温漂校正")
    void testTemperatureLeanRectify() {
        String imei = "0860527058314981";
        double temperature = 5.41;
        // x=-2529, y=1091, z=-15388
        CoordinateData accelerator = new CoordinateData(-2529, 1091, -15388);
        CoordinateDoubleData leanRectify = temperatureRectifyHandler.leanRectify(imei, temperature, accelerator);
        log.info("leanRectify = {}", JSON.toJSONString(leanRectify));
    }


    @Test
    @DisplayName("测试温漂校正使用参数")
    void testGetTempRectifyConfig() {
        String imei = "0860527058320285";
        double temperature = 5.41;
        CoordinateData accelerator = new CoordinateData(-175, 409, 517);
        CoordinateDoubleData rectify = temperatureRectifyHandler.spinRectify(imei, temperature, accelerator);
        log.info("rectify = {}", rectify);
    }

    @Test
    @DisplayName("测试获取温漂配置数据")
    void testGetTempConfig() {
        // checkExistTempConfig
        String imei = "0860527058320285";
        int type = DeviceConstants.SPIN_CONFIG;
        boolean existTempConfig = temperatureRectifyHandler.checkExistTempConfig(imei, type);
        log.info("existTempConfig = {}", existTempConfig);
    }


    @Test
    @DisplayName("测试倾斜角")
    void testLean() {
        String imei  = "11111";
        CoordinateDoubleData accelerator = new CoordinateDoubleData(-301, -14284, -7457);
        double leanIndex = spinAndLeanCalculateHandler.calculateInnovativeLeanAngle(imei, accelerator);
    }

    @Test
    @DisplayName("测试获取温漂倾斜配置项")
    void testGetDeviceSpinConfig() {
        DeviceSpinConfig deviceSpinConfig = deviceExtraConfigBiz.getDeviceSpinConfig("0860527058030165");
        Double initX = deviceSpinConfig.getInitX();
        log.info("initX={}, deviceSpinConfig = {}", initX, deviceSpinConfig);
    }

    @Test
    @DisplayName("测试map转换为实体")
    void testMapToEntity() {
        List<DeviceExtraConfig> params = deviceExtraConfigBiz.getFullDeviceTempParams("0860527058030165", 1);
        Map<String, Double> map = new HashMap<>();
        for (DeviceExtraConfig deviceExtraConfig : params) {
            map.put(deviceExtraConfig.getConfigKey(), deviceExtraConfig.getConfigValue());
        }
        // 将 Map 转换为 实体类
        DeviceSpinConfig deviceSpinConfig = JSON.parseObject(JSON.toJSONString(map), DeviceSpinConfig.class);
        log.info("deviceSpinConfig = {}", deviceSpinConfig);
    }

    @Test
    @DisplayName("测试密码加密")
    void testPassword() {
        String password = "SZ1231=ptgly";
        String encode = encoder.encode(password);
        log.info("encode = {}", encode);
    }

    @DisplayName("测试RestTemplate发送消息")
    @Test
    void testRestTemplateSend() {
        String url = "http://convercomm.com/wx/wxToken/verify_wx_token";
        loadBalancedRestTemplate.getForEntity(url, String.class);
    }

    @DisplayName("测试Redis添加非存在key情况")
    @Test
    void testAddNullKeyRedis() {
        String key = "0860527055266312";
        deviceCommandCache.putNewReportMsg(key, "");
        deviceCommandCache.putLatestCommand(key, key);
    }

    @DisplayName("测试字符串包含")
    @Test
    void testStringContains() {
        String str = "CPOTA,0404d100,61068,60,1024,C33AF3DAE70B443B7ED6C500445EB4703B959DD01AC8503CA80EBF6F24BBA89114AF8CBE044845E4A367DD65D3DCD50B4BC717707FBE3B3DF6852A5A,33F1681FC3954621C9AFC6B3F0D97BB8,57";
        log.info("str = {}", str.contains("CPOTA"));
    }

    @DisplayName("测试token生成")
    @Test
    void testToken() throws Exception {
        String token = this.generateToken(1, "groupCode");
        log.info("token: {}", token);
    }

    public String generateToken(int groupId, String groupCode) throws Exception {
        String compactJws = Jwts.builder()
                .setSubject("register_token")
                .claim("GROUP_ID", groupId)
                .claim("GROUP_CODE", groupCode)
                // 与在线日志保持一致 token的过期时间延长
                .setExpiration(DateTime.now().plusMinutes(2 * 60).toDate())
//                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(RsaKeyHelper.toBytes("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM9F9zgDlICamLexJapEGT9AvZRD\n" +
//                        "+6+f8Gxto2hrmbPUBpYlLd4/uVIEV7M9ZsKyQiGrpYVOEou0YOWh875BVvuLeHSFEvln7jO4Bs5J\n" +
//                        "jiIy/W9ivIcDKM8Zfa+5saWvfKuuO3dANjBtO6PzneDzOBnR7kbQuF1yVZkscQ9oWkUjAgMBAAEC\n" +
//                        "gYArXRjmmCEmcrGGpMIbiBm8GQBQl5R9Xrm/BYUNYI2MiMxD901MVQqhdRUgA7WPABpDzxRW+kl4\n" +
//                        "/ujSwforkKHwqAtHeb834L3QBicjvZ9Tkzo0GErt+wOsZhDI3CxogRLD4vL9Xqh6k/53dm0sx6Lo\n" +
//                        "hKFtQOQabRmWupz5Jc3wMQJBAOqtVOaKRL6ycOzPdFeHAK3hvhtdD4P65WLOoQ8XYYwq/qXlt1Z6\n" +
//                        "wxH73mWO520v5AiwAP5GY5rerp+4AtsNyUkCQQDiGzaHPPbB9bYPjK4xrTA48+X1oVVd84B86XF+\n" +
//                        "WNRwLt+nxSeGl1mhgf1dbUwlmfkJ8JWUSEcBdKQ6/HTQ1KcLAkBsJfjsTWgk4aL83xXkiEid2Vx8\n" +
//                        "y8QstGElycebZtEDgYTc+yIkbmqbTRFOiC7KuLlD76hlhha89kZPQMPAI3hRAkBQUhx3xEdgNZoc\n" +
//                        "QfxrdzuHL9VEAbDitCqztPX1TTcCNxSKc7YL0N4tSpEnzDjdrqnSRx3L1DUtJjNlJOOWf8RrAkEA\n" +
//                        "u5juRM2OfYUXXe7p3MjwutiIS+XodK/tl4q/4eQsHj/Fos27MlBCtORYCyNXF/Y0u5hOQuUqf35D\n" +
//                        "K2U/f5ICBg==")))
                .compact();
        return compactJws;
    }

    @DisplayName("测试token解析")
    @Test
    void testParseToken() throws Exception {
//        String token = "eyJhbGciOiJub25lIn0.eyJzdWIiOiJyZWdpc3Rlcl90b2tlbiIsIkdST1VQX0lEIjoxLCJHUk9VUF9DT0RFIjoiZ3JvdXBDb2RlIiwiZXhwIjoxNjM5Mzc0NTMyfQ.";
        String token = "eyJzdWIiOiJyZWdpc3Rlcl90b2tlbiIsIkdST1VQX0lEIjoxLCJHUk9VUF9DT0RFIjoiZ3JvdXBDb2RlIiwiZXhwIjoxNjM5Mzc0NTMyfQ";
        this.getInfoFromToken(token);
    }

    public void getInfoFromToken(String token) throws Exception {
        if (token.startsWith("Bearer")) {
            token = token.replace("Bearer ", "");
        }
//        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKey)).parseClaimsJws(token);
        Jwt parse = Jwts.parser().parse(token);
        log.info("body:{}", parse);
//        return new JWTInfo(body.getSubject(), StringHelper.getObjectValue(body.get("GROUP_ID")), StringHelper.getObjectValue(body.get("GROUP_CODE")));
    }

    @DisplayName("判断Redis序列化key不存在时,对象是否为空")
    @Test
    void judgeRedisCacheObjectIsNull() {
        String imei = "123";
        InitialAngle cachedInitialAngle = configCache.getCachedInitialAngle(imei);
        boolean aNull = Objects.isNull(cachedInitialAngle);
        String imei1 = cachedInitialAngle.getImei();
        log.info("对象是否为空: ", aNull);
        log.info("imei: ", imei1);
        cachedInitialAngle.setImei("789456123");

        String imei2 = "0860527058317406";
        InitialAngle cachedInitialAngle1 = configCache.getCachedInitialAngle(imei);
        cachedInitialAngle1.setRoll("-88888");
    }

    @DisplayName("测试三个轴绝对值最大")
    @Test
    void testXYZAxisAbsMax() {
        CoordinateData accelerator = new CoordinateData(83, -15974, 286);
        String verticalAxis = getVerticalAxis(accelerator);
        log.info("verticalAxis:{}", verticalAxis);
    }

    private String getVerticalAxis(CoordinateData accelerator) {
        // 判断那个轴的绝对值最大
        Map<String, Integer> axisData = new HashMap<>();
        axisData.put("x", Math.abs(accelerator.getX()));
        axisData.put("y", Math.abs(accelerator.getY()));
        axisData.put("z", Math.abs(accelerator.getZ()));
        Collection<Integer> values = axisData.values();
        Integer max = values.stream().max(Comparator.comparingInt(o -> o)).get();
        String verticalAxis = "x";
        for (String key : axisData.keySet()) {
            if (axisData.get(key).equals(max)) {
                verticalAxis = key;
                break;
            }
        }
        return verticalAxis;
    }

    @Test
    @DisplayName("测试int计算double类型数据,精度损失")
    void testDoubleDiff() {
        double a = 1.0;
        int b = 265745;

        double c = 1.0;
        double d = 265745.0;

        double i = a / b;
        double v = c / d;
        double y = a / b;
        log.info("i :{}, v :{}, y : {}", i, v, y);
    }

    @Test
    @DisplayName("测试向量计算")
    void testVectorCalculate() {
        CoordinateDoubleData magVector = new CoordinateDoubleData(-484.0, -498.0, -99.0);
        CoordinateDoubleData v1 = new CoordinateDoubleData(-0.008555486670799752, 1.0, 0.0);
        CoordinateDoubleData v2 = new CoordinateDoubleData(-0.037383756974581524, 0.0, 1.0);
//        CoordinateDoubleData u1 = calculateU(magVector, v1);
        double uv2 = calculateUV2(magVector, v2);
        double length = calculateVectorLength(v2);
        CoordinateDoubleData u2 = calculateU(magVector, v2);
//        log.info("u1: {}, u2:{}", u1, u2);
        log.info("uv2: {}, length : {}, u2: {}", uv2, length, u2);
    }

    /**
     * 计算t1 * u 内积
     *
     * @param t1
     * @param u
     * @return
     */
    private double calculateUV2(CoordinateDoubleData t1, CoordinateDoubleData u) {
        return (t1.getX() * u.getX()) + t1.getY() * u.getY() + t1.getZ() * u.getZ();
    }

    private double calculateVectorLength(CoordinateDoubleData v) {
        return Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2) + Math.pow(v.getZ(), 2);
    }

    private CoordinateDoubleData calculateU(CoordinateDoubleData magVector, CoordinateDoubleData v) {
        double vLength = Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2) + Math.pow(v.getZ(), 2);
        double uTimesV = magVector.getX() * v.getX() + magVector.getY() * v.getY() + magVector.getZ() * v.getZ();
        return new CoordinateDoubleData((uTimesV * v.getX()) / vLength, (uTimesV * v.getY()) / vLength, (uTimesV * v.getZ()) / vLength);
    }

    @Test
    @DisplayName("测试acos计算数据")
    void testacos() {
        int rollDiff = -5;
        int pitchDiff = 10;
        double angle = calculateFinalLeanAngle(rollDiff, pitchDiff);
        log.info("综合倾角计算: {}", angle);
    }


    private double calculateFinalLeanAngle(double rollDiff, double pitchDiff) {
        double roll = Math.toRadians(rollDiff);
        double pith = Math.toRadians(pitchDiff);
        double denominator = Math.pow(Math.tan(roll), 2) + Math.pow(Math.tan(pith), 2) + 1;
        double coscPow = 1 / denominator;
        double cosc = Math.sqrt(coscPow);
        double acos = Math.acos(cosc);
        double degree = Math.toDegrees(acos);
        return degree;
    }

    @Test
    @DisplayName("测试欧拉角计算")
    void testEular() {
        CoordinateData accelerator = new CoordinateData(-15057, -2719, -4423);
        CoordinateData coordinateData = changeAxisIfNecessary(accelerator);
        Euler euler = calculateEuler(coordinateData);


        CoordinateData previousCoodinate = new CoordinateData(-16179, -117, 271);
        CoordinateData previousCoordinateData = changeAxisIfNecessary(previousCoodinate);
        Euler previousEuler = calculateEuler(previousCoordinateData);

        double angle = calculateDiffAngle(euler, previousEuler);
        log.info("原始值:{}, 坐标轴转换后: {}, 计算后欧拉角: {}, 计算后的倾斜角: {}", accelerator, coordinateData, euler, angle);
    }

    @DisplayName("判断科学计数法")
    @Test
    void testSci() {
        double a = 1E-6;
        log.info("a: {}", a);
    }


    private CoordinateData changeAxisIfNecessary(CoordinateData accelerator) {
        String verticalAxis = getVerticalAxis(accelerator);
        CoordinateData coordinate = null;
        switch (verticalAxis) {
            // 若X轴垂直地面: X轴的数据=Gy Y轴的数据=Gz Z轴的数据=Gx
            case "x":
                coordinate = new CoordinateData(accelerator.getY(), accelerator.getZ(), accelerator.getX());
                break;
            // 若Y轴垂直地面: X轴的数据=Gx Y轴的数据=Gz Z轴的数据=Gy
            case "y":
                coordinate = new CoordinateData(accelerator.getX(), accelerator.getZ(), accelerator.getY());
                break;
            // 若Y轴垂直地面: X轴的数据=Gx Y轴的数据=Gy Z轴的数据=Gz
            case "z":
                coordinate = new CoordinateData(accelerator.getX(), accelerator.getY(), accelerator.getZ());
                break;
            default:
                break;
        }
        return coordinate;
    }

    public static Euler calculateEuler(CoordinateData accelerator) {

        // 计算角度
        double alpha = 0.03;

        Euler euler = new Euler();
//        CoordinateData accelerator = po.getAccelerator();


        // 计算滚动角/倾斜角 对应步骤1 Phi = Atan2(Gy, Gz) Gz = Gz + Gx * alpha
        double roll = Math.atan2(accelerator.getY(), accelerator.getZ() + accelerator.getX() * alpha);
//        roll = Math.toDegrees(roll);
//        euler.setRoll(roll);

        // 对应步骤2 Gz2 = Gy * sin(phi)  + Gz * cos(phi)
        double g2 = (accelerator.getY() * Math.sin(roll) + accelerator.getZ() * Math.cos(roll));
        double pitch;
        if (g2 == 0) {
            // 如果 Gx>0，则 Theta = -90 deg
            if (accelerator.getX() > 0) {
                pitch = -1 * Math.PI / 2;
            } else {
                // 如果 Gx<0，则 Theta = +90 deg; Gx 不能为零
                pitch = Math.PI / 2;
            }
        } else {
            // 对应俯仰角 Theta = Atan(-Gx/Gz2)
            pitch = Math.atan(-1 * accelerator.getX() / g2);
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
        yaw = Math.toDegrees(yaw);
        euler.setYaw(Math.toDegrees(yaw));
        euler.setRoll(Math.toDegrees(roll));
        euler.setPitch(Math.toDegrees(pitch));
        return euler;
    }


    private double calculateDiffAngle(Euler info, Euler previousEuler) {

        double previousRoll = previousEuler.getRoll();
        double previousPitch = previousEuler.getPitch();

        double currentRoll = info.getRoll();
        double currentPitch = info.getPitch();

        double rollDiff = calculateEulerDiff(previousRoll, currentRoll);
        double pitchDiff = calculateEulerDiff(previousPitch, currentPitch);
        double finalLeanAngle = calculateFinalLeanAngle(rollDiff, pitchDiff);
        log.info("倾斜角计算, 激活时横滚角: {}, 上报横滚角: {} , 差值: {}, 激活时俯仰角: {}, 上报俯仰角: {}, 差值: {}, 综合倾角: {}",
                previousRoll, currentRoll, rollDiff, previousPitch, currentPitch, pitchDiff, finalLeanAngle);
        // 返回三值当中的绝对值最大者
        return Stream.of(Math.abs(rollDiff), Math.abs(pitchDiff), Math.abs(finalLeanAngle)).max(Comparator.comparingDouble(o -> o)).get();
    }

    private double calculateEulerDiff(double previousAngle, double currentAngle) {

        final int normalAngle = 180;
        final int maxAngle = 360;

        double diff = currentAngle - previousAngle;
        // 获取差值绝对值
        double unsignedDiff = Math.abs(diff);
        if (unsignedDiff < normalAngle) {
            return diff;
        } else {
            double supplement = maxAngle - unsignedDiff;
            return diff > 0 ? supplement : supplement * (-1);
        }
    }

}
