package com.company;

import com.company.koneksi.Konfigurasi;
import com.company.koneksi.RequestHandler;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String nama="fathurahman";
        String nim="201810370311257";
        String asal="tarakan";
        String no_hp="087810159937";


        Scanner input = new Scanner(System.in);

        System.out.println("masukkan nama");
        nama=input.nextLine();

        System.out.println("masukkan nim");
        nim = input.nextLine();

        System.out.println("masukkan asal");
        asal=input.nextLine();

        System.out.println("masukkan no hp");
        no_hp=input.nextLine();


        HashMap<String,String> params = new HashMap<>();

        params.put(Konfigurasi.KEY_NAMA,nama);
        params.put(Konfigurasi.KEY_NIM,nim);
        params.put(Konfigurasi.KEY_ASAL,asal);
        params.put(Konfigurasi.KEY_NO_HP,no_hp);

        RequestHandler res = new RequestHandler();

//        //MEMASUKKAN ANTRIAN
//        String result = res.sendPostRequest(Konfigurasi.URL_ADD,params);
//        System.out.println(result);


        //MENGAMBIL ANTRIAN
        String result2 =res.sendGetRequest(Konfigurasi.URL_POP);
        System.out.println(result2);

//        //MENGHAPUS ANTRIAN YANG BARU SAJA DIAMBIL
//        String result3 = res.sendGetRequest(Konfigurasi.URL_DELETE);
//        System.out.println(result3);


    }
}
