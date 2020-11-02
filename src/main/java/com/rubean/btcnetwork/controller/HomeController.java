package com.rubean.btcnetwork.controller;

import com.rubean.btcnetwork.bitcoin.MyWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private MyWallet myWallet;

    @RequestMapping
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/send")
    public String send(@RequestParam String amount, @RequestParam String address) {
        myWallet.send(amount, address);
        return "Sent " + amount + " BTC to " + address;
    }

    @RequestMapping(value = "/balance")
    public String balance() {
        return myWallet.getBalance() / 100000000 + " BTC";
    }

    @RequestMapping(value = "/address")
    public String address() {
        return "Wallet address: " + myWallet.getAddress();
    }

}
