package com.mikesantos.FTPServerSetup;

import com.guichaguri.minimalftp.FTPServer;
import com.guichaguri.minimalftp.impl.NativeFileSystem;

import java.io.File;

@SuppressWarnings("resource")
public class FTPServerSetup {

    public static void main(String[] args) {
    	String jarName = new File(FTPServerSetup.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
        if (args.length >= 3) {
            String folder = args[0].trim();
            String username = args[1].trim();
            String password = args[2].trim();

            File file = new File(folder);
            NativeFileSystem nFileSystem = new NativeFileSystem(file);
            
            SimpleAuth auth = new SimpleAuth(nFileSystem, username, password);
            
            FTPServer server = new FTPServer();
            server.setAuthenticator(auth);
            try {
                server.listenSync(21);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        System.out.println(String.format("java -jar %s.jar [folder] [username] [password]", jarName));
    }

}
