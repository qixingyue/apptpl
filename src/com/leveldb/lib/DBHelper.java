package com.leveldb.lib;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

import android.content.Context;

public class DBHelper {

	private static File mPath;
	private final static String DB_NAME = "db-cache";
	private static DB mDb;

	private static DBHelper sInstance;

	public static DBHelper getInstance(Context context) {
		if (sInstance == null) {
			sInstance = new DBHelper();
		}
		open(context);
		return sInstance;
	}

	public String getDbName(){
		return DB_NAME;
	}
	
	private static void open(Context context) {
		if (mDb == null) {
			mPath = new File(context.getCacheDir(), DB_NAME);
			mDb = new DB(mPath);
			mDb.open();
		}
	}

	public void putObject(String key, Object value) {
		try {
			mDb.put(bytes(key), serialize(value));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getObject(String key) {
		try {
			byte[] value = mDb.get(bytes(key));
			if (value != null) {
				return deserialize(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteObject(String key) {
		try {
			mDb.delete(bytes(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(b);
		o.writeObject(obj);
		return b.toByteArray();
	}

	public static Object deserialize(byte[] bytes) throws IOException,
			ClassNotFoundException {
		ByteArrayInputStream b = new ByteArrayInputStream(bytes);
		ObjectInputStream o = new ObjectInputStream(b);
		Object obj = o.readObject();
		return obj;
	}

	private byte[] bytes(String str) {
		try {
			return str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
