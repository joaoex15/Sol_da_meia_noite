package app;

public class Notificacao {
    private String destinatario;
    private String mensagem;
    private String meio; // E-mail, SMS ou WhatsApp

    public Notificacao(String destinatario, String mensagem, String meio) {
        this.destinatario = destinatario;
        this.mensagem = mensagem;
        this.meio = meio;
    }

    public void enviar() {
        // Aqui você implementaria a lógica para enviar a notificação
        // Exemplo: Integrar com APIs de envio de e-mail, SMS, WhatsApp, etc.
        System.out.println("Enviando " + meio + " para " + destinatario + ": " + mensagem);
    }
}
