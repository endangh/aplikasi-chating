package com.khannedy.chating;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eko Khannedy
 * @since 11/27/14
 */
public class ClientDatabase {

    /**
     * io.netty.channel.Channel merupakan representasi dari client
     * di framework Netty
     */
    private static List<Channel> channels = new ArrayList<>();

    /**
     * Menambah client ke databae ketika login
     *
     * @param channel chat client
     */
    public static void login(Channel channel) {
        channels.add(channel);
    }

    /**
     * Menghapus client dari database ketika logout
     *
     * @param channel chat client
     */
    public static void logout(Channel channel) {
        channels.remove(channel);
    }

    /**
     * Mengirim pesan dari client ke client yang lain
     *
     * @param sender client pengirim pesan
     * @param json   pesan yang dikirim
     */
    public static void chat(Channel sender, String json) {
        // stream dan lambda expression adalah fitur java 8
        channels.stream()
                // filter untuk hapus pengirim supaya tidak dikirim pesan nya
                .filter(channel -> channel != sender)
                        // kirim kesemua client selain pengirim pesan
                .forEach(channel -> channel.writeAndFlush(json));
    }

//    /**
//     * Mengirim pesan dari client ke client yang lain
//     *
//     * @param sender client pengirim pesan
//     * @param json   pesan yang dikirim
//     */
//    public static void chat(Channel sender, String json) {
//
//        // operasi chat() tanpa menggunakan stream dan lambda expression java 8
//
//        // filter untuk hapus pengirim supaya tidak dikirim pesan nya
//        List<Channel> hasilFilter = new ArrayList<>();
//        for (Channel channel : channels) {
//            if (channel != sender) {
//                // jika bukan sender, tambahkan ke hasilFilter
//                hasilFilter.add(channel);
//            }
//        }
//
//        // kirim kesemua client selain pengirim pesan
//        for (Channel channel : hasilFilter) {
//            channel.writeAndFlush(json);
//        }
//    }

}
