package com.domain.utils

import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

fun encrypt(dataToEncrypt: String, secretKey: String = "your_secret12345"): String {
    try {
        val key = SecretKeySpec(secretKey.toByteArray(), "AES")
        val iv = IvParameterSpec(ByteArray(16))
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key, iv)
        val encryptedBytes = cipher.doFinal(dataToEncrypt.toByteArray())
        return Base64.getEncoder().encodeToString(encryptedBytes)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ""
}

fun decrypt(encryptedData: String, secretKey: String = "your_secret12345"): String {
    try {
        val key = SecretKeySpec(secretKey.toByteArray(), "AES")
        val iv = IvParameterSpec(ByteArray(16))
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, key, iv)
        val decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData))
        return String(decryptedBytes)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ""
}