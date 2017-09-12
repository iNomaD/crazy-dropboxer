package fi.jyu.dropboxer;

import fi.jyu.dropboxer.client.DropboxClient;
import fi.jyu.dropboxer.models.AccountInfo;

/**
 * Created by Denis on 12.09.2017.
 */

//command line interface
public class CLI {

    public static void main(String[] args){
        System.out.println("Hello dropbox!");

        // create dropbox client
        DropboxClient dropboxClient = DropboxClient.getInstance();

        // make authorization

        // use client
        AccountInfo accountInfo = dropboxClient.getAccountInfo();
        System.out.println(accountInfo);
    }
}
