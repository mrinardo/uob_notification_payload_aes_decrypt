# UOB Notification Payload AES Decrypt

Java program to test AES decryption of UOB's notification payload.

The AES Key (Session Key) in base64 format is intended to come from the [Session Key Decrypt test program](https://github.com/mrinardo/uob_notification_session_key_rsa_decrypt).

The encrypted payload and AES IV should come from the notification payload sent by UOB notification service.

AAD should be the domain configured on UOB's environment.

![Screenshot](https://raw.githubusercontent.com/mrinardo/uob_notification_payload_aes_decrypt/assets/PayloadDecrypt.PNG)

## Reference

- https://github.com/1MansiS/java_crypto/blob/master/cipher/SecuredGCMUsage.java
