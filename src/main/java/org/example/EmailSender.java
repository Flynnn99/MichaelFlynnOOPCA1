package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class EmailSender
{
    private final ArrayList<Email> emailList;


    public EmailSender()
    {
        this.emailList = new ArrayList<>();

    }
    public void sendEmail(String to, String from, String subject, String text)
    {

        Email E = new Email(to,from,subject,text);
        boolean dup = false;


        for (Email e : emailList)
        {


            if (e.equals(E))
            {
                dup = true;
                System.out.println("Email Already Exists");
                break;

            }
        }
        if (!dup) {
            emailList.add(E);
            System.out.println("Email was Successfully Sent");
        }
    }

    public void sendEmailMenu()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Compose an Email");
        System.out.print("To:  ");
        String to = keyboard.nextLine();
        System.out.print("\nSubject:  ");
        String subject = keyboard.nextLine();
        System.out.print("\nText:  ");
        String text = keyboard.nextLine();
        System.out.print("\nFrom:  ");
        String from = keyboard.nextLine();

        sendEmail(to,from,subject,text);


    }

    public void displayOutBox()
    {

        System.out.println("=============================================================================");
        for (Email e : this.emailList)
        {

            System.out.println(e.getTo() + "   \n" + e.getSubject() + " \n" + e.getText() + "\n" + e.getFrom() + "\n" + e.getDate() + "\n");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }
}
