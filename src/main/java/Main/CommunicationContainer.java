package Main;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class CommunicationContainer {
    public static void main(String[] args) {
        try {
            Runtime runtime = Runtime.instance();
            ProfileImpl profileImpl = new ProfileImpl();
            profileImpl.setParameter(ProfileImpl.MAIN_HOST, "localhost");
            AgentContainer agentContainer = runtime.createAgentContainer(profileImpl);
            AgentController agentController1 = agentContainer.createNewAgent
                    ("AgentA", "Main.AgentA", new Object[]{});
            agentController1.start();
            AgentController agentController2 = agentContainer.createNewAgent
                    ("AgentB", "Main.AgentB", new Object[]{});
            agentController2.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
