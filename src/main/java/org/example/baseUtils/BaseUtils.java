package org.example.baseUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseUtils implements Colors{
    private static BaseUtils instance;
    public String encode(String rawPassword) {
        return PasswordConfigurer.encode(rawPassword);
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return PasswordConfigurer.matchPassword(rawPassword, encodedPassword);
    }

    public static BaseUtils getInstance() {
        if (instance == null) {
            instance = new BaseUtils();
        }
        return instance;
    }

    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static Gson gsonWithNulls = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

    private final static Scanner readText = new Scanner(System.in);
    private final static Scanner readNumerics = new Scanner(System.in);

    public static String readText() {
        return readText.nextLine();
    }

    public static String readText(String data) {
        print(data, Colors.BLUE);
        return readText.nextLine();
    }

    public static String readPassword(String data) {
        return new String(System.console().readPassword(data + Colors.BLUE));
    }

    public static String readText(String data, String color) {
        print(data, color);
        return readText.nextLine();
    }

    public static void print(String data) {
        print(data, Colors.BLUE);
    }

    public static void print(String data, String color) {
        out.print(color + data + Colors.RESET);
    }

    public static void println(Object data) {
        println(data, Colors.BLUE);
    }


    public static void println(Object data, String color) {
        out.println(color + data + Colors.RESET);
    }

    public static void println(String data, Object... args) {
        out.printf((data) + "%n", args);
    }

    public static String otp(int bound){
        return String.valueOf((new Random().nextInt((int) Math.pow(10,bound-1), (int) Math.pow(10,bound))));
    }

    public static Gson gson_ = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) ->
            ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime()).setDateFormat("dd.MM.YYYY  HH:mm:ss").setPrettyPrinting().create();

}
