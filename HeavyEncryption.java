package dataStructures;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
public class HeavyEncryption 
{
	public static class AES extends HeavyEncryption
	{
		private static String secretKey;
		private static String salt;
		public AES(String k, String s)
		{
			secretKey = k;
			salt = s;
		}
		public String getKey()
		{
			return secretKey;
		}
		public String getSalt()
		{
			return salt;
		}
		public static void setKey(String k)
		{
			secretKey = k;
		}
		public static void setSalt(String s)
		{
			salt = s;
		}
		public String decrypt(String message)
		{
			try
			{
				byte[] iv =  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				
				IvParameterSpec ivspec = new IvParameterSpec(iv);
				
				
				SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
				KeySpec spec = new PBEKeySpec(secretKey.toCharArray(),salt.getBytes(),655,256);
				SecretKey tmp = factory.generateSecret(spec);
				SecretKeySpec makeKey = new SecretKeySpec(tmp.getEncoded(), "AES");
				
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
				cipher.init(Cipher.DECRYPT_MODE, makeKey,ivspec);
				return new String(cipher.doFinal(Base64.getDecoder().decode(message)));
			}
			catch (Exception e)
			{
				System.out.println("Error while decrypting: "+e.toString());
			}
			return null;
		}
		public static String encrypt(String message)
		{
			try
			{
				byte[] iv =  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				
				IvParameterSpec ivspec = new IvParameterSpec(iv);
				
				
				SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
				KeySpec spec = new PBEKeySpec(secretKey.toCharArray(),salt.getBytes(),655,256);
				SecretKey tmp = factory.generateSecret(spec);
				SecretKeySpec makeKey = new SecretKeySpec(tmp.getEncoded(), "AES");
				
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
				cipher.init(Cipher.ENCRYPT_MODE, makeKey,ivspec);
				return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes(StandardCharsets.UTF_8)));
			}
			catch (Exception e)
			{
				System.out.println("Error while decrypting: "+e.toString());
			}
			return null;
		}
	}
	public static String convertToString(String binaryString)
	{
		String[] breakBinary = binaryString.split("");
		int[] numBinary = new int[breakBinary.length];
		for (int i = 0; i<breakBinary.length;i++)
		{
			numBinary[i] = Integer.parseInt(breakBinary[i]);
		}
		int decimal = 0;
		int trackPower=  numBinary.length-1;
		for (int increment: numBinary)
		{
			if(increment==1)
			{
				decimal+=Math.pow(2, trackPower);
			}
			trackPower--;
		}
		char getCharacter = (char)decimal;
		String finalString = Character.toString(getCharacter);
		return finalString;
	}
	public static String[] getBinaries(String text)
	{
		String[] binaryList = new String[text.length()];
		for (int i = 0; i<binaryList.length;i++)
		{
			binaryList[i] = Integer.toBinaryString(text.charAt(i));
		}
		return binaryList;
	}
	public static String[] formEncryptedBinaries(String unencrypted)
	{
		String[] getBinaries = getBinaries(unencrypted);
		for (int i = 0; i<getBinaries.length;i++)
		{
			getBinaries[i] = AES.encrypt(getBinaries[i]);
		}
		return getBinaries;
	}
}
