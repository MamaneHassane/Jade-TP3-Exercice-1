package Main;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Random;

public class AgentA extends Agent {
    int nombre_Nombres;
    String suite_Nombre = "";

    public void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                nombre_Nombres = new Random().nextInt(3);
                suite_Nombre = "";
                for (int i = 0; i < nombre_Nombres; i++) {
                    suite_Nombre += Integer.toString(new Random().nextInt(11)) + "-";
                }
                System.out.println(suite_Nombre);

                ACLMessage nombresEnvoyes = new ACLMessage(ACLMessage.INFORM);
                nombresEnvoyes.addReceiver(new AID("AgentB", AID.ISLOCALNAME));
                nombresEnvoyes.setContent(suite_Nombre);
                send(nombresEnvoyes);
            }
        });

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage reponse = receive();
                if (reponse != null) {
                    if (reponse.getContent().equals("stop")) {
                        System.out.println("Reception du message " + reponse.getContent());
                        myAgent.doDelete();
                    }
                }
            }
        });
    }
}
