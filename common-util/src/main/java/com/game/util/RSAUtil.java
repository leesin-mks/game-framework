/*
 * Copyright 2016-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.game.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jacken
 *
 */
public class RSAUtil
{

    private static final Logger LOGGER = LoggerFactory.getLogger(RSAUtil.class);

    /**
     * * 生成密钥对 *
     * 
     * @return KeyPair *
     * @throws Exception
     */
    public static KeyPair generateKeyPair() throws Exception
    {
        try
        {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA",
                    new BouncyCastleProvider());

            // KEY_SIZE关系到块加密的大小
            final int KEY_SIZE = 1024;
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            KeyPair keyPair = keyPairGen.generateKeyPair();
            return keyPair;
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    public static RSAPublicKey generateRSAPublicKey(String pubModString,
            String pubPubExpString) throws Exception
    {
        byte[] pubModBytes = Base64.decodeBase64(pubModString);
        byte[] pubPubExpBytes = Base64.decodeBase64(pubPubExpString);

        return generateRSAPublicKey(pubModBytes, pubPubExpBytes);
    }

    // 生成公钥
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus,
            byte[] publicExponent) throws Exception
    {
        KeyFactory keyFac = null;
        try
        {
            keyFac = KeyFactory.getInstance("RSA", new BouncyCastleProvider());
        }
        catch (NoSuchAlgorithmException ex)
        {
            throw new Exception(ex.getMessage());
        }

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(1,
                modulus), new BigInteger(1, publicExponent));
        try
        {
            return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
        }
        catch (InvalidKeySpecException ex)
        {
            throw new Exception(ex.getMessage());
        }
    }

    public static RSAPrivateKey generateRSAPrivateKey(String priModString,
            String priPriExpString) throws Exception
    {
        byte[] modBytes = Base64.decodeBase64(priModString);
        byte[] priExpBytes = Base64.decodeBase64(priPriExpString);
        return generateRSAPrivateKey(modBytes, priExpBytes);
    }

    // 生成私钥
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus,
            byte[] privateExponent) throws Exception
    {
        KeyFactory keyFac = null;
        try
        {
            keyFac = KeyFactory.getInstance("RSA", new BouncyCastleProvider());
        }
        catch (NoSuchAlgorithmException ex)
        {
            throw new Exception(ex.getMessage());
        }

        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(1,
                modulus), new BigInteger(1, privateExponent));
        try
        {
            return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
        }
        catch (InvalidKeySpecException ex)
        {
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * Encrypt String.
     * 
     * @return byte[]
     */
    public static byte[] encrypt(RSAPublicKey publicKey, byte[] obj)
    {
        if (publicKey != null)
        {
            try
            {
                Cipher cipher = Cipher.getInstance("RSA");
                // ENCRYPT_MODE : 用于将 cipher 初始化为加密模式的常量。
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);

                ByteBuffer buffer = ByteBuffer.wrap(obj);
                ByteBuffer result = ByteBuffer.allocate(2048);

                byte[] data = new byte[117];

                // 分段解密
                while (buffer.position() < buffer.limit())
                {
                    if (buffer.remaining() >= 117)
                        buffer.get(data);
                    else
                    {
                        data = new byte[buffer.remaining()];
                        buffer.get(data, 0, buffer.remaining());
                    }

                    result.put(cipher.doFinal(data));
                }

                result.flip();
                byte[] cipherBytes = new byte[result.limit()];
                result.get(cipherBytes);

                return cipherBytes;
            }
            catch (Exception e)
            {
                LOGGER.error("RSAUtil:encrypt", e);
            }
        }
        return null;
    }

    public static String encrypt(String pubModString, String pubPubExpString,
            String str)
    {
        try
        {
            RSAPublicKey recoveryPubKey = generateRSAPublicKey(pubModString,
                    pubPubExpString);
            byte[] raw = encrypt(recoveryPubKey, str.getBytes());
            return base64Encode(raw);
        }
        catch (Exception e)
        {
            LOGGER.error("RSAUtil:encrypt", e);
        }

        return null;
    }

    /**
     * Basic decrypt method
     * 
     * @return byte[]
     */
    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] obj)
    {
        if (privateKey != null)
        {
            try
            {
                Cipher cipher = Cipher.getInstance("RSA");
                ByteBuffer buffer = ByteBuffer.wrap(obj);
                ByteBuffer result = ByteBuffer.allocate(2048);

                // DECRYPT_MODE : 用于将 cipher 初始化为解密模式的常量。
                cipher.init(Cipher.DECRYPT_MODE, privateKey);

                byte[] data = new byte[128];

                // 分段解密
                while (buffer.position() < buffer.limit())
                {
                    if (buffer.remaining() >= 128)
                        buffer.get(data);
                    else
                    {
                        data = new byte[buffer.remaining()];
                        buffer.get(data, 0, buffer.remaining());
                    }

                    result.put(cipher.doFinal(data));
                }

                result.flip();
                byte[] cipherBytes = new byte[result.limit()];
                result.get(cipherBytes);

                return cipherBytes;
            }
            catch (Exception e)
            {
                LOGGER.error("RSAUtil:decrypt", e);
            }
        }

        return null;
    }

    public static byte[] decrypt(String priModString, String priPriExpString,
            byte[] clipherString) throws Exception
    {
        RSAPrivateKey recoveryPriKey = generateRSAPrivateKey(priModString,
                priPriExpString);
        return RSAUtil.decrypt(recoveryPriKey, clipherString);
    }

    public static String decrypt(String priModBytesNew,
            String priPriExpBytesNew, String str)
    {
        try
        {
            byte[] enRaw = Base64.decodeBase64(str.getBytes());
            byte[] data = RSAUtil.decrypt(priModBytesNew, priPriExpBytesNew,
                    enRaw);
            return new String(data);
        }
        catch (Exception e)
        {
            LOGGER.error("RSAUtil:decrypt", e);
        }

        return null;
    }

    public static String base64Encode(byte[] bts)
    {
        return Base64.encodeBase64String(bts);
    }

    public static void main(String[] args)
    {
        KeyPair keyPair;
        try
        {
            keyPair = generateKeyPair();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            System.out.print(base64Encode(privateKey.getEncoded()));
            System.out.print("---------------------------------------\r\n");
            System.out.print(base64Encode(publicKey.getEncoded()));
            System.out.print("pri---------------------------------------\r\n");
            String privt = base64Encode(privateKey.getPrivateExponent().toByteArray());
            System.out.print(privt);
            String module = base64Encode(privateKey.getModulus().toByteArray());
            System.out.print("module---------------------------------------\r\n");
            System.out.print(module);
            System.out.print("16---------------------------------------\r\n");
            System.out.print(privateKey.getModulus().toString(16));
            // System.out.print(publicKey);
            String pub = base64Encode(publicKey.getPublicExponent().toByteArray());
            System.out.print("---------------------------------------\r\n");
            System.out.print("publicKey.getPublicExponent().toString(16): " + publicKey.getPublicExponent().toString(16));

            String str = "123456789asdfghjkl";
            str = "{\"pwd\":\"123\",\"time\":\"123456\"}";
            String secrt = encrypt(module, pub, str);
            System.out.print("---------------------------------------\r\n");
            System.out.print("secret: " + secrt);
            module = "AMKdkIslPzpQjfpg1Tb7QHDjq1EuSqcHx1lOCUCDXWcz1V6zd6IJQjKSB149Lxl/ZeAGh2TWrGNIcE3tGrPsmn4e3NjrRoNLSbhqNPKRgury1L87o6vbqoT9dP9ot9eyop2DGPgpx3ey5nRxNcffAaNXMvOSBQt2D1CkXVtGco6d";
            privt = "T58AHnV4J0cCtz97q9liVW1Hz1hFkY2UqPxMu7ii85CYyHxbQw9PpnnpCsy0YtWk2+E/7mkHsTzXOYJ6bMctsxh5EcA8MT8DvXerUs6v9ZyQv3yMZtwQl+jw5mvSvc2y3AzMcMx3GIGRR/IEpmr5v0Xs5vk+pQY3Bk3Xk3QhuW8=";
            //secrt = "jBDpSLST5Q6dxTAurS64HEO+QiFmv0ebeScF7TfMSZuA1CTrHOOTFMfzA5myhGssaSBE7ZeZAhu40Dqw6gHcusBlA01+E97yt1k/REAzjkObVBKuEjIkZGXw8SkEHyv09bbkUoZBkswHc35LU+1A4nKtft5oFoqoy1uGrJ7xIZY=";
            // secrt = "dw0uFiO7GIMCTvFpV6SDGqjYAknaxyiaUmZ1YmcuHAreH+Xi/+CHq3/Nycw0I5WgpjozUTWJOxJolrlEFm6W3Lp65RdpuCIqP407pYFlm0DrpQx3ZoeoYnNjc4Q9kfk45xaYzD1NgM20V+W4mbI5xm5U/LdABeMHn78ZlKL15KM=";
            // byte[] bytes = HexStringToBinary(secrt);
            // secrt = "oEHBkjcYnQ1hWFm8GBqnS5cWV9vujPyuH2VUtPq3VXPqbRegtJd2eqMWDkpTdJTYssKRGxk6WKwbVPGXp4f/m8HkreetgCWbUgNAHzF4IRm3Mwg8hZg4pZd66ajOK4l7";
            secrt = decrypt(module, privt, secrt);
            System.out.print("---------------------------------------\r\n");
            System.out.print(secrt);
        }
        catch (Exception e)
        {
            LOGGER.error("RSAUtil:main", e);

        }

    }

    public static byte[] HexStringToBinary(String src)
    {
        int m = 0, n = 0;
        int len = src.length() / 2;
        System.out.println(len);
        byte[] ret = new byte[len];
        for (int i = 0; i < len; i++)
        {
            m = i * 2 + 1;
            n = m + 1;
            // Height nibble
            int h = Byte.decode("0x" + src.substring(i * 2, m)) << 4;
            // Low nibble
            int l = Byte.decode("0x" + src.substring(m, n));
            ret[i] = (byte) (h | l);
        }
        return ret;
    }
}
