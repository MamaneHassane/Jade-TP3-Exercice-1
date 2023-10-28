package Main;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.StringTokenizer;

public class AgentB extends Agent {
    int laSomme=0;
    public void setup(){
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
                ACLMessage courrier = receive(mt);
                if(courrier!=null && courrier.getContent()!=null) {
                        StringTokenizer lesNombres = new StringTokenizer(courrier.getContent(), "-");
                        while (lesNombres.hasMoreTokens()) {
                            String token = lesNombres.nextToken();
                            if (!token.equals("-")) {
                                laSomme += Integer.parseInt(token);
                                System.out.println("La somme est " + laSomme);
                            }
                        }
                        if (laSomme >= 50) {
                            ACLMessage caSuffit = new ACLMessage(ACLMessage.INFORM);
                            caSuffit.addReceiver(courrier.getSender());
                            caSuffit.setContent("stop");
                            send(caSuffit);
                            System.out.println("Agent B: Je meurs");
                            myAgent.doDelete();
                        }
                        else {
                            ACLMessage envoiEncore = new ACLMessage(ACLMessage.INFORM);
                            envoiEncore.addReceiver(courrier.getSender());
                            envoiEncore.setContent("Continue");
                            send(envoiEncore);
                        }
                    }
                }
        });
    }
}
