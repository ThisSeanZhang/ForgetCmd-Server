package io.whileaway.forgetcmd.util;

import io.whileaway.forgetcmd.rbac.entites.Developer;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class Crypto {

	private final static int CRYPTO_TIMES = 2;

	private static String getSalt(String password) {
		List<String> result = Arrays.asList(password.split(""));
		Collections.shuffle(result);
		String substring = new Md5Hash(String.join("", result)).toString().substring(0, 6);
		System.out.println("salt result : " + substring);
		return substring;
	}

	public static void cryptoDeveloperPass (Developer developer) {
		if (Objects.isNull(developer)) return;
		developer.setSalt(getSalt(developer.getPass()));
		developer.setPass(cryptoPass(developer.getPass(), developer.getSalt()));
	}
	public static String cryptoPass(String pass, String salt) {
		return new Md5Hash(pass, salt, CRYPTO_TIMES).toString();
	}

	public static String cryptoPass(Supplier<String> pass, Supplier<String> salt) {
		return cryptoPass(pass.get(), salt.get());
	}


	public static void decryptoPass (String target, String pass, String salt) {
			String s = new Md5Hash(target, salt, 2).toString();
			System.out.println(s.equals(pass));
	}
	 public static void main(String[] args) {
		String password = "12345678";
//		List<String> result = Arrays.asList(password.split(""));
//		Collections.shuffle(result);
//		 System.out.println(new Md5Hash(password, "123456").toString().length());
//		System.out.println(result.stream().limit(6).collect(Collectors.joining("")));
//		 System.out.println(password.substring(0,6));
//		 Developer developer = new Developer();
//		 developer.setDeveloperPass("ABCDEFGHIJK");
//		 cryptoPass(developer);
//		 System.out.println(developer);
//		 decryptoPass("123456789", developer.getDeveloperPass(), developer.getSalt());
//		 decryptoPass("ABCDEFGHIJK", developer.getDeveloperPass(), developer.getSalt());
	}
}
