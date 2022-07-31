package com.liteiot.common.util.jwt;

import com.liteiot.common.util.UUIDUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * JWT信息类
 */
@Data
@AllArgsConstructor
public class JWTInfo implements Serializable, IJWTInfo {
    private String username;
    private String userId;
    private String name;
    private String tokenId;

    public JWTInfo(String username, String userId, String name) {
        this.username = username;
        this.userId = userId;
        this.name = name;
        this.tokenId = UUIDUtils.generateShortUuid();
    }

    @Override
    public String getUniqueName() {
        return username;
    }


    @Override
    public String getId() {
        return userId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTokenId() {
        return tokenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JWTInfo jwtInfo = (JWTInfo) o;

        if (!Objects.equals(username, jwtInfo.username)) {
            return false;
        }
        return Objects.equals(userId, jwtInfo.userId);

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
