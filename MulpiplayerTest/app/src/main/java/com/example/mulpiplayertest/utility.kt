package com.example.mulpiplayertest

import java.math.BigInteger
import java.security.MessageDigest

class utility {
    companion object{
        fun algo_MD5(mail: String): String? {
            var result: String? = null
            val messageDigest = MessageDigest.getInstance("MD5")
            messageDigest.reset()
            messageDigest.update(mail.toByteArray())
            val bigInt = BigInteger(1, messageDigest.digest())
            result = bigInt.toString(16)
            return result
        }

    }
}