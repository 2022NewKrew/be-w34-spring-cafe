//package com.kakao.cafe.config;
//
//import com.kakao.cafe.domain.Vault;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.vault.authentication.AppRoleAuthentication;
//import org.springframework.vault.authentication.AppRoleAuthenticationOptions;
//import org.springframework.vault.authentication.ClientAuthentication;
//import org.springframework.vault.authentication.TokenAuthentication;
//import org.springframework.vault.client.VaultEndpoint;
//import org.springframework.vault.config.AbstractVaultConfiguration;
//import org.springframework.vault.core.VaultTemplate;
//import org.springframework.vault.support.VaultResponseSupport;
//import org.springframework.vault.support.VaultToken;
//
//import java.net.URI;
//
//@Configuration
//public class VaultConfig extends AbstractVaultConfiguration {
//
//    private VaultTemplate vaultTemplate = new VaultTemplate(vaultEndpoint(), clientAuthentication());
//
//
//    @Override
//    public VaultEndpoint vaultEndpoint() {
//        try {
//            return VaultEndpoint.from(new URI("https://vault-beta.daumkakao.io"));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public ClientAuthentication clientAuthentication() {
//        return new TokenAuthentication("s.ZvWlovKxqHHwrh37ocaApAyX");
//    }
//
////    @Override
////    public ClientAuthentication clientAuthentication() {
////        AppRoleAuthenticationOptions options = AppRoleAuthenticationOptions.builder()
////                .roleId(AppRoleAuthenticationOptions.RoleId.provided("90bef2a4-5808-b2b9-c3bb-a41abdc1425b"))
////                .secretId(AppRoleAuthenticationOptions.SecretId.wrapped(VaultToken.of("639b4872-983f-e7c0-d9c2-756af6c2d8f8")))
////                .build();
////
////        return new AppRoleAuthentication(options, restOperations());
////    }
//}
