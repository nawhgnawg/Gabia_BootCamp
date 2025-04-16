package dev.mvc.resort_v3sbm3c;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@SpringBootTest
public class SecurityTest {

    private String iv;
    private Key keySpec;

    // 키 생성
    public void keyGen() throws UnsupportedEncodingException {
        // String key = "soldeskAES256Key";    // key 는 16자, 123 ->
        String key = "soldeskAES256235";    // key 는 16자, 123 ->
        iv = key.substring(0, 16);

        byte[] keyBytes = new byte[16];
        byte[] bytes = key.getBytes("UTF-8");
        System.out.println(bytes);

        int len = bytes.length;
        if (len > keyBytes.length) {
            len = keyBytes.length;
        }

        System.arraycopy(bytes, 0, keyBytes, 0, len);
        keySpec = new SecretKeySpec(keyBytes, "AES");
        System.out.println(keySpec);
    }

    // 암호화
    public String aesEncode(String str) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

        byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
        String enStr = new String(Base64.getEncoder().encode(encrypted));

        return enStr;
    }

    // 복호화 - 복호화를 가능하게 하는것은 불법임! 따라서 기능 구현 하지 않음. 잊어버리면 비밀번호 변경으로 안내.
    public String aesDecode(String str) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));

        byte[] byteStr = Base64.getDecoder().decode(str.getBytes());

        return new String(cipher.doFinal(byteStr), "UTF-8");
    }



    @Test
    void pass() {
        try {
            keyGen();

            String text = "가나다 123 ABC !@#";
            System.out.println("암호화할 문자: " + text);

            String encText = aesEncode(text);   // 암호화 : kiOZvoBdQWQWAdVY1WvwqoK3etb1quxuNwg1XJOQ6Bc=
            System.out.println("암호화된 문자(DBMS 저장): " + encText);

            String decText = aesDecode(encText); // 복호화 : 가나다 123 ABC !@#
            System.out.println("복호화된 문자: " + decText);

            System.out.println("Login");
            String login = "가나다 123 ABC !@#";
            String login_encoding = aesEncode(login);   // 암호화 : kiOZvoBdQWQWAdVY1WvwqoK3etb1quxuNwg1XJOQ6Bc=
            System.out.println("Form login: " + login_encoding);

            // 비교시 복호화가 아니라 암호화를하여 비교
            Assertions.assertThat(login_encoding).isEqualTo(encText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
