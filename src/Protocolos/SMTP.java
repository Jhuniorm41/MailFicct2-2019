/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Protocolos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import utils.Constantes;

/**
 *
 * @author ADL
 */
public class SMTP {
    private static final int PORT = 25; // SMTP

    public static void sendMail(String toMail, String subject, String content) {
        // Estableciendo variables
        BufferedReader reader;
        DataOutputStream writer;
        String command;

        try {
            Socket socket;
            socket = new Socket(Constantes.MAIL_SERVER_HOST, PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new DataOutputStream(socket.getOutputStream());

            if (socket != null && reader != null && writer != null) {
                reader.readLine();
                // Saludar al servidor
                command = "EHLO " + Constantes.MAIL_SERVER_HOST + "\r\n";
                writer.writeBytes(command);
                System.out.println(command);
                //getMultiline(reader);
                System.out.println(getMultiline(reader));

                command = "MAIL FROM : " + Constantes.MAIL_USERMAIL + "\r\n";
                writer.writeBytes(command);
                System.out.println(command);
                //reader.readLine();
                System.out.println(reader.readLine());

                command = "RCPT TO : " + toMail + "\r\n";
                writer.writeBytes(command);
                System.out.println(command);
                //reader.readLine();
                System.out.println(reader.readLine());
                
                // Escribir Mensaje
                command = "DATA\n";
                writer.writeBytes(command);
                System.out.println(command);
                System.out.println(getMultiline(reader));

                command = "Subject: " + subject + "\r\n" + content + "\n.\r\n";
                writer.writeBytes(command);
                System.out.println(command);
                System.out.println(reader.readLine());

                command = "QUIT\r\n";
                writer.writeBytes(command);
                System.out.println(command);
                System.out.println(reader.readLine());
            }

            // Cerrar Conexion
            writer.close();
            reader.close();
            socket.close();
            System.out.println("Cierra Conexión");
        } catch (IOException ex) {
            System.out.println("Error-IOExecption: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error-Exception: " + ex.getMessage());
        }
    }

    static protected String getMultiline(BufferedReader in) throws IOException {
        String lines = "";
        while (true) {
            String line = in.readLine();
            if (line == null) {
                // Server closed connection
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.charAt(3) == ' ') {
                lines = lines + "\n" + line;
                // No more lines in the server response
                break;
            }
            // Add read line to the list of lines
            lines = lines + "\n" + line;
        }
        return lines;
    }
}
