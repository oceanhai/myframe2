package com.example.java_lib.pactera.shell;

import org.junit.Test;

/**
 * 作者 wh
 * 
 * 创建日期 2020/11/20 16:54
 * 
 * 描述：
 */
public class ShellTest {
    private static final byte PASSWORD = 'A';

    @Test
    public void testEncryptUtils(){
        byte encryptResult = encrypt((byte) 42);
        System.out.println("加密后 encryptResult = " + encryptResult);
        byte decryptResult = decrypt(encryptResult);
        System.out.println("解密后 decryptResult = " + decryptResult);
    }

    /**
     * 加密
     */
    public byte encrypt(byte msg){
        System.out.println("加密前 msg = " + msg);
        msg ^= PASSWORD;
        return msg;
    }

    /**
     * 解密
     */
    public byte decrypt(byte msg){
        msg ^= PASSWORD;
        return msg;
    }
}
