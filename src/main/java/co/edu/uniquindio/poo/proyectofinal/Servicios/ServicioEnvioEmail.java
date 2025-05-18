package co.edu.uniquindio.poo.proyectofinal.Servicios;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;


public class ServicioEnvioEmail {

    private static ServicioEnvioEmail INSTANCE;

    public static ServicioEnvioEmail getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServicioEnvioEmail();
        }
        return INSTANCE;
    }

    public void enviarNotificacion(String destinatario, String asunto, String mensaje) {

        Email email = EmailBuilder.startingBlank()
                .from("fuentesthomasito777@gmail.com")
                .to(destinatario)
                .withSubject(asunto)
                .withPlainText(mensaje)
                .buildEmail();

        try {
            Mailer mailer = MailerBuilder
                    .withSMTPServer("smtp.gmail.com", 587, "fuentesthomasito777@gmail.com", "igfs alxn gezv qogs")
                    .withTransportStrategy(TransportStrategy.SMTP_TLS)
                    .withDebugLogging(true)
                    .buildMailer();

            mailer.sendMail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

