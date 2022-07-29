package com.theverygroup.lf.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.theverygroup.lf.steps.GlobalContext;

public class EncryptDecrypt {
	public static void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
	}
	public static void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
	}
	public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
		}
	}
	public static void doCopy(InputStream is, OutputStream os) throws IOException {
		byte[] bytes = new byte[64];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}
	public static void readproperties(String path) {
		File file = new File(path);
		Properties prop = new Properties();
		FileInputStream inStream = null;
		try {
			
			inStream = new FileInputStream(file);
			prop.load(inStream);
			for(Entry<Object,Object> entry:prop.entrySet()) {
				GlobalContext.addCredentials((String)entry.getKey(), (String)entry.getValue());
			}
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if(inStream != null) {
					inStream.close();
					file.delete();
				}
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public static void main(String args[]) throws Throwable {
//		String key ="udelsaWKSswwOOQ!@Awdnj#";
//		FileInputStream fis = new FileInputStream("target\\properties");
//		FileOutputStream fos = new FileOutputStream("src\\main\\resources\\properties.encrypt");
//		encrypt(key, fis, fos);
		
//		FileInputStream fis2 = new FileInputStream("src\\main\\resources\\properties.encrypt");
//		FileOutputStream fos2 = new FileOutputStream("src\\main\\resources\\properties");
//		decrypt(key, fis2, fos2);
//		readproperties("src\\main\\resources\\properties");
		
	}
}
