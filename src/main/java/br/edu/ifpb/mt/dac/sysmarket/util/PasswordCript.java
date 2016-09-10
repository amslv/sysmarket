package br.edu.ifpb.mt.dac.sysmarket.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.edu.ifpb.mt.dac.sysmarket.entities.Usuario;

public class PasswordCript {

	public static String criptografarSenha(Usuario usuario) throws Exception {
		try {
			return cript(usuario.getSenha());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	private static String cript(String senha) throws Exception {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(senha.getBytes("UTF-8"));
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String output = bigInt.toString(16);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("N�oo foi poss�vel criptografar a senha!");
		} catch (UnsupportedEncodingException e) {
			throw new Exception("N�o foi poss�vel criptografar a senha!");
		}
	}
}
