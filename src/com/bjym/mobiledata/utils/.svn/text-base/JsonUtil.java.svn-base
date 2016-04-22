package com.bjym.mobiledata.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	public static String objToString(Object obj) {
		return JSON.toJSONString(obj);
	}

	public static Object stringToObj(String text, Class clazz) {
		return JSON.parseObject(text, clazz);
	}

	public static JSONObject strToJSON(String text) {
		return JSON.parseObject(text);
	}

	public static Object jsonToObj(JSON json, Class clazz) {
		return JSON.toJavaObject(json, clazz);
	}

	public static String getValueFromString(String text, String key) {
		return (String) JSON.parseObject(text).get(key);
	}

	public static JSONArray strToJSONArray(String text) {
		return JSONArray.parseArray(text);
	}

	public static void main(String[] args) {
		// class Person {
		// private String name;
		// private String sex;
		//
		// public Person() {
		// super();
		// }
		//
		// public Person(String name, String sex) {
		// super();
		// this.name = name;
		// this.sex = sex;
		// }
		//
		// public String getName() {
		// return name;
		// }
		//
		// public void setName(String name) {
		// this.name = name;
		// }
		//
		// public String getSex() {
		// return sex;
		// }
		//
		// public void setSex(String sex) {
		// this.sex = sex;
		// }
		// }
		//
		// Person p1 = new Person("张三", "男");
		// Person p2 = new Person("李四", "女");
		//
		// List<Person> list = new ArrayList<Person>();
		// list.add(p1);
		// list.add(p2);
		//
		// JSONArray array =
		// JsonUtil.strToJSONArray(JSONArray.toJSONString(list));
		// for (int i = 0; i < array.size(); i++) {
		// System.out.println(array.get(i));
		// }
		// String s = "{\"name\":\"张三\",\"sex\":\"男\"}";
		// System.out.println(((Person) JsonUtil.strToObj(s, Person.class))
		// .getName());
	}
}
