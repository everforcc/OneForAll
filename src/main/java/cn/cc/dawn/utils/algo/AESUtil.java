package cn.cc.dawn.utils.algo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * AES加密工具类
 */
@Slf4j
public class AESUtil {

    public static String generate_key() {
        return "default_aes_key";
    }

    private static Map<String, Cipher> keyMap = new HashMap<>();

    private static Map<String, Cipher> keyDecryptMap = new HashMap<>();

    private synchronized static Cipher getCipher(String password) {
        if (StringUtils.isBlank(password)) {
            return null;
        }
        Cipher cipher = keyMap.get(password);
        if (cipher == null) {
            try {
                byte[] keyBytes = Arrays.copyOf(password.getBytes("ASCII"), 16);

                SecretKey key = new SecretKeySpec(keyBytes, "AES");

                cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE, key);
                keyMap.put(password, cipher);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cipher;
    }

    private synchronized static Cipher getDecryptCipher(String password) {
        if (StringUtils.isBlank(password)) {
            return null;
        }
        Cipher cipher = keyDecryptMap.get(password);
        if (cipher == null) {
            try {
                byte[] keyBytes = Arrays.copyOf(password.getBytes("ASCII"), 16);

                SecretKey key = new SecretKeySpec(keyBytes, "AES");

                cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, key);
                keyDecryptMap.put(password, cipher);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cipher;
    }

    public static String aes_encrypt(String password) {
        try {
            Cipher cipher = getCipher(generate_key());
            byte[] cleartext = password.getBytes("UTF-8");
            byte[] ciphertextBytes = cipher.doFinal(cleartext);
            return new String(Hex.encodeHex(ciphertextBytes));

        } catch (Exception e) {
            log.info("aes_encrypt error:" + password);
            e.printStackTrace();
        }
        return null;
    }

    public static String aes_decrypt(String password) {
        try {
            if (StringUtils.startsWith(password, "{AES}")) {
                password = StringUtils.replace(password, "{AES}", "");
            }
            Cipher cipher = getDecryptCipher(generate_key());

            byte[] cleartext = Hex.decodeHex(password.toCharArray());
            byte[] ciphertextBytes = cipher.doFinal(cleartext);
            return new String(ciphertextBytes, "UTF-8");

        } catch (Exception e) {
            log.info("aes_decrypt error:" + password);
            e.printStackTrace();
        }
        return null;
    }

    public static String safe_aes_decrypt(String password) {
        if (StringUtils.isEmpty(password)) {
            return password;
        }
        if (StringUtils.startsWith(password, "{AES}")) {
            password = StringUtils.replace(password, "{AES}", "");
        }
        try {
            Cipher cipher = getDecryptCipher(generate_key());

            byte[] cleartext = Hex.decodeHex(password.toCharArray());
            byte[] ciphertextBytes = cipher.doFinal(cleartext);
            return new String(ciphertextBytes, "UTF-8");

        } catch (Exception e) {
            log.info("safe_aes_decrypt error:" + password);
            e.printStackTrace();
        }
        return null;
    }

    public static String aes_encrypt(String password, String strKey) {
        try {
            if (StringUtils.isBlank(strKey)) {
                return password;
            }
            Cipher cipher = getCipher(strKey);

            byte[] cleartext = password.getBytes("UTF-8");
            byte[] ciphertextBytes = cipher.doFinal(cleartext);

            return new String(Hex.encodeHex(ciphertextBytes));

        } catch (Exception e) {
            log.info("aes_encrypt2 error:" + password);
            //logger.error(e);
        }
        return null;
    }

    public static String aes_decrypt(String password, String strKey) {
        if (StringUtils.isNotBlank(password) && password.contains("{AES}")) {
            password = password.substring(5);
        }
        try {
            Cipher cipher = getDecryptCipher(strKey);

            byte[] cleartext = Hex.decodeHex(password.toCharArray());
            byte[] ciphertextBytes = cipher.doFinal(cleartext);
            return new String(ciphertextBytes, "UTF-8");

        } catch (Exception e) {
            log.info("aes_encrypt2 error:" + password);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回数据格式为{AES}加密字符串
     *
     * @param str
     * @return
     */
    public static String aesEncryptFormat(String str) {
        return "{AES}" + aes_encrypt(str);
    }

    /**
     * 返回数据格式为{AES}加密字符串 安全
     *
     * @param str
     * @return
     */
    public static String safeEncryptFormat(String str) {
        if (StringUtils.isEmpty(str) || StringUtils.startsWith(str, "{AES}")) {
            return str;
        }
        return "{AES}" + aes_encrypt(str);
    }

    public static void main(String[] args) {
        String str = aes_encrypt("{\"phone\":\"13813813138\",\"lon\":\"120.6794\",\"lat\":\"31.31696\"}");
        log.info(str);
        str = "7a1620347169f7942aa59cda3a61eebd88d610630b9751a8445\n" +
                "dfb4e69838707cdc8f7cf3e53cadddaf3014d75ecd5290a640d0\n" +
                "167e2750cee4319a64c9a5acb2c21a45eac123acc3dca249efcc\n" +
                "52963fdf16e7241593ef06bef4b326a6581904c1b4b85d1caec8\n" +
                "3b0714b7d9f63fa2bb96830488925f29b1e6953e84d9b217e791\n" +
                "a56b96626d383711a1f00c46ee1e5a8da8308f07affca5336c06\n" +
                "4883ba7bcabce0f854b2adb8b98d34e873eb7ab56920e4aabf2b\n" +
                "8051a9cb2e712bf53c0554bb9f6dc8058a895fa7ddcad1a3d671\n" +
                "d3800ff1d51848e2230505f40261d5999f4dcee0febf0e2cef32\n" +
                "328cf0152bd1efae61ac8dba66b2b98acca7675501fd75933ec6\n" +
                "3c29741e67293074bfaba04461b3584c000faeb47e0115b02983\n" +
                "f35f2724e0a4682d14928ec8ba1eb7e80dbec1f6416e91133c80\n" +
                "825517bcc76d3aea825aa334b98984cc5c73a503104a22f1d1bc\n" +
                "5b726125b3ad8044c59a748fc7de5eda898791c3430f41142663\n" +
                "a379d0e0e2aace38a4abfdf829d84dc5f576592509d86bcf8a33\n" +
                "683655171ef198c0b1adb3491d3b59d6452de1485103a3b41e22\n" +
                "6ac776b225a122964c08dc69426af439494746f2301883470f47\n" +
                "e4b31f99456bc";
        log.info(aes_decrypt(str));
    }

}
