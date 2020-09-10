package com.core.webapi.config.server;

import com.core.webapi.model.Role;
import com.core.webapi.model.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User currentUser = (User) authentication.getUserAuthentication().getPrincipal();
        StringBuilder roles = new StringBuilder();
        List<Role> grantedRoles = currentUser.getGrantedRoles();
        for (int i = 0; i < grantedRoles.size(); i++) {
            Role role = grantedRoles.get(i);

            if(i != grantedRoles.size() - 1){
                roles.append(role.getCode() + ",");
            }else {
                roles.append(role.getCode());
            }

        }
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("username", authentication.getName());
        additionalInfo.put("role", getRole(roles.toString()));
        additionalInfo.put("group", getGroup("sample"));
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(
                additionalInfo);
        return accessToken;
    }
    private String getGroup(String username) {
        return username;
    }

    private String getRole(String i) {
        return i;
    }
}
