package com.oneguyonegirl.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.openjpa.jdbc.kernel.PreparedQueryCacheImpl.StrongExclusion;

;

public class CAHUtil {

	public static final int STRETCHED_HASH_ALGO = 0;
	public static final int NORMAL_HASH_ALGO = 1;

	private static CAHUtil instance = new CAHUtil();
	

	private CAHUtil(){
		
	}
	
	public static CAHUtil getInstance(){
		
		return instance;
	}
	
	

	public  String[] loadCards(final String fileName) throws IOException {

		final CAHFIleParser cp = CAHFIleParser.getInstance();
		final String[] cardFile = cp.loadFile(fileName);
		return cardFile;
	}
	
	/*
	 * This method returns the password and the salt to store
	 */
	public  PasswordContainer saltPassword(final String pwd) {

		final PasswordContainer con = new PasswordContainer();
		con.setPassword(pwd);
		final byte[] salt = generateRawSalt();
		con.setSalt(salt);
		System.out.println("random: " + con.getSalt() + " / "
				+ con.getPassword() + " / " + salt);
		return con;
	}

	public  byte[] generateRawSalt(final Integer hashLength) {

		final byte data[] = new byte[hashLength];
		try {
			final SecureRandom secureRandomObj = SecureRandom.getInstance("SHA1PRNG");

			secureRandomObj.nextBytes(data);

		} catch (final NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public  byte[] generateRawSalt() {

		return generateRawSalt(Integer.valueOf(64));

	}

	public  String generateHashSalt() {

		return generateHashSalt(32);
	}

	public  String generateHashSalt(final Integer hashLength) {

		String hexString = "";
		try {
			final SecureRandom secureRandomObj = SecureRandom.getInstance("SHA1PRNG");
			final byte data[] = new byte[hashLength];
			secureRandomObj.nextBytes(data);

			hexString = hexify(data);

		} catch (final NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hexString.toString();
	}

	public  PasswordContainer generateHash(final PasswordContainer pc, final int algo) {

		PasswordContainer retval = null;
		if (algo == STRETCHED_HASH_ALGO) {
			try {
				retval =  generateStretchedPasswordHash(pc);
			} catch (final NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			retval = generateHash(pc);
		}
		
		return retval; 

	}

	public  PasswordContainer generateHash(final PasswordContainer pc) {

		MessageDigest md;

		if (pc.getSalt() == null) {
			pc.setSalt(generateRawSalt());
		}

		final String pwd = pc.getPassword() + hexify(pc.getSalt());
		byte[] digest = new byte[0];
		try {
			md = MessageDigest.getInstance("SHA-256");

			md.update(pwd.getBytes()); // Change this to "UTF-16" if needed
			digest = md.digest();
		} catch (final NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final String hexString = hexify(digest);

		// set the hashed pwd;
		pc.setHashedPassword(hexString);
		return pc;
	}

	private  PasswordContainer generateStretchedPasswordHash(
			final PasswordContainer pc) throws NoSuchAlgorithmException,
			InvalidKeySpecException {

		final int iterations = 1000;

		final char[] chars = pc.getPassword().toCharArray();
		final byte[] salt = pc.getSalt() == null ? generateRawSalt() : pc.getSalt();

		final PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
		final SecretKeyFactory skf = SecretKeyFactory
				.getInstance("PBKDF2WithHmacSHA1");
		final byte[] hashedPwd = skf.generateSecret(spec).getEncoded();

		// The PBKDF2 hash salts the password in one go
		pc.setHashedPassword(hexify(hashedPwd));
		pc.setSalt(salt);
		return pc;
	}

	public  String hexify(final byte[] data) {

		final StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < data.length; i++) {

			// logical and with 0xff to address negative numbers - meaning the
			// hex string is one way
			final String hex = Integer.toHexString(0xff & data[i]);
			if (hex.length() == 1){
				hexString.append('0');
			}
			hexString.append(hex);
		}

		return hexString.toString();
	}

	public  boolean isPasswordValid(final PasswordContainer pc, final int algo) {

		System.out.println("passed hash: " + pc.getHashedPassword());
		System.out.println("generated hash: "
				+ generateHash(pc, algo).getHashedPassword());

		return generateHash(pc, algo).getHashedPassword().equals(
				pc.getHashedPassword());

	}
}
