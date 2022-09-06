package com.pre_38.pre_project.member.token;

import org.apache.tomcat.websocket.AuthenticationException;

public interface TokenProvider {
    String createToken(String payload);

    String parsePayload(String token);

    void validateToken(String token) throws AuthenticationException;
}
